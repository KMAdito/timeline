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
 * @author k.mifka, 17.01.2018
 */
public class TimelineEventHandler implements ITimelineEventHandler
{
  private final List<ITimelineActionListener> actionListener = new ArrayList<>();

  protected void firePauseEvent()
  {
    _fireEvent(ITimelinePauseListener.class);
  }

  protected void firePlayEvent()
  {
    _fireEvent(ITimelinePlayListener.class);
  }

  protected void fireStopEvent()
  {
    _fireEvent(ITimelineStopListener.class);
  }

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

  private void _addListener(ITimelineActionListener pListener)
  {
    synchronized (actionListener)
    {
      actionListener.add(pListener);
    }
  }

  private void _removeListener(ITimelineActionListener pListener)
  {
    synchronized (actionListener)
    {
      actionListener.remove(pListener);
    }
  }

  private void _fireEvent(Class<? extends ITimelineActionListener> pActionListener)
  {
    for (ITimelineActionListener action : actionListener)
      if(pActionListener.isAssignableFrom(action.getClass()))
        action.fireAction();
  }
}
