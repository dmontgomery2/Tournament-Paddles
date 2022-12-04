package seasonlogic.playoffs;

import java.util.List;
import seasonlogic.SeasonStatus;
import seasonlogic.calendar.Matchup;
import seasonlogic.models.PlayerProfile;

public interface PlayoffBracket extends SeasonStatus {

  List<PlayoffSeries> getCurrentSeries();

  boolean justAdvancedRound();

  void setJustAdvancedRound(boolean justAdvancedRound);

  void advance();

  boolean isHumanActive();

  void recordMatchup(Matchup matchup);

  PlayerProfile getChampion();
}
