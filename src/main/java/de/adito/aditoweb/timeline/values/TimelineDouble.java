package de.adito.aditoweb.timeline.values;

import de.adito.aditoweb.timeline.timing.ITimelineBezier;
import de.adito.aditoweb.timeline.values.definition.AbstractTimelineValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * TimelineValue für Doubles
 *
 * @author k.mifka, 10.01.2018
 */
public class TimelineDouble extends AbstractTimelineValue<Double>
{
  public TimelineDouble()
  {
    super(0.0, 0.0);
  }

  public TimelineDouble(@NotNull Double pStart, @NotNull Double pEnd)
  {
    super(pStart, pEnd);
  }

  public TimelineDouble(@NotNull Double pStart, @NotNull Double pEnd, @NotNull Consumer<Double> pConsumer)
  {
    super(pStart, pEnd, pConsumer);
  }

  public TimelineDouble(@NotNull Double pStart, @NotNull Double pEnd, @Nullable ITimelineBezier pTiming)
  {
    super(pStart, pEnd, pTiming);
  }

  public TimelineDouble(@NotNull Double pStart, @NotNull Double pEnd, @Nullable ITimelineBezier pTiming, @NotNull Consumer<Double> pConsumer)
  {
    super(pStart, pEnd, pTiming, pConsumer);
  }

  @NotNull
  @Override
  public Double calculateValue(float pTimedProgress)
  {
    double value = (getEnd() - getStart()) * (double) pTimedProgress;
    return getStart() + value;
  }

}
