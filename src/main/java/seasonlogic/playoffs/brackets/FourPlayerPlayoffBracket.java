package seasonlogic.playoffs.brackets;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static java.util.stream.Collectors.toList;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import seasonlogic.calendar.Calendar;
import seasonlogic.calendar.Matchup;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.PlayoffBracket;
import seasonlogic.playoffs.PlayoffSeries;
import seasonlogic.playoffs.brackets.drawers.FourPlayerPlayoffBracketDrawer;
import settings.SeasonSettings;

public class FourPlayerPlayoffBracket implements PlayoffBracket {

  private final Map<Integer, Supplier<Integer>> CURRENT_ROUND_TO_ROUND_LENGTH_SUPPLIER = ofEntries(
      entry(1, this::get1stRoundLength),
      entry(2, this::get2ndRoundLength)
  );

  private final Map<Integer, Supplier<List<PlayoffSeries>>> CURRENT_ROUND_TO_CURRENT_SERIES_SUPPLIER = ofEntries(
      entry(1, this::get1stRoundSeries),
      entry(2, this::get2ndRoundSeries)
  );

  private final PlayoffBracketDrawer playoffBracketDrawer;

  private int currentRound;

  private PlayerProfile champion;

  private final Calendar calendar;

  private boolean justAdvancedRound;

  private final List<PlayerProfile> playoffParticipants;

  private final List<PlayoffSeries> firstRoundSeries;
  private final List<PlayoffSeries> secondRoundSeries;

  private final SeasonSettings settings;

  public FourPlayerPlayoffBracket(Calendar calendar, List<PlayerProfile> playoffParticipants,
      SeasonSettings settings) {
    firstRoundSeries = new ArrayList<>();
    secondRoundSeries = new ArrayList<>();
    playoffBracketDrawer = FourPlayerPlayoffBracketDrawer.builder()
        .firstRoundSeries(firstRoundSeries)
        .secondRoundSeries(secondRoundSeries)
        .build();
    currentRound = 1;
    this.settings = settings;
    this.calendar = calendar;
    Collections.sort(playoffParticipants);
    this.playoffParticipants = playoffParticipants;
    set1stRoundSeries();
  }

  private int getCurrentRoundLength() {
    return getFromMap(CURRENT_ROUND_TO_ROUND_LENGTH_SUPPLIER);
  }

  @Override
  public List<PlayoffSeries> getCurrentSeries() {
    return getFromMap(CURRENT_ROUND_TO_CURRENT_SERIES_SUPPLIER);
  }

  private <T> T getFromMap(Map<Integer, Supplier<T>> map) {
    return map
        .get(currentRound)
        .get();
  }

  private List<PlayoffSeries> get1stRoundSeries() {
    return firstRoundSeries;
  }

  private int get1stRoundLength() {
    return settings.getRound1();
  }

  private List<PlayoffSeries> get2ndRoundSeries() {
    return secondRoundSeries;
  }

  private int get2ndRoundLength() {
    return settings.getRound2();
  }

  @Override
  public boolean justAdvancedRound() {
    return justAdvancedRound;
  }

  @Override
  public void setJustAdvancedRound(boolean justAdvancedRound) {
    this.justAdvancedRound = justAdvancedRound;
  }

  @Override
  public void advance() {
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

  private List<PlayoffSeries> getActiveSeries() {
    return getCurrentSeries().stream()
        .filter(PlayoffSeries::isActive)
        .collect(toList());
  }

  private void advanceRound() {
    setNextRound();
    justAdvancedRound = true;
  }

  private void set1stRoundSeries() {
    for (int i = 0; i < playoffParticipants.size() / 2; i++) {
      PlayerProfile player1 = playoffParticipants.get(i);
      PlayerProfile player2 = playoffParticipants.get(playoffParticipants.size() - 1 - i);
      firstRoundSeries.add(new PlayoffSeries(player1, player2, getCurrentRoundLength()));
    }
  }

  private void setNextRound() {
    if (getCurrentSeries().size() == 1) {
      champion = getCurrentSeries().get(0).getWinner();
      return;
    }
    List<PlayoffSeries> previousSeries = getCurrentSeries();
    currentRound++;
    List<PlayoffSeries> currentSeries = getCurrentSeries();
    for (int i = 0; i < previousSeries.size() / 2; i++) {
      PlayerProfile player1 = previousSeries.get(i).getWinner();
      PlayerProfile player2 = previousSeries.get(previousSeries.size() - 1 - i).getWinner();
      currentSeries.add(new PlayoffSeries(player1, player2, getCurrentRoundLength()));
    }
  }

  @Override
  public void drawSelf(Graphics g) {
    playoffBracketDrawer.draw(g);
  }

  @Override
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

  @Override
  public void recordMatchup(Matchup matchup) {
    PlayoffSeries playoffSeries = getCurrentSeries().stream()
        .filter(s -> s.convertToMatchup().getPlayer1().equals(matchup.getPlayer1()))
        .findFirst()
        .get();
    playoffSeries.giveWinTo(matchup.getWinner());
  }

  @Override
  public PlayerProfile getChampion() {
    return champion;
  }

}
