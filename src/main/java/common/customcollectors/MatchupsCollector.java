package common.customcollectors;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import seasonlogic.calendar.Matchup;
import seasonlogic.calendar.Matchups;

class MatchupsCollector implements Collector<Matchup, Matchups, Matchups> {

  private static final MatchupsCollector INSTANCE = new MatchupsCollector();

  private MatchupsCollector() {

  }

  static MatchupsCollector getInstance() {
    return INSTANCE;
  }

  @Override
  public Supplier<Matchups> supplier() {
    return Matchups::new;
  }

  @Override
  public BiConsumer<Matchups, Matchup> accumulator() {
    return Matchups::add;
  }

  @Override
  public BinaryOperator<Matchups> combiner() {
    return (matchups1, matchups2) -> {
      matchups1.addAll(matchups2);
      return matchups1;
    };
  }

  @Override
  public Function<Matchups, Matchups> finisher() {
    return Function.identity();
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.emptySet();
  }

}


