package seasonlogic.playoffs;

import gameplay.GameplayResult;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import seasonlogic.Season;
import seasonlogic.SeasonStatus;
import seasonlogic.calendar.Calendar;
import seasonlogic.calendar.Matchup;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.playoffbracket.PlayoffBracket;
import settings.SeasonSettings;

public class Playoffs implements Season, Serializable {

  private final Calendar calendar;
  private final SeasonSettings settings;
  private final PlayoffBracket playoffBracket;
  private PlayerProfile champion;

  public Playoffs(Calendar calendar, List<PlayerProfile> players, SeasonSettings settings) {
    this.calendar = calendar;
    for (int i = 0; i < players.size(); i++) {
      players.get(i).setSeed(i + 1);
    }
    this.settings = settings;
    playoffBracket = new PlayoffBracket(calendar, players, settings);
    calendar.scheduleInitialSeries(playoffBracket.getCurrentSeries());
  }

  @Override
  public Season advance() {
    recordResults();
    calendar.advanceDay();
    playoffBracket.advance();
    if (playoffBracket.getChampion() != null) {
      champion = playoffBracket.getChampion();
      return this;
    }
    if (playoffBracket.justAdvancedRound()) {
      playoffBracket.setJustAdvancedRound(false);
      calendar.scheduleInitialSeries(playoffBracket.getCurrentSeries());
    }
    return this;
  }

  private void recordResults() {
    calendar.getMatchups().forEach(matchup -> playoffBracket.recordMatchup(matchup));
  }

  @Override
  public void simulateMatchups() {
    calendar.simulateMatchups(settings.getPointsToWin());
  }

  @Override
  public void recordGameplayResult(GameplayResult gameplayResult) {
    calendar.recordGameplayResult(gameplayResult);
  }

  @Override
  public int getPointsToWin() {
    return settings.getPointsToWin();
  }

  @Override
  public Optional<PlayerProfile> getOpponentForHuman() {
    return calendar.getOpponentForHuman();
  }

  @Override
  public String getDay() {
    return Integer.toString(calendar.getDayNumber());
  }

  @Override
  public SeasonStatus getStatus() {
    return playoffBracket;
  }

  @Override
  public List<Matchup> getMatchups() {
    return calendar.getMatchups();
  }

  @Override
  public int getPaddleSize() {
    return settings.getPaddleSize();
  }

  @Override
  public boolean isHumanActive() {
    return playoffBracket.isHumanActive();
  }

  @Override
  public PlayerProfile getChampion() {
    return champion;
  }

  @Override
  public boolean isMaximumDifficulty() {
    return settings.isMaximumDifficulty();
  }
}
