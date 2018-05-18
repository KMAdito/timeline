package com.github.kmadito.timeline.timing;

import com.github.kmadito.timeline.timing.timer.TimelineTimerTask;
import org.jetbrains.annotations.NotNull;

/**
 * @author k.mifka, 17.01.2018
 */
public interface ITimelineTimerTaskCancelListener
{
  void canceled(@NotNull TimelineTimerTask pTimerTask);
}
