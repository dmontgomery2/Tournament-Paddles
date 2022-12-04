package seasonlogic.calendar;

import static common.customcollectors.CustomCollectors.toMatchups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.PlayoffSeries;

class MatchupsCreator {

  private static final Random RANDOM = new Random();

  public Matchups getSeasonDayMatchups(List<PlayerProfile> computers, Human human) {
    List<PlayerProfile> computersCopy = new ArrayList<>(computers);
    Collections.shuffle(computersCopy);

    PlayerProfile opponentForHuman = computersCopy.get(0);
    computersCopy.remove(opponentForHuman);
    Matchup humanComputerMatchup = createHumanComputerMatchup(human,
        opponentForHuman);

    Matchups computerComputerMatchups = createComputerComputerMatchups(
        computersCopy);

    Matchups allMatchups = new Matchups();
    allMatchups.add(humanComputerMatchup);
    allMatchups.addAll(computerComputerMatchups);
    allMatchups.shuffle();
    return allMatchups;
  }

  private Matchup createHumanComputerMatchup(Human human, PlayerProfile opponentForHuman) {
    return RANDOM.nextBoolean() ? new Matchup(human, opponentForHuman)
        : new Matchup(opponentForHuman, human);
  }

  private Matchups createComputerComputerMatchups(List<PlayerProfile> computers) {
    Matchups computerComputerMatchups = new Matchups();
    for (int j = 0; j < computers.size() / 2; j++) {
      PlayerProfile computer1 = computers.get(j);
      PlayerProfile computer2 = computers.get(computers.size() - 1 - j);
      computerComputerMatchups.add(new Matchup(computer1, computer2));
    }
    return computerComputerMatchups;
  }

  public Matchups getMatchupsForActiveSeries(List<PlayoffSeries> activeSeries) {
    return convertSeriesToMatchups(activeSeries);
  }

  private Matchups convertSeriesToMatchups(List<PlayoffSeries> series) {
    return series.stream()
        .map(PlayoffSeries::convertToMatchup)
        .collect(toMatchups());
  }

  public void scheduleInitialSeries(List<Day> days, List<PlayoffSeries> playoffSeries) {
    int gamesToWin = playoffSeries.get(0).getGamesToWin();
    Matchups matchups = convertSeriesToMatchups(playoffSeries);

    for (int i = 0; i < gamesToWin; i++) {
      days.add(new Day(matchups));
    }
  }
}
