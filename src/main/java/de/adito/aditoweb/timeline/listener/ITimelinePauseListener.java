package de.adito.aditoweb.timeline.listener;

/**
 * @author k.mifka, 17.01.2018
 */
public interface ITimelinePauseListener extends ITimelineActionListener
{
  void paused();

  @Override
  default void fireAction()
  {
    paused();
  }
}