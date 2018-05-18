package de.adito.aditoweb.timeline.timing;

/**
 * @author k.mifka, 10.01.2018
 */
public class TimelineBezier implements ITimelineBezier
{
  private float x1;
  private float y1;
  private float x2;
  private float y2;

  public TimelineBezier(float pX1, float pY1, float pX2, float pY2)
  {
    x1 = pX1;
    y1 = pY1;
    x2 = pX2;
    y2 = pY2;
  }

  @Override
  public float calculateX(float pT)
  {
    return _calculateValue(x1, x2, pT);
  }

  @Override
  public float calculateY(float pT)
  {
    return _calculateValue(y1, y2, pT);
  }

  private float _calculateValue(float pV1, float pV2, float pT)
  {
    float a = 1 - pT;
    return (float)((3 * pT * Math.pow(a, 2) * pV1) + (3 * Math.pow(pT, 2) * a * pV2) + Math.pow(pT, 3));
  }
}
