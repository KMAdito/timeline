package de.adito.aditoweb.timeline.values.definition;

import de.adito.aditoweb.timeline.definition.ITimelineRunner;
import de.adito.aditoweb.timeline.timing.ITimelineBezier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Abstrakte TimelineValue
 *
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

  private Thread valueThread;

  /**
   * Konstruktor
   *
   * @param pStart Startwert
   * @param pEnd Endwert
   */
  public AbstractTimelineValue(T pStart, T pEnd)
  {
    start = pStart;
    end = pEnd;
    value = start;
  }

  /**
   * Konstruktor
   *
   * @param pStart Startwert
   * @param pEnd Endwert
   * @param pConsumer Consumer für Wertänderungen
   */
  public AbstractTimelineValue(T pStart, T pEnd, Consumer<T> pConsumer)
  {
    this(pStart, pEnd);
    registerConsumer(pConsumer);
  }

  /**
   * Konstruktor
   *
   * @param pStart Startwert
   * @param pEnd Endwert
   * @param pTiming Das Timing für den Wert (Bei null wird das Standardtiming der Timeline verwendet)
   */
  public AbstractTimelineValue(@NotNull T pStart, @NotNull T pEnd, @Nullable ITimelineBezier pTiming)
  {
    this(pStart, pEnd);
    timing = pTiming;
  }

  /**
   * Konstruktor
   *
   * @param pStart Startwert
   * @param pEnd Endwert
   * @param pTiming Das Timing für den Wert (Bei null wird das Standardtiming der Timeline verwendet)
   * @param pConsumer Consumer für Wertänderungen
   */
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
  public void registerConsumer(@NotNull Consumer<T> pConsumer)
  {
    synchronized (consumers)
    {
      consumers.add(pConsumer);
    }
  }

  @Override
  public void unregisterConsumer(@NotNull Consumer<T> pConsumer)
  {
    synchronized (consumers)
    {
      consumers.remove(pConsumer);
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

  @Override
  public void setValue(@NotNull T pValue)
  {
    _setValue(pValue, null);
  }

  @Override
  public void updateValue(ITimelineBezier pDefaultTiming, ITimelineRunner pRunner, float pProgress)
  {
    if(valueThread == null || !valueThread.isAlive() || pProgress == 1 || pProgress == 0)
      _startValueThread(pDefaultTiming, pRunner, pProgress);
  }

  /**
   * Berechnet seinen Wert in Abhängigket vom Fortschritt
   *
   * ACHTUNG: Hier muss das Timing NICHT berücksichtigt werden.
   *
   * @param pProgress Frotschritt in Prozent
   * @return berechneter Wert
   */
  protected abstract T calculateValue(float pProgress);

  /**
   * Setzt seinen Wert und informiert alle Consumer
   *
   * @param pValue zu setztenden Wert
   * @param pRunner Thread für Wertänderungen
   */
  private void _setValue(@NotNull T pValue, @Nullable ITimelineRunner pRunner)
  {
    if(!Objects.equals(value, pValue))
    {
      value = pValue;

      synchronized (consumers)
      {
        for (Consumer<T> listener : consumers)
        {
          if (pRunner != null)
            pRunner.runValueChange(() -> listener.accept(value));
          else
            listener.accept(value);
        }
      }
    }
  }

  /**
   * Wartet auf den ValueThread
   */
  private void _waitForValueThread()
  {
    while (valueThread != null && valueThread.isAlive())
    {
      try
      {
        valueThread.join();
      }
      catch (InterruptedException pE)
      {
        //egal da while
      }
    }
  }

  /**
   * Startet einen Thread zum aktualisieren des Werts
   *
   * @param pDefaultTiming Standard-Timing
   * @param pRunner ITimelineRunner
   * @param pProgress Frotschritt in Prozent
   */
  private void _startValueThread(ITimelineBezier pDefaultTiming, ITimelineRunner pRunner, float pProgress)
  {
    _waitForValueThread();
    valueThread = new Thread(() -> _updateValue(pDefaultTiming, pRunner, pProgress));
    valueThread.start();
  }

  /**
   * Aktualisiert den Wert in Abhängigkeit des Fortschritts und Timings
   *
   * @param pDefaultTiming Standard-Timing
   * @param pRunner ITimelineRunner
   * @param pProgress Frotschritt in Prozent
   */
  private void _updateValue(ITimelineBezier pDefaultTiming, ITimelineRunner pRunner, float pProgress)
  {
    ITimelineBezier timingBezier = getTiming();

    if (timingBezier == null)
      timingBezier = pDefaultTiming;

    float inOutProgress = _calculateInOutProgress(pProgress);

    T value = calculateValue(timingBezier.calculateY(inOutProgress));

    _setValue(value, pRunner);
  }

  /**
   * Berechnet den Fortschritt in Abhängigkeit der In- und Out-Angaben
   *
   * @param pProgress Fortschritt
   * @return Fortschritt in Abhängigkeit der In- und Out-Angaben
   */
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
