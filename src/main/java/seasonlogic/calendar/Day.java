package seasonlogic.calendar;

import static common.customcollectors.CustomCollectors.toMatchups;

import java.util.List;
import java.util.Optional;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;
import seasonlogic.simulation.MatchSimulator;

public class Day {

  private final Matchups matchups;

  private final Matchups computerComputerMatchups;

  public Day(Matchups matchups) {
    this.matchups = matchups;
    computerComputerMatchups = matchups.stream()
        .filter(Matchup::doesNotContainHuman)
        .collect(toMatchups());
  }

  public void simulateMatchups(MatchSimulator matchSimulator) {
    matchSimulator.simulateMatches(computerComputerMatchups);
  }

  public List<String> getMatchupsStrings() {
    return matchups.getStrings();
  }

  public List<String> getResultsStrings() {
    return matchups.getResultsStrings();
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

  public Matchups getMatchups() {
    return matchups;
  }

}
