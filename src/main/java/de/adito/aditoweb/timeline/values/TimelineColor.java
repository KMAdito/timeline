package de.adito.aditoweb.timeline.values;

import de.adito.aditoweb.timeline.timing.ITimelineBezier;
import de.adito.aditoweb.timeline.values.definition.AbstractTimelineValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.function.Consumer;

/**
 * TimelineValue für Farben
 *
 * @author k.mifka, 10.01.2018
 */
public class TimelineColor extends AbstractTimelineValue<Color>
{
  public TimelineColor()
  {
    super(Color.BLACK, Color.WHITE);
  }

  public TimelineColor(@NotNull Color pStart, @NotNull Color pEnd)
  {
    super(pStart, pEnd);
  }

  public TimelineColor(@NotNull Color pStart, @NotNull Color pEnd, @NotNull Consumer<Color> pConsumer)
  {
    super(pStart, pEnd, pConsumer);
  }

  public TimelineColor(@NotNull Color pStart, @NotNull Color pEnd, @Nullable ITimelineBezier pTiming)
  {
    super(pStart, pEnd, pTiming);
  }

  public TimelineColor(@NotNull Color pStart, @NotNull Color pEnd, @Nullable ITimelineBezier pTiming, @NotNull Consumer<Color> pConsumer)
  {
    super(pStart, pEnd, pTiming, pConsumer);
  }

  @NotNull
  @Override
  public Color calculateValue(float pTimedProgress)
  {
    int rS = getStart().getRed();
    int gS = getStart().getGreen();
    int bS = getStart().getBlue();
    int aS = getStart().getAlpha();

    int rE = getEnd().getRed();
    int gE = getEnd().getGreen();
    int bE = getEnd().getBlue();
    int aE = getEnd().getAlpha();

    int r = rS + (int)((double)(rE - rS) * pTimedProgress);
    int g = gS + (int)((double)(gE - gS) * pTimedProgress);
    int b = bS + (int)((double)(bE - bS) * pTimedProgress);
    int a = aS + (int)((double)(aE - aS) * pTimedProgress);

    return new Color(r, g, b, a);
  }

}
