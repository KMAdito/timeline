package de.adito.aditoweb.timeline.definition;

import org.jetbrains.annotations.NotNull;

/**
 * Dieses Interface dient zur Aufnahme eines Runnables,
 * um den Thread für die Wertänderung zu bestimmen
 *
 * @author k.mifka, 19.01.2018
 */
public interface ITimelineRunner
{
  /**
   * Wertänderungen werden innerhalb des Runnable ausgeführt.
   *
   * @param pRunnable Runnable für Wertänderungen
   */
  void runValueChange(@NotNull Runnable pRunnable);
}
