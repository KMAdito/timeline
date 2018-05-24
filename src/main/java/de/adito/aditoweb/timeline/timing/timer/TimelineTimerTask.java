package de.adito.aditoweb.timeline.timing.timer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * Spezieller TimerTask, welcher einen Abbruchslistener unterstützt
 *
 * @author k.mifka, 17.01.2018
 */
public abstract class TimelineTimerTask extends TimerTask
{
  private final List<ITimelineTimerTaskCancelListener> cancelListeners = new ArrayList<>();

  /**
   * Fügt einen Abbruchslistener hinzu
   *
   * @param pListener hinzuzufügender Listener
   */
  public void addCancelListener(@NotNull ITimelineTimerTaskCancelListener pListener)
  {
    synchronized (cancelListeners)
    {
      cancelListeners.add(pListener);
    }
  }

  /**
   * Entfernt einen Abbruchslistener
   *
   * @param pListener entfernender Listener
   */
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

  /**
   * Feuert ein Abbruchsereignis
   */
  private void _fireCancelEvent()
  {
    synchronized (cancelListeners)
    {
      for (ITimelineTimerTaskCancelListener listener : new ArrayList<>(cancelListeners))
        listener.canceled(this);
    }
  }
}
