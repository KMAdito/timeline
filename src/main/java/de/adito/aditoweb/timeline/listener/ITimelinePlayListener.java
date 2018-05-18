package de.adito.aditoweb.timeline.listener;

/**
 * @author k.mifka, 17.01.2018
 */
public interface ITimelinePlayListener extends ITimelineActionListener
{
  void played();

  @Override
  default void fireAction()
  {
    played();
  }
}
