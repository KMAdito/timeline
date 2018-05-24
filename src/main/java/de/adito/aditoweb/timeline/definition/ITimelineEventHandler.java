package de.adito.aditoweb.timeline.definition;

import de.adito.aditoweb.timeline.listener.*;
import de.adito.aditoweb.timeline.listener.ITimelinePauseListener;
import de.adito.aditoweb.timeline.listener.ITimelinePlayListener;
import de.adito.aditoweb.timeline.listener.ITimelineTickFinishListener;
import org.jetbrains.annotations.NotNull;

/**
 * K�mmert sich um das EventHandling
 *
 * @author k.mifka, 17.01.2018
 */
public interface ITimelineEventHandler
{
  /**
   * F�gt einen PauseListener hinzu.
   * Dieser l�st aus, sobald die Timeline pausiert wurde.
   *
   * @param pListener ITimelinePauseListener
   */
  void addPauseListener(@NotNull ITimelinePauseListener pListener);

  /**
   * F�gt einen PlayListener hinzu.
   * Dieser l�st aus, sobald die Timeline gestratet wurde.
   *
   * @param pListener ITimelinePlayListener
   */
  void addPlayListener(@NotNull ITimelinePlayListener pListener);

  /**
   * F�gt einen StopListener hinzu.
   * Dieser l�st aus, sobald die Timeline gestoppt wurde.
   *
   * @param pListener ITimelineStopListener
   */
  void addStopListener(@NotNull ITimelineStopListener pListener);

  /**
   * F�gt einen TickFinishListener hinzu.
   * Dieser l�st aus, sobald ein Tick, bzw. eine Interpolation berechnet wurde.
   *
   * @param pListener ITimelineTickFinishListener
   */
  void addTickFinishListener(@NotNull ITimelineTickFinishListener pListener);

  /**
   * Entfernt einen PauseListener.
   *
   * @param pListener ITimelineTickFinishListener
   */
  void removePauseListener(@NotNull ITimelinePauseListener pListener);

  /**
   * Entfernt einen PlayListener.
   *
   * @param pListener ITimelineTickFinishListener
   */
  void removePlayListener(@NotNull ITimelinePlayListener pListener);

  /**
   * Entfernt einen StopListener.
   *
   * @param pListener ITimelineTickFinishListener
   */
  void removeStopListener(@NotNull ITimelineStopListener pListener);

  /**
   * Entfernt einen TickListener.
   *
   * @param pListener ITimelineTickFinishListener
   */
  void removeTickListener(@NotNull ITimelineTickFinishListener pListener);
}
