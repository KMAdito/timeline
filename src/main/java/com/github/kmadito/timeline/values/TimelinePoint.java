package com.github.kmadito.timeline.values;

import com.github.kmadito.timeline.timing.ITimelineBezier;
import com.github.kmadito.timeline.values.definition.AbstractTimelineValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.function.Consumer;

/**
 * @author k.mifka, 10.01.2018
 */
public class TimelinePoint extends AbstractTimelineValue<Point>
{
  public TimelinePoint()
  {
    super(new Point(), new Point());
  }

  public TimelinePoint(@NotNull Point pStart, @NotNull Point pEnd)
  {
    super(pStart, pEnd);
  }

  public TimelinePoint(@NotNull Point pStart, @NotNull Point pEnd, @NotNull Consumer<Point> pConsumer)
  {
    super(pStart, pEnd, pConsumer);
  }

  public TimelinePoint(@NotNull Point pStart, @NotNull Point pEnd, @Nullable ITimelineBezier pTiming)
  {
    super(pStart, pEnd, pTiming);
  }

  public TimelinePoint(@NotNull Point pStart, @NotNull Point pEnd, @Nullable ITimelineBezier pTiming, @NotNull Consumer<Point> pConsumer)
  {
    super(pStart, pEnd, pTiming, pConsumer);
  }

  @NotNull
  @Override
  public Point calculateValue(float pTimedProgress)
  {
    int x = getStart().x + (int) Math.round((double) (getEnd().x - getStart().x) * pTimedProgress);
    int y = getStart().y + (int) Math.round((double) (getEnd().y - getStart().y) * pTimedProgress);

    return new Point(x, y);
  }
}
