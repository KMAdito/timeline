package com.github.kmadito.timeline.definition;

import com.github.kmadito.timeline.timing.ITimelineBezier;
import com.github.kmadito.timeline.timing.TimelineBeziers;

/**
 * @author k.mifka, 10.04.2018
 */
public interface ITimelineConstants
{
    int DEFAULT_DURATION = 1000;
    int DEFAULT_TICK_DELAY = 1;

    ITimelineBezier DEFAULT_BEZIER = TimelineBeziers.LINEAR;
}
