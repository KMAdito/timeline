package de.adito.aditoweb.timeline.values.definition;

import de.adito.aditoweb.timeline.definition.ITimelineRunner;
import de.adito.aditoweb.timeline.timing.ITimelineBezier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Definition eines TimelineValues
 *
 * @author k.mifka, 10.01.2018
 */
public interface ITimelineValue<T>
{
  /**
   * Gibt den Startwert des Values an
   *
   * @return Startwert
   */
  @NotNull
  T getStart();

  /**
   * Gibt den Endwert des Values an
   *
   * @return Endwert
   */
  @NotNull
  T getEnd();

  /**
   * Gibt den aktuellen Wert an
   *
   * @return aktueller Wert
   */
  @NotNull
  T get();

  /**
   * Setzt den Wert
   *
   * @param pValue Wert
   */
  void setValue(@NotNull T pValue);

  /**
   * Setzt den Startwert
   *
   * @param pStart Startwert
   */
  void setStart(@NotNull T pStart);

  /**
   * Setzt den Endwert
   *
   * @param pEnd Endwert
   */
  void setEnd(@NotNull T pEnd);

  /**
   * Setzt den Fortschritt, bei dem die Interpolation beginnen soll
   *
   * @param pProgress Fortschritt in Prozent
   */
  void setIn(@Nullable Float pProgress);

  /**
   * Setzt den Fortschritt, bei dem die Interpolation enden soll
   *
   * @param pProgress Fortschritt in Prozent
   */
  void setOut(@Nullable Float pProgress);

  /**
   * Gibt den Fortschritt zurück, bei dem die Interpolation beginnen soll
   *
   * @return Fortschritt in Prozent
   */
  @Nullable
  Float getIn();

  /**
   * Gibt den Fortschritt zurück, bei dem die Interpolation enden soll
   *
   * @return Fortschritt in Prozent
   */
  @Nullable
  Float getOut();

  /**
   * Setzt das Timing
   *
   * @param pTiming Timing-Bezierkurve
   */
  void setTiming(@Nullable ITimelineBezier pTiming);

  /**
   * Gibt das Timing zurück
   *
   * @return Timing-Bezierkurve
   */
  @Nullable
  ITimelineBezier getTiming();

  /**
   * Registriert einen Consumer
   *
   * @param pConsumer Consumer für Wertänderungen
   */
  void registerConsumer(@NotNull Consumer<T> pConsumer);

  /**
   * Entfernt einen Consumer
   *
   * @param pConsumer Consumer für Wertänderungen
   */
  void unregisterConsumer(@NotNull Consumer<T> pConsumer);

  /**
   * Aktualisiert den Wert in Abhängigkeit des Fortschritts und Timings
   *
   * @param pDefaultTiming Standard-Timing
   * @param pRunner ITimelineRunner
   * @param pProgress Frotschritt in Prozent
   */
  void updateValue(ITimelineBezier pDefaultTiming, ITimelineRunner pRunner, float pProgress);
}
