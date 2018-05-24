package de.adito.aditoweb.timeline.definition;

import org.jetbrains.annotations.NotNull;

/**
 * Dieses Interface dient zur Aufnahme eines Runnables,
 * um den Thread f�r die Wert�nderung zu bestimmen
 *
 * @author k.mifka, 19.01.2018
 */
public interface ITimelineRunner
{
  /**
   * Wert�nderungen werden innerhalb des Runnable ausgef�hrt.
   *
   * @param pRunnable Runnable f�r Wert�nderungen
   */
  void runValueChange(@NotNull Runnable pRunnable);
}
