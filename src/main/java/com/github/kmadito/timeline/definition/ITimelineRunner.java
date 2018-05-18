package com.github.kmadito.timeline.definition;

import org.jetbrains.annotations.NotNull;

/**
 * @author k.mifka, 19.01.2018
 */
public interface ITimelineRunner
{
  void runValueFade(@NotNull Runnable pRunnable);
}
