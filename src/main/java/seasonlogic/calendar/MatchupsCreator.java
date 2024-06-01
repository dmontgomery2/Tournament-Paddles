package seasonlogic.calendar;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.PlayoffSeries;

class MatchupsCreator {

  private static final Random RANDOM = new Random();

  public List<Matchup> getSeasonDayMatchups(List<PlayerProfile> computers, Human human) {
    List<PlayerProfile> computersCopy = new ArrayList<>(computers);
    Collections.shuffle(computersCopy);

    PlayerProfile opponentForHuman = computersCopy.get(0);
    computersCopy.remove(opponentForHuman);
    Matchup humanComputerMatchup = createHumanComputerMatchup(human,
        opponentForHuman);

    List<Matchup> computerComputerMatchups = createComputerComputerMatchups(
        computersCopy);

    List<Matchup> allMatchups = new ArrayList<>();
    allMatchups.add(humanComputerMatchup);
    allMatchups.addAll(computerComputerMatchups);
    Collections.shuffle(allMatchups);
    return allMatchups;
  }

  private Matchup createHumanComputerMatchup(Human human, PlayerProfile opponentForHuman) {
    return RANDOM.nextBoolean() ? new Matchup(human, opponentForHuman)
        : new Matchup(opponentForHuman, human);
  }

  private List<Matchup> createComputerComputerMatchups(List<PlayerProfile> computers) {
    List<Matchup> computerComputerMatchups = new ArrayList<>();
    for (int j = 0; j < computers.size() / 2; j++) {
      PlayerProfile computer1 = computers.get(j);
      PlayerProfile computer2 = computers.get(computers.size() - 1 - j);
      computerComputerMatchups.add(new Matchup(computer1, computer2));
    }
    return computerComputerMatchups;
  }

  public List<Matchup> getMatchupsForActiveSeries(List<PlayoffSeries> activeSeries) {
    return convertSeriesToMatchups(activeSeries);
  }

  private List<Matchup> convertSeriesToMatchups(List<PlayoffSeries> series) {
    return series.stream()
        .map(PlayoffSeries::convertToMatchup)
        .collect(toList());
  }

  public void scheduleInitialSeries(List<Day> days, List<PlayoffSeries> playoffSeries) {
    int gamesToWin = playoffSeries.get(0).getGamesToWin();
    List<Matchup> matchups = convertSeriesToMatchups(playoffSeries);

    for (int i = 0; i < gamesToWin; i++) {
      days.add(new Day(matchups));
    }
  }
}
