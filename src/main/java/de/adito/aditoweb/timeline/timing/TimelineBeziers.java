package de.adito.aditoweb.timeline.timing;

/**
 * Sammlung von oft verwendeten ITimelineBeziers
 *
 * @author k.mifka, 10.01.2018
 */
public final class TimelineBeziers
{
  public static final ITimelineBezier LINEAR = new TimelineBezier(0, 0, 1, 1);
  public static final ITimelineBezier EASE = new TimelineBezier(.25F,.1F,.25F,1);
  public static final ITimelineBezier EASE_IN = new TimelineBezier(.42F,0,1,1);
  public static final ITimelineBezier EASE_OUT = new TimelineBezier(0,0,.58F,1);
  public static final ITimelineBezier EASE_IN_OUT = new TimelineBezier(.42F,0,.58F,1);
}
