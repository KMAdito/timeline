package de.adito.aditoweb.timeline.definition;

import de.adito.aditoweb.timeline.timing.ITimelineBezier;
import de.adito.aditoweb.timeline.timing.TimelineBeziers;

/**
 * Konstanten für die Timeline
 *
 * @author k.mifka, 10.04.2018
 */
public interface ITimelineConstants
{
  long DEFAULT_DURATION = 1000;
  long DEFAULT_TICK_DELAY = 1;

  ITimelineBezier DEFAULT_BEZIER = TimelineBeziers.LINEAR;
}
