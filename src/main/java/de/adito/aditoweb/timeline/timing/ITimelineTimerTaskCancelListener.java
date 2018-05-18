package de.adito.aditoweb.timeline.timing;

import de.adito.aditoweb.timeline.timing.timer.TimelineTimerTask;
import org.jetbrains.annotations.NotNull;

/**
 * @author k.mifka, 17.01.2018
 */
public interface ITimelineTimerTaskCancelListener
{
  void canceled(@NotNull TimelineTimerTask pTimerTask);
}
