package de.adito.aditoweb.timeline.timing.timer;

import org.jetbrains.annotations.NotNull;

/**
 * Abbruchslistener für den TimelineTimerTask
 *
 * @author k.mifka, 17.01.2018
 */
public interface ITimelineTimerTaskCancelListener
{
  /**
   * Der Task wurde abgebrochen
   *
   * @param pTimerTask abgebrochener Task
   */
  void canceled(@NotNull TimelineTimerTask pTimerTask);
}
