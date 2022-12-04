package seasonlogic;

import gameplay.GameplayResult;
import seasonlogic.models.PlayerProfile;

import java.util.List;
import java.util.Optional;

public interface Season {
    int getPointsToWin();
    int getPaddleSize();
    String getDay();
    Optional<PlayerProfile> getOpponentForHuman();
    SeasonStatus getStatus();
    Season advance();
    void simulateMatchups();
    void recordGameplayResult(GameplayResult gameplayResult);

    List<String> getResultsStrings();
    List<String> getMatchupsStrings();





    boolean isHumanActive();
    PlayerProfile getChampion();
}
