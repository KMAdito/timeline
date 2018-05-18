package com.github.kmadito.timeline;

import com.github.kmadito.timeline.definition.ITimeline;
import com.github.kmadito.timeline.definition.ITimelineConstants;
import com.github.kmadito.timeline.eventhandling.TimelineEventHandler;
import com.github.kmadito.timeline.timing.timer.TimelineTimer;
import com.github.kmadito.timeline.timing.timer.TimelineTimerTask;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author k.mifka, 10.04.2018
 */
public abstract class AbstractTimeline extends TimelineEventHandler implements ITimeline
{
  private float progress;
  private long startMillis;
  private boolean inverse;

  private final AtomicBoolean running = new AtomicBoolean();

  private int duration = ITimelineConstants.DEFAULT_DURATION;
  private int tickDelay = ITimelineConstants.DEFAULT_TICK_DELAY;

  @Override
  public void setDuration(int pMillis)
  {
    duration = pMillis;
  }

  @Override
  public int getDuration()
  {
    return duration;
  }

  @Override
  public int getTickDelay()
  {
    return tickDelay;
  }

  @Override
  public void setTickDelay(int pTickDelay)
  {
    tickDelay = pTickDelay;
    _updateTimer();
  }

  @Override
  public void playForward()
  {
    if (!running.get())
    {
      if (progress == 1)
        progress = 0;

      inverse = false;
      _play();
    }
  }

  @Override
  public void playBackward()
  {
    if (!running.get())
    {
      if (progress == 0)
        progress = 1;

      inverse = true;
      _play();
    }
  }

  @Override
  public void pause()
  {
    _pause();
  }

  @Override
  public void stop()
  {
    progress = 0;
    _stop();
  }

  @Override
  public boolean isRunning()
  {
    return running.get();
  }

  @Override
  public float getProgress()
  {
    return progress;
  }

  @Override
  public void setProgress(float pProgress)
  {
    if (!running.get())
    {
      progress = pProgress;
      updateProgress(progress);
    }
  }

  protected abstract void updateProgress(float pProgress);

  private void _play()
  {
    running.set(true);
    _updateTimer();

    firePlayEvent();
  }

  private void _pause()
  {
    if (running.getAndSet(false))
      firePauseEvent();
  }

  private void _stop()
  {
    if (running.getAndSet(false))
    {
      inverse = false;
      _updateTimer();

      fireStopEvent();
    }
  }

  private void _updateTimer()
  {
    if (running.get())
    {
      float tempProgress = inverse ? 1 - progress : progress;
      startMillis = System.currentTimeMillis() - (int) Math.round((double) duration * tempProgress);

      TimelineTimer.schedule(new _TimelineTask(), tickDelay);
    }
  }

  private class _TimelineTask extends TimelineTimerTask
  {
    @Override
    public void run()
    {
      if (running.get())
      {
        long currMillis = System.currentTimeMillis();
        progress = (float) ((double) (currMillis - startMillis) / (double) duration);
        progress = Math.min(progress, 1);
        progress = inverse ? 1 - progress : progress;

        updateProgress(progress);

        fireTickFinishEvent();

        if (currMillis - startMillis >= duration)
          _stop();

        if (!running.get())
          cancel();
      }
      else
        cancel();
    }
  }
}
