package de.adito.aditoweb.timeline.timing;

/**
 * @author k.mifka, 10.04.2018
 */
public interface ITimelineBezier
{
    float calculateX(float pT);

    float calculateY(float pT);

    ITimelineBezier invert();
}
