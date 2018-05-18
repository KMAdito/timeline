package de.adito.aditoweb.timeline.timing.timer;

import de.adito.aditoweb.timeline.timing.ITimelineTimerTaskCancelListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * @author k.mifka, 17.01.2018
 */
public abstract class TimelineTimerTask extends TimerTask
{
  private final List<ITimelineTimerTaskCancelListener> cancelListeners = new ArrayList<>();

  public void addCancelListener(@NotNull ITimelineTimerTaskCancelListener pListener)
  {
    synchronized (cancelListeners)
    {
      cancelListeners.add(pListener);
    }
  }

  public void removeCancelListener(@NotNull ITimelineTimerTaskCancelListener pListener)
  {
    synchronized (cancelListeners)
    {
      cancelListeners.remove(pListener);
    }
  }

  @Override
  public boolean cancel()
  {
    boolean cancel = super.cancel();
    _fireCancelEvent();
    return cancel;
  }

  private void _fireCancelEvent()
  {
    synchronized (cancelListeners)
    {
      for (ITimelineTimerTaskCancelListener listener : new ArrayList<>(cancelListeners))
        listener.canceled(this);
    }
  }
}
