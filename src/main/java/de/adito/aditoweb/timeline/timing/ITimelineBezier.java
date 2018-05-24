package de.adito.aditoweb.timeline.timing;

/**
 * Allgemeine Definition einer Bezierkurve für die Timeline
 *
 * @author k.mifka, 10.04.2018
 */
public interface ITimelineBezier
{
  /**
   * Berechnet den X-Wert
   *
   * @param pT Wert, für welchen der X-Wert berechnet werden soll
   * @return X-Wert
   */
  float calculateX(float pT);

  /**
   * Berechnet den Y-Wert
   *
   * @param pT Wert, für welchen der Y-Wert berechnet werden soll
   * @return XY-Wert
   */
  float calculateY(float pT);

  /**
   * Gibt eine invertierte Bezierkurve zurück
   *
   * @return invertierte Bezierkurve
   */
  ITimelineBezier invert();
}
