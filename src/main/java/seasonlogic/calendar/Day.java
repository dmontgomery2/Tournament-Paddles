package seasonlogic.calendar;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;
import seasonlogic.simulation.MatchSimulator;

public class Day implements Serializable {

  private final List<Matchup> matchups;

  private final List<Matchup> computerComputerMatchups;

  public Day(List<Matchup> matchups) {
    this.matchups = matchups;
    computerComputerMatchups = matchups.stream()
        .filter(Matchup::doesNotContainHuman)
        .collect(toList());
  }

  public void simulateMatchups(MatchSimulator matchSimulator) {
    matchSimulator.simulateMatches(computerComputerMatchups);
  }

  public Optional<Matchup> getHumanComputerMatchup() {
    return matchups.stream()
        .filter(Matchup::containsHuman)
        .findFirst();
  }

  public Optional<PlayerProfile> getOpponentForHuman() {
    return getHumanComputerMatchup()
        .map(this::getComputerFromHumanComputerMatchup);
  }

  private PlayerProfile getComputerFromHumanComputerMatchup(Matchup humanComputerMatchup) {
    return humanComputerMatchup.getPlayer1() instanceof Human ?
        humanComputerMatchup.getPlayer2() : humanComputerMatchup.getPlayer1();
  }

  public List<Matchup> getMatchups() {
    return matchups;
  }

}
