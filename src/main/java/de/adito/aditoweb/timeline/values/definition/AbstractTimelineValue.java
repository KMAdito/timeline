package de.adito.aditoweb.timeline.values.definition;

import de.adito.aditoweb.timeline.timing.ITimelineBezier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author k.mifka, 10.01.2018
 */
public abstract class AbstractTimelineValue<T> implements ITimelineValue<T>
{
  private ITimelineBezier timing;

  private T start;
  private T end;
  private T value;

  private Float in;
  private Float out;

  private final List<Consumer<T>> consumers = new ArrayList<>();

  public AbstractTimelineValue(T pStart, T pEnd)
  {
    start = pStart;
    end = pEnd;
    value = start;
  }

  public AbstractTimelineValue(T pStart, T pEnd, Consumer<T> pConsumer)
  {
    this(pStart, pEnd);
    registerConsumer(pConsumer);
  }

  public AbstractTimelineValue(@NotNull T pStart, @NotNull T pEnd, @Nullable ITimelineBezier pTiming)
  {
    this(pStart, pEnd);
    timing = pTiming;
  }

  public AbstractTimelineValue(T pStart, T pEnd, ITimelineBezier pTiming, Consumer<T> pConsumer)
  {
    this(pStart, pEnd, pConsumer);
    timing = pTiming;
  }

  @NotNull
  @Override
  public T getStart()
  {
    return start;
  }

  @NotNull
  @Override
  public T getEnd()
  {
    return end;
  }

  @NotNull
  @Override
  public T get()
  {
    return value;
  }

  @Override
  public void setStart(@NotNull T pStart)
  {
    start = pStart;
  }

  @Override
  public void setEnd(@NotNull T pEnd)
  {
    end = pEnd;
  }

  @Override
  public void registerConsumer(@NotNull Consumer<T> pListener)
  {
    synchronized (consumers)
    {
      consumers.add(pListener);
    }
  }

  @Override
  public void unregisterConsumer(@NotNull Consumer<T> pListener)
  {
    synchronized (consumers)
    {
      consumers.remove(pListener);
    }
  }

  @Override
  public void setIn(@Nullable Float pProgress)
  {
    in = pProgress;
  }

  @Override
  public void setOut(@Nullable Float pProgress)
  {
    out = pProgress;
  }

  @Override
  public Float getIn()
  {
    return in;
  }

  @Override
  public Float getOut()
  {
    return out;
  }

  @Override
  public void setTiming(@Nullable ITimelineBezier pTiming)
  {
    timing = pTiming;
  }

  @Override
  @Nullable
  public ITimelineBezier getTiming()
  {
    return timing;
  }

  public void setValue(@NotNull T pValue)
  {
    if(!Objects.equals(value, pValue))
    {
      value = pValue;

      synchronized (consumers)
      {
        for (Consumer<T> listener : consumers)
          listener.accept(value);
      }
    }
  }

  @Override
  public void setProgress(ITimelineBezier pDefaultTiming, float pProgress)
  {
    ITimelineBezier timingBezier = getTiming();

    if (timingBezier == null)
      timingBezier = pDefaultTiming;

    float inOutProgress = _calculateInOutProgress(pProgress);

    T value = calculateValue(timingBezier.calculateY(inOutProgress));

    setValue(value);
  }

  protected abstract T calculateValue(float pProgress);

  private float _calculateInOutProgress(float pProgress)
  {
    Float i = getIn();
    Float o = getOut();

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
