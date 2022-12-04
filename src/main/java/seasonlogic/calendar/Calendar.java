package seasonlogic.calendar;

import gameplay.GameplayResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.PlayoffSeries;
import seasonlogic.simulation.MatchSimulator;

public class Calendar {

  private static final MatchupsCreator MATCHUPS_CREATOR = new MatchupsCreator();
  private final List<Day> days;

  private int currentDaysIndex;

  private Calendar(Builder builder) {
    List<PlayerProfile> computers = builder.computers;
    Human human = builder.human;
    int numberOfGames = builder.numberOfGames;
    days = new ArrayList<>();
    for (int i = 0; i < numberOfGames; i++) {
      addSeasonDay(computers, human);
    }
  }


  public static class Builder {

    private List<PlayerProfile> computers;
    private Human human;
    private int numberOfGames;

    public Builder computers(List<PlayerProfile> computers) {
      this.computers = computers;
      return this;
    }

    public Builder human(Human human) {
      this.human = human;
      return this;
    }

    public Builder numberOfGames(int numberOfGames) {
      this.numberOfGames = numberOfGames;
      return this;
    }

    public Calendar build() {
      return new Calendar(this);
    }

  }

  private void addSeasonDay(List<PlayerProfile> computers, Human human) {
    days.add(new Day(MATCHUPS_CREATOR.getSeasonDayMatchups(computers, human)));
  }

  public void scheduleInitialSeries(List<PlayoffSeries> currentSeries) {
    MATCHUPS_CREATOR.scheduleInitialSeries(days, currentSeries);
  }

  public void addDayForActiveSeries(List<PlayoffSeries> activeSeries) {
    days.add(new Day(MATCHUPS_CREATOR.getMatchupsForActiveSeries(activeSeries)));
  }

  public boolean outOfDays() {
    return currentDaysIndex >= days.size();
  }

  public Optional<PlayerProfile> getOpponentForHuman() {
    return getCurrentDay().getOpponentForHuman();
  }

  public Optional<Matchup> getHumanComputerMatchup() {
    return getCurrentDay().getHumanComputerMatchup();
  }

  public Matchups getMatchups() {
    return getCurrentDay().getMatchups();
  }

  public void recordGameplayResult(GameplayResult gameplayResult) {
    getHumanComputerMatchup().ifPresent(matchup -> matchup.recordGameplayResult(gameplayResult));
  }

  public int getDayNumber() {
    return currentDaysIndex + 1;
  }

  public void advanceDay() {
    currentDaysIndex++;
  }

  public void simulateMatchups(int pointsToWin) {
    getCurrentDay().simulateMatchups(new MatchSimulator(pointsToWin));
  }

  private Day getCurrentDay() {
    return days.get(currentDaysIndex);
  }

  public List<String> getMatchupsStrings() {
    return getCurrentDay().getMatchupsStrings();
  }

  public List<String> getResultsStrings() {
    return getCurrentDay().getResultsStrings();
  }
}
