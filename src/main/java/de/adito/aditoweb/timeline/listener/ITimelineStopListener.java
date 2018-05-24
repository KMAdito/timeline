package de.adito.aditoweb.timeline.listener;

/**
 * PauseListener: löst aus, sobald die Timeline gestoppt wurde.
 *
 * @author k.mifka, 17.01.2018
 */
public interface ITimelineStopListener extends ITimelineActionListener
{
  /**
   * Timeline wurde gestoppt
   */
  void stopped();

  @Override
  default void fireAction()
  {
    stopped();
  }
}
