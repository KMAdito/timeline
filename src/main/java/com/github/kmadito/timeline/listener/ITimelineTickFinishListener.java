package com.github.kmadito.timeline.listener;

/**
 * @author k.mifka, 17.01.2018
 */
public interface ITimelineTickFinishListener extends ITimelineActionListener
{
  void tickFinished();

  @Override
  default void fireAction()
  {
    tickFinished();
  }
}
