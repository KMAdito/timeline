package de.adito.aditoweb.timeline.timing;

/**
 * Sammlung von oft verwendeten ITimelineBeziers
 *
 * @author k.mifka, 10.01.2018
 */
public final class TimelineBeziers
{
  public static final ITimelineBezier LINEAR = new TimelineBezier(0, 0, 1, 1);

  public static final ITimelineBezier EASE_OUT = new TimelineBezier(0, 1, 0, 1);
}
