package de.adito.aditoweb.timeline.listener;

/**
 * PauseListener: l�st aus, sobald die Timeline pausiert wurde.
 *
 * @author k.mifka, 17.01.2018
 */
public interface ITimelinePauseListener extends ITimelineActionListener
{
  /**
   * Timeline wurde pausiert
   */
  void paused();

  @Override
  default void fireAction()
  {
    paused();
  }
}
