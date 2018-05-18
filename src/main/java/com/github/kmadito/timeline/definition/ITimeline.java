package com.github.kmadito.timeline.definition;

import com.github.kmadito.timeline.timing.ITimelineBezier;
import com.github.kmadito.timeline.values.definition.ITimelineValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author k.mifka, 10.01.2018
 */
public interface ITimeline extends ITimelineEventHandler
{
  void addValue(@NotNull ITimelineValue pAdjustValue);

  void removeValue(@NotNull ITimelineValue pAdjustValue);

  void removeAll();

  @NotNull
  ITimelineValue[] getValues();

  void setDuration(int pMillis);

  int getDuration();

  void setTickDelay(int pTickDelay);

  int getTickDelay();

  void playForward();

  void playBackward();

  void pause();

  void stop();

  boolean isRunning();

  float getProgress();

  void setProgress(float pProgress);

  void setRunner(@Nullable ITimelineRunner pFadeRunner);

  void setDefaultTiming(@NotNull ITimelineBezier pDefaultTiming);

  @NotNull
  ITimelineBezier getDefaultTiming();
}
