package de.adito.aditoweb.timeline.values.definition;

import de.adito.aditoweb.timeline.definition.ITimelineRunner;
import de.adito.aditoweb.timeline.timing.ITimelineBezier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author k.mifka, 10.01.2018
 */
public interface ITimelineValue<T>
{
  @NotNull
  T getStart();

  @NotNull
  T getEnd();

  @NotNull
  T get();

  void setValue(@NotNull T pValue);

  void setStart(@NotNull T pStart);

  void setEnd(@NotNull T pEnd);

  void setIn(@Nullable Float pProgress);

  void setOut(@Nullable Float pProgress);

  @Nullable
  Float getIn();

  @Nullable
  Float getOut();

  void setTiming(@Nullable ITimelineBezier pTiming);

  @Nullable
  ITimelineBezier getTiming();

  void registerConsumer(@NotNull Consumer<T> pListener);

  void unregisterConsumer(@NotNull Consumer<T> pListener);

  void setProgress(ITimelineBezier pDefaultTiming, ITimelineRunner pRunner, float pProgress);
}
