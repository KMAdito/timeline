package de.adito.aditoweb.timeline;

import de.adito.aditoweb.timeline.definition.ITimelineConstants;
import de.adito.aditoweb.timeline.definition.ITimelineRunner;
import de.adito.aditoweb.timeline.timing.ITimelineBezier;
import de.adito.aditoweb.timeline.values.definition.ITimelineValue;
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
    synchronized (values)
    {
      for (ITimelineValue<?> value : values)
        value.setProgress(defaultTiming, runner, pProgress);
    }
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
}
