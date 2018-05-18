package com.github.kmadito.timeline.values;

import com.github.kmadito.timeline.timing.ITimelineBezier;
import com.github.kmadito.timeline.values.definition.AbstractTimelineValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author k.mifka, 10.01.2018
 */
public class TimelineInteger extends AbstractTimelineValue<Integer>
{
  public TimelineInteger()
  {
    super(0, 0);
  }

  public TimelineInteger(@NotNull Integer pStart, @NotNull Integer pEnd)
  {
    super(pStart, pEnd);
  }

  public TimelineInteger(@NotNull Integer pStart, @NotNull Integer pEnd, @NotNull Consumer<Integer> pConsumer)
  {
    super(pStart, pEnd, pConsumer);
  }

  public TimelineInteger(@NotNull Integer pStart, @NotNull Integer pEnd, @Nullable ITimelineBezier pTiming)
  {
    super(pStart, pEnd, pTiming);
  }

  public TimelineInteger(@NotNull Integer pStart, @NotNull Integer pEnd, @Nullable ITimelineBezier pTiming, @NotNull Consumer<Integer> pConsumer)
  {
    super(pStart, pEnd, pTiming, pConsumer);
  }

  @NotNull
  @Override
  public Integer calculateValue(float pTimedProgress)
  {
    int value = (int) Math.round((double) (getEnd() - getStart()) * pTimedProgress);
    return getStart() + value;
  }

}
