package de.adito.aditoweb.timeline.timing.timer;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Spezieller Timer für die Timeline
 *
 * Dieser führt alle TimelineTimerTasks innerhalb eines einzigen Timers aus, um Ressourcen zu sparen.
 * Wird kein Task ausgeführt, wird der aktuelle Timer gelöscht, um Speicherlecks zu verhindern.
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
   * Führt einen TimelineTimerTask aus
   *
   * @param pTask auszuführender Task
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
   * Zerstört den Timer
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
   * Listener, welcher auslöst, sobald ein Task abgebrochen wurde
   * Sind alle Tasks abgebrochenm, wird der Timer zerstört.
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
