package common.customcollectors;

import java.util.stream.Collector;
import seasonlogic.calendar.Matchup;
import seasonlogic.calendar.Matchups;

public class CustomCollectors {
  public static Collector<Matchup, Matchups, Matchups> toMatchups(){
    return MatchupsCollector.getInstance();
  }

}
