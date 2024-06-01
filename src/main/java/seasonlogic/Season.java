package seasonlogic;

import gameplay.GameplayResult;
import java.util.List;
import java.util.Optional;
import seasonlogic.calendar.Matchup;
import seasonlogic.models.PlayerProfile;

public interface Season {

  int getPointsToWin();

  List<Matchup> getMatchups();

  int getPaddleSize();

  String getDay();

  Optional<PlayerProfile> getOpponentForHuman();

  SeasonStatus getStatus();

  Season advance();

  void simulateMatchups();

  void recordGameplayResult(GameplayResult gameplayResult);

  boolean isHumanActive();

  PlayerProfile getChampion();

  boolean isMaximumDifficulty();
}
