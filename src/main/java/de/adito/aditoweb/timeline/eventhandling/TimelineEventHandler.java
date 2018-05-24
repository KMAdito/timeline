package de.adito.aditoweb.timeline.eventhandling;

import de.adito.aditoweb.timeline.definition.ITimelineEventHandler;
import de.adito.aditoweb.timeline.listener.*;
import de.adito.aditoweb.timeline.listener.ITimelineActionListener;
import de.adito.aditoweb.timeline.listener.ITimelinePauseListener;
import de.adito.aditoweb.timeline.listener.ITimelinePlayListener;
import de.adito.aditoweb.timeline.listener.ITimelineTickFinishListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Eventhandler für die TImeline
 *
 * @author k.mifka, 17.01.2018
 */
public class TimelineEventHandler implements ITimelineEventHandler
{
  private final List<ITimelineActionListener> actionListener = new ArrayList<>();

  /**
   * Feuert ein PauseEvent
   */
  protected void firePauseEvent()
  {
    _fireEvent(ITimelinePauseListener.class);
  }

  /**
   * Feuert ein PlayEvent
   */
  protected void firePlayEvent()
  {
    _fireEvent(ITimelinePlayListener.class);
  }

  /**
   * Feuert ein StopEvent
   */
  protected void fireStopEvent()
  {
    _fireEvent(ITimelineStopListener.class);
  }

  /**
   * Feuert ein TickFinishEvent
   */
  protected void fireTickFinishEvent()
  {
    _fireEvent(ITimelineTickFinishListener.class);
  }

  @Override
  public void addPauseListener(@NotNull ITimelinePauseListener pListener)
  {
    _addListener(pListener);
  }

  @Override
  public void addPlayListener(@NotNull ITimelinePlayListener pListener)
  {
    _addListener(pListener);
  }

  @Override
  public void addStopListener(@NotNull ITimelineStopListener pListener)
  {
    _addListener(pListener);
  }

  @Override
  public void addTickFinishListener(@NotNull ITimelineTickFinishListener pListener)
  {
    _addListener(pListener);
  }

  @Override
  public void removePauseListener(@NotNull ITimelinePauseListener pListener)
  {
    _removeListener(pListener);
  }

  @Override
  public void removePlayListener(@NotNull ITimelinePlayListener pListener)
  {
    _removeListener(pListener);
  }

  @Override
  public void removeStopListener(@NotNull ITimelineStopListener pListener)
  {
    _removeListener(pListener);
  }

  @Override
  public void removeTickListener(@NotNull ITimelineTickFinishListener pListener)
  {
    _removeListener(pListener);
  }

  /**
   * Fügt einen Listener hinzu
   *
   * @param pListener hinzuzufügender Listener
   */
  private void _addListener(ITimelineActionListener pListener)
  {
    synchronized (actionListener)
    {
      actionListener.add(pListener);
    }
  }

  /**
   * Entfernt einen Listener
   *
   * @param pListener zu entfernender Listener
   */
  private void _removeListener(ITimelineActionListener pListener)
  {
    synchronized (actionListener)
    {
      actionListener.remove(pListener);
    }
  }

  /**
   * Feuert ein Event der übergebenen Klasse
   *
   * @param pActionListener Klasse des zu feuernden Listeners
   */
  private void _fireEvent(Class<? extends ITimelineActionListener> pActionListener)
  {
    for (ITimelineActionListener action : actionListener)
      if(pActionListener.isAssignableFrom(action.getClass()))
        action.fireAction();
  }
}
