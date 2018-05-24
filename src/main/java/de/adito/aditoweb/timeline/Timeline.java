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
 * Eine Timeline ist ein Zeitstrahl, welcher ITimelineValues aufnhemen kann
 * und vorwiegend zur Realisierung von Animationen gedacht ist.
 *
 * Sie nimmt eine Dauer an (setDuration()), welche angibt, wie lange es dauert,
 * alle hinzugefügten ITimelineValues vom Startwert bis zu Endwert zu interpolieren.
 *
 * Die Interpolation lässt sich mithilfe von Bezier-Kurven timen (setDefaultTiming())
 *
 * @author k.mifka, 10.01.2018
 */
public class Timeline extends AbstractTimeline
{
  private final List<ITimelineValue<?>> values = new ArrayList<>();

  private ITimelineRunner runner;
  private ITimelineBezier defaultTiming;

  /**
   * Konstruktor
   */
  public Timeline()
  {
    this(null);
  }

  /**
   * Konstruktor
   *
   * @param pDefaultTiming Standard-Timing-Bezierkurve
   */
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
  public List<ITimelineValue> getValues()
  {
    return new ArrayList<>(values);
  }

  @Override
  protected void updateProgress(float pProgress)
  {
    synchronized (values)
    {
      for (ITimelineValue<?> value : values)
        value.updateValue(defaultTiming, runner, pProgress);
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
