package de.adito.aditoweb.timeline.definition;

import de.adito.aditoweb.timeline.timing.ITimelineBezier;
import de.adito.aditoweb.timeline.values.definition.ITimelineValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Allgemeine Definition einer Timeline
 *
 * Eine Timeline ist ein Zeitstrahl, welcher ITimelineValues aufnhemen kann
 * und vorwiegen zur Realisierung von Animationen gedacht ist.
 *
 * Sie nimmt eine Dauer an (setDuration()), welche angibt, wie lange es dauert,
 * alle hinzugefügten ITimelineValues vom Startwert bis zu Endwert zu interpolieren.
 *
 * Die Interpolation lässt sich mithilfe von Bezier-Kurven timen (setDefaultTiming())
 *
 * @author k.mifka, 10.01.2018
 */
public interface ITimeline extends ITimelineEventHandler
{
  /**
   * ITimelineValue hinzufügen
   *
   * @param pAdjustValue ITimelineValue
   */
  void addValue(@NotNull ITimelineValue pAdjustValue);

  /**
   * ITimelineValue entfernen
   *
   * @param pAdjustValue ITimelineValue
   */
  void removeValue(@NotNull ITimelineValue pAdjustValue);

  /**
   * Entfernt alle ITimelineValues
   */
  void removeAll();

  /**
   * Liste mit allen hinzugefügten ITimelineValues
   *
   * @return Liste mit allen hinzugefügten ITimelineValues
   */
  @NotNull
  List<ITimelineValue> getValues();

  /**
   * Setzt die Dauer der Timeline
   *
   * @param pMillis Dauer in Millisekunden
   */
  void setDuration(long pMillis);

  /**
   * Gibt die Dauer der Timeline zurück
   *
   * @return Dauer in Millisekunden
   */
  long getDuration();

  /**
   * Gibt die Dauer zwischen den einzelnen Interpolationen an.
   *
   * @param pTickDelay Dauer in Millisekunden
   */
  void setTickDelay(long pTickDelay);

  /**
   * Gibt die Dauer zwischen den einzelnen Interpolationen zurück
   *
   * @return Dauer in Millisekunden
   */
  long getTickDelay();

  /**
   * Startet die Interpolation vom Startwert zum Endwert
   */
  void playForward();

  /**
   * Startet die Interpolation vom Endwert zum Startwert
   */
  void playBackward();

  /**
   * Pausiert die Interpolation
   */
  void pause();

  /**
   * Stopt die Interpolation (Progress := 0)
   */
  void stop();

  /**
   * Gibt an, ob die Interpolation gerade läuft
   *
   * @return true, wenn sie läuft
   */
  boolean isRunning();

  /**
   * Gibt den Fortschritt der Interpolation an
   *
   * @return Fortschritt in Prozent
   */
  float getProgress();

  /**
   * Setzt den Fortschritt der Interpolation
   *
   * @param pProgress Fortschritt in Prozent
   */
  void setProgress(float pProgress);

  /**
   * Setzt den "Runner"
   *
   * Hier kann der Thread gesetzt werden, in welchem die Wertänderungen ausgeführt werden sollen.
   *
   * Beispiel für Swing-Animation: setRunner(SwingUtilities::invokeLater);
   *
   * @param pFadeRunner ITimelineRunner
   */
  void setRunner(@Nullable ITimelineRunner pFadeRunner);

  /**
   * Setzt das Standard-Timing
   *
   * Dieses Timing wird nur verwendet, wenn ein Value selber kein Timing besitzt
   *
   * @param pDefaultTiming ITimelineBezier
   */
  void setDefaultTiming(@NotNull ITimelineBezier pDefaultTiming);

  /**
   * Gibt das Standard-Timing zurück
   *
   * @return Standard-Timing
   */
  @NotNull
  ITimelineBezier getDefaultTiming();
}
