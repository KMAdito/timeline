package com.github.kmadito.timeline;

import com.github.kmadito.timeline.definition.*;
import com.github.kmadito.timeline.timing.ITimelineBezier;
import com.github.kmadito.timeline.values.definition.ITimelineValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author k.mifka, 10.01.2018
 */
public class Timeline extends AbstractTimeline
{
  private final List<ITimelineValue<?>> values = new ArrayList<>();

  private ITimelineRunner runner;
  private ITimelineBezier defaultTiming;

  public Timeline()
  {
    this(null);
  }

  public Timeline(@Nullable ITimelineBezier pDefaultTiming)
  {
    defaultTiming = pDefaultTiming;

    if(defaultTiming == null)
      defaultTiming = ITimelineConstants.DEFAULT_BEZIER;
  }

  @Override
  public void addValue(@NotNull ITimelineValue pAdjustValue)
  {
    synchronized (values)
    {
      values.add(pAdjustValue);
    }
  }

  @Override
  public void removeValue(@NotNull ITimelineValue pAdjustValue)
  {
    synchronized (values)
    {
      values.remove(pAdjustValue);
    }
  }

  @Override
  public void removeAll()
  {
    synchronized (values)
    {
      values.clear();
    }
  }

  @NotNull
  @Override
  public ITimelineValue[] getValues()
  {
    return values.toArray(new ITimelineValue[0]);
  }

  @Override
  protected void updateProgress(float pProgress)
  {
    if (runner != null)
      runner.runValueFade(() -> _updateValues(pProgress));
    else
      _updateValues(pProgress);
  }

  @Override
  public void setRunner(@Nullable ITimelineRunner pFadeRunner)
  {
    runner = pFadeRunner;
  }

  @Override
  public void setDefaultTiming(@NotNull ITimelineBezier pDefaultTiming)
  {
    defaultTiming = pDefaultTiming;
  }

  @Override
  @NotNull
  public ITimelineBezier getDefaultTiming()
  {
    return defaultTiming;
  }

  private <T> void _calculateValue(ITimelineValue<T> pValue, float pProgress)
  {
    ITimelineBezier timingBezier = pValue.getTiming();

    if (timingBezier == null)
      timingBezier = defaultTiming;

    T value = pValue.calculateValue(timingBezier.calculateY(pProgress));
    pValue.setValue(value);
  }

  private void _updateValues(float pProgress)
  {
    synchronized (values)
    {
      for (ITimelineValue<?> value : values)
      {
        float trimmedProgress = _calculateInOutProgress(value, pProgress);
        _calculateValue(value, trimmedProgress);
      }
    }
  }

  private float _calculateInOutProgress(ITimelineValue pValue, float pProgress)
  {
    Float i = pValue.getIn();
    Float o = pValue.getOut();

    if(o == null && i == null)
      return pProgress;

    if (o == null)
      o = 1F;

    if (i == null)
      i = 0F;

    if (i > pProgress)
      return 0F;

    if (o < pProgress)
      return 1F;

    return (pProgress - i) / (o - i);
  }
}
