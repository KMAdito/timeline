package de.adito.aditoweb.timeline.listener;

/**
 * PauseListener: löst aus, sobald ein Tick, bzw. eine Interpolation berechnet wurde.
 *
 * @author k.mifka, 17.01.2018
 */
public interface ITimelineTickFinishListener extends ITimelineActionListener
{
  /**
   * ein Tick, bzw. eine Interpolation wurde berechnet
   */
  void tickFinished();

  @Override
  default void fireAction()
  {
    tickFinished();
  }
}
