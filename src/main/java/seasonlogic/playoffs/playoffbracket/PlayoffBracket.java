package seasonlogic.playoffs.playoffbracket;

import static java.util.stream.Collectors.toList;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import seasonlogic.SeasonStatus;
import seasonlogic.calendar.Calendar;
import seasonlogic.calendar.Matchup;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.PlayoffSeries;
import settings.SeasonSettings;

public class PlayoffBracket implements SeasonStatus, Serializable {

  private final PlayoffBracketDrawer playoffBracketDrawer;

  private int currentRoundIndex;

  private PlayerProfile champion;

  private final Calendar calendar;

  private boolean justAdvancedRound;

  private final SeasonSettings settings;

  private final List<List<PlayoffSeries>> playoffSeries;

  public PlayoffBracket(Calendar calendar, List<PlayerProfile> playoffParticipants,
      SeasonSettings settings) {
    this.calendar = calendar;
    this.settings = settings;
    playoffSeries = getPlayoffSeries(playoffParticipants);
    playoffBracketDrawer = PlayoffBracketDrawerFactory.getPlayoffBracketDrawer(playoffSeries);

  }

  private List<List<PlayoffSeries>> getPlayoffSeries(List<PlayerProfile> playoffParticipants) {
    List<List<PlayoffSeries>> series = new ArrayList<>();
    int roundIndex = 0;
    List<PlayoffSeries> firstRound = new ArrayList<>();
    for (int i = 0; i < playoffParticipants.size() / 2; i++) {
      PlayerProfile player1 = playoffParticipants.get(i);
      PlayerProfile player2 = playoffParticipants.get(playoffParticipants.size() - 1 - i);
      PlayoffSeries newSeries = new PlayoffSeries(i, getRoundLength(roundIndex));
      newSeries.setPlayer1(player1);
      newSeries.setPlayer2(player2);
      firstRound.add(newSeries);
    }
    series.add(firstRound);
    while (series.get(series.size() - 1).size() > 1) {
      roundIndex++;
      List<PlayoffSeries> newRound = new ArrayList<>();
      for (int i = 0; i < series.get(series.size() - 1).size() / 2; i++) {
        newRound.add(new PlayoffSeries(i, getRoundLength(roundIndex)));
      }
      series.add(newRound);
    }
    return series;
  }

  private int getRoundLength(int roundNumber) {
    switch (roundNumber) {
      case 1:
        return settings.getRound1();
      case 2:
        return settings.getRound2();
      default:
        return settings.getRound3();
    }
  }

  public List<PlayoffSeries> getCurrentSeries() {
    return playoffSeries.get(currentRoundIndex);
  }

  public boolean justAdvancedRound() {
    return justAdvancedRound;
  }

  public void setJustAdvancedRound(boolean justAdvancedRound) {
    this.justAdvancedRound = justAdvancedRound;
  }

  public void advance() {
    if (currentRoundIndex < playoffSeries.size() - 1) {
      checkFinishedSeries();
    }

    if (atLeastOneSeriesActive()) {
      advanceSeries();
    } else {
      advanceRound();
    }
  }

  private boolean atLeastOneSeriesActive() {
    return getCurrentSeries().stream()
        .anyMatch(PlayoffSeries::isActive);
  }

  private void advanceSeries() {
    if (calendar.outOfDays()) {
      calendar.addDayForActiveSeries(getActiveSeries());
    }
    calendar.simulateMatchups(settings.getPointsToWin());
  }

  private void checkFinishedSeries() {
    List<PlayoffSeries> nextRound = playoffSeries.get(currentRoundIndex + 1);
    playoffSeries.get(currentRoundIndex)
        .stream()
        .filter(PlayoffSeries::isFinished)
        .filter(PlayoffSeries::isNotAdvanced)
        .forEach(series -> advanceSeries(series, nextRound));
  }

  private void advanceSeries(PlayoffSeries playoffSeries, List<PlayoffSeries> nextRound) {
    int newSeriesIndex = playoffSeries.getSeriesIndex() / 2;
    boolean isPlayer1 = playoffSeries.getSeriesIndex() % 2 == 0;
    if (isPlayer1) {
      nextRound.get(newSeriesIndex).setPlayer1(playoffSeries.getWinner());
    } else {
      nextRound.get(newSeriesIndex).setPlayer2(playoffSeries.getWinner());
    }
  }

  private List<PlayoffSeries> getActiveSeries() {
    return getCurrentSeries().stream()
        .filter(PlayoffSeries::isActive)
        .collect(toList());
  }

  private void advanceRound() {
    if (currentRoundIndex == playoffSeries.size() - 1) {
      champion = playoffSeries.get(currentRoundIndex).get(0).getWinner();
      return;
    }
    currentRoundIndex++;
    justAdvancedRound = true;
  }

  @Override
  public void drawSelf(Graphics g) {
    playoffBracketDrawer.draw(g);
  }

  public boolean isHumanActive() {
    return getHumanComputerSeries()
        .map(PlayoffSeries::isActive)
        .orElse(false);
  }

  private Optional<PlayoffSeries> getHumanComputerSeries() {
    return getCurrentSeries()
        .stream()
        .filter(PlayoffSeries::hasHuman)
        .findAny();
  }

  public void recordMatchup(Matchup matchup) {
    PlayoffSeries playoffSeries = getCurrentSeries().stream()
        .filter(s -> s.convertToMatchup().getPlayer1().equals(matchup.getPlayer1()))
        .findFirst()
        .get();
    playoffSeries.giveWinTo(matchup.getWinner());
  }

  public PlayerProfile getChampion() {
    return champion;
  }

}
