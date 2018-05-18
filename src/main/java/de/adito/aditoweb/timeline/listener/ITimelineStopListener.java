package de.adito.aditoweb.timeline.listener;

/**
 * @author k.mifka, 17.01.2018
 */
public interface ITimelineStopListener extends ITimelineActionListener
{
  void stopped();

  @Override
  default void fireAction()
  {
    stopped();
  }
}
