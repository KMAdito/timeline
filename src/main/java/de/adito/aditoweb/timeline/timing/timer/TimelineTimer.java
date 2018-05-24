package de.adito.aditoweb.timeline.timing.timer;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Spezieller Timer f�r die Timeline
 *
 * Dieser f�hrt alle TimelineTimerTasks innerhalb eines einzigen Timers aus, um Ressourcen zu sparen.
 * Wird kein Task ausgef�hrt, wird der aktuelle Timer gel�scht, um Speicherlecks zu verhindern.
 *
 * @author k.mifka, 17.01.2018
 */
public class TimelineTimer
{
  private static final Object lock = new Object();

  private static Timer timer;
  private static final AtomicInteger RUNNING_TASKS = new AtomicInteger();
  private static final ITimelineTimerTaskCancelListener CANCEL_LISTENER = new _CancelListener();

  /**
   * F�hrt einen TimelineTimerTask aus
   *
   * @param pTask auszuf�hrender Task
   * @param pPeriod Dauer in Millisekunden zwischen den Perioden
   */
  public static void schedule(@NotNull TimelineTimerTask pTask, long pPeriod)
  {
    synchronized (lock)
    {
      _createTimer();
      pTask.addCancelListener(CANCEL_LISTENER);

      RUNNING_TASKS.incrementAndGet();
      timer.schedule(pTask, 0, pPeriod);
    }
  }

  /**
   * Erzeugt den Timer
   */
  private static void _createTimer()
  {
    if(timer == null)
    {
      timer = new Timer();
      RUNNING_TASKS.set(0);
    }
  }

  /**
   * Zerst�rt den Timer
   */
  private static void _destroyTimer()
  {
    synchronized (lock)
    {
      if (timer != null)
      {
        timer.purge();
        timer.cancel();
        timer = null;
        RUNNING_TASKS.set(0);
      }
    }
  }

  /**
   * Listener, welcher ausl�st, sobald ein Task abgebrochen wurde
   * Sind alle Tasks abgebrochenm, wird der Timer zerst�rt.
   */
  private static class _CancelListener implements ITimelineTimerTaskCancelListener
  {
    @Override
    public void canceled(@NotNull TimelineTimerTask pTimerTask)
    {
      pTimerTask.removeCancelListener(this);

      int count = timer.purge();

      if(RUNNING_TASKS.addAndGet(-count) <= 0)
        _destroyTimer();
    }
  }
}
