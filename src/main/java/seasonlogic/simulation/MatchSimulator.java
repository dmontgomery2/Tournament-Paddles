package seasonlogic.simulation;

import seasonlogic.calendar.Matchup;
import seasonlogic.calendar.Matchups;

import java.util.Random;

public class MatchSimulator {

  private static final Random RANDOM = new Random();

  private final int pointsToWin;

  public MatchSimulator(int pointsToWin) {
    this.pointsToWin = pointsToWin;
  }


  public void simulateMatches(Matchups matchups) {
    matchups.stream()
        .filter(Matchup::doesNotContainHuman)
        .forEach(this::simulateMatch);
  }

  private void simulateMatch(Matchup matchup) {
    int loserScore = RANDOM.nextInt(pointsToWin);
    if (player1Wins(matchup)) {
      matchup.setPlayer1Score(pointsToWin);
      matchup.setPlayer2Score(loserScore);
    } else {
      matchup.setPlayer2Score(pointsToWin);
      matchup.setPlayer1Score(loserScore);
    }
  }

  private boolean player1Wins(Matchup matchup) {
    return RANDOM.nextInt(100) < 50 + 5 * (matchup.getPlayer1().getDifficulty()
        - matchup.getPlayer2().getDifficulty());
  }
}
