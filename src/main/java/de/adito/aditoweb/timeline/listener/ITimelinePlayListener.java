package de.adito.aditoweb.timeline.listener;

/**
 * PauseListener: l�st aus, sobald die Timeline gestartet wurde.
 *
 * @author k.mifka, 17.01.2018
 */
public interface ITimelinePlayListener extends ITimelineActionListener
{
  /**
   * Timeline wurde gestartet
   */
  void played();

  @Override
  default void fireAction()
  {
    played();
  }
}
