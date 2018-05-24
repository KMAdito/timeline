package de.adito.aditoweb.timeline.timing;

/**
 * Bezierkurve für die Timeline
 *
 * @author k.mifka, 10.01.2018
 */
public class TimelineBezier implements ITimelineBezier
{
  private float x1;
  private float y1;
  private float x2;
  private float y2;

  /**
   * Konstruktor
   *
   * @param pX1 X1
   * @param pY1 Y1
   * @param pX2 X2
   * @param pY2 Y2
   */
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

  @Override
  public ITimelineBezier invert()
  {
    //noinspection SuspiciousNameCombination
    return new TimelineBezier(y1, x1, y2, x2);
  }

  /**
   * Berechnet den X- oder Y-Wert
   *
   * @param pV1 X1 oder Y1
   * @param pV2 X2 oder Y2
   * @param pT Wert für die Berechnung
   * @return X- oder Y-Wert
   */
  private float _calculateValue(float pV1, float pV2, float pT)
  {
    float a = 1 - pT;
    return (float)((3 * pT * Math.pow(a, 2) * pV1) + (3 * Math.pow(pT, 2) * a * pV2) + Math.pow(pT, 3));
  }
}
