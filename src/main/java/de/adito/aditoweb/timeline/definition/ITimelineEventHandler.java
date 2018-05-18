package de.adito.aditoweb.timeline.definition;

import de.adito.aditoweb.timeline.listener.*;
import de.adito.aditoweb.timeline.listener.ITimelinePauseListener;
import de.adito.aditoweb.timeline.listener.ITimelinePlayListener;
import de.adito.aditoweb.timeline.listener.ITimelineTickFinishListener;
import org.jetbrains.annotations.NotNull;

/**
 * @author k.mifka, 17.01.2018
 */
public interface ITimelineEventHandler
{
  void addPauseListener(@NotNull ITimelinePauseListener pListener);

  void addPlayListener(@NotNull ITimelinePlayListener pListener);

  void addStopListener(@NotNull ITimelineStopListener pListener);

  void addTickFinishListener(@NotNull ITimelineTickFinishListener pListener);

  void removePauseListener(@NotNull ITimelinePauseListener pListener);

  void removePlayListener(@NotNull ITimelinePlayListener pListener);

  void removeStopListener(@NotNull ITimelineStopListener pListener);

  void removeTickListener(@NotNull ITimelineTickFinishListener pListener);
}
