package de.adito.aditoweb.timeline.timing.timer;

import de.adito.aditoweb.timeline.timing.ITimelineTimerTaskCancelListener;
import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author k.mifka, 17.01.2018
 */
public class TimelineTimer
{
  private static final Object lock = new Object();

  private static Timer timer;
  private static final AtomicInteger RUNNING_TASKS = new AtomicInteger();
  private static final ITimelineTimerTaskCancelListener CANCEL_LISTENER = new _CancelListener();

  public static void schedule(@NotNull TimelineTimerTask pTask, int pPeriod)
  {
    synchronized (lock)
    {
      _createTimer();
      pTask.addCancelListener(CANCEL_LISTENER);

      RUNNING_TASKS.incrementAndGet();
      timer.schedule(pTask, 0, pPeriod);
    }
  }

  private static void _createTimer()
  {
    if(timer == null)
    {
      timer = new Timer();
      RUNNING_TASKS.set(0);
    }
  }

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
