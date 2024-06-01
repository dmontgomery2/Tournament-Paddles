package seasonlogic.simulation;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import seasonlogic.calendar.Matchup;

public class MatchSimulator implements Serializable {

  private static final Random RANDOM = new Random();

  private final int pointsToWin;

  public MatchSimulator(int pointsToWin) {
    this.pointsToWin = pointsToWin;
  }


  public void simulateMatches(List<Matchup> matchups) {
    matchups.stream()
        .filter(Matchup::doesNotContainHuman)
        .forEach(this::simulateMatch);
  }

  private void simulateMatch(Matchup matchup) {
    RandomizationResult randomizationResult = getRandomizationResult(matchup);
    if (randomizationResult.player1Won()) {
      matchup.setPlayer1Score(pointsToWin);
      matchup.setPlayer2Score(getLoserScore(randomizationResult.getPercentageToLoss()));
    } else {
      matchup.setPlayer2Score(pointsToWin);
      matchup.setPlayer1Score(getLoserScore(randomizationResult.getPercentageToLoss()));
    }
  }

  private RandomizationResult getRandomizationResult(Matchup matchup) {
    int cutoff = 10 + matchup.getPlayer1().getDifficulty() - matchup.getPlayer2().getDifficulty();
    int randomInt = RANDOM.nextInt(20);
    boolean player1Wins = randomInt < cutoff;
    float percentageToLoss;
    if (player1Wins) {
      percentageToLoss = (1f + randomInt) / (float) cutoff;
    } else {
      percentageToLoss = (20f - randomInt) / (20f - cutoff);
    }

    return new RandomizationResult(percentageToLoss, player1Wins);
  }

  private int getLoserScore(float percentageToLoss) {
    return Math.min((int) (percentageToLoss * pointsToWin), pointsToWin - 1);
  }
}
