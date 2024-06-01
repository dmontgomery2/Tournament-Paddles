package seasonlogic.calendar;

import gameplay.GameplayResult;
import java.io.Serializable;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;

public class Matchup implements Serializable {

  private final PlayerProfile player1;
  private final PlayerProfile player2;

  private int player1Score;
  private int player2Score;

  public Matchup(PlayerProfile player1, PlayerProfile player2) {
    this.player1 = player1;
    this.player2 = player2;
  }


  public PlayerProfile getPlayer1() {
    return player1;
  }

  public PlayerProfile getPlayer2() {
    return player2;
  }

  public void setPlayer1Score(int player1Score) {
    this.player1Score = player1Score;
  }

  public void setPlayer2Score(int player2Score) {
    this.player2Score = player2Score;
  }

  public boolean containsHuman() {
    return player1 instanceof Human || player2 instanceof Human;
  }

  public boolean doesNotContainHuman() {
    return !containsHuman();
  }

  public String getResult() {
    return player1.getName() + " " + player1Score + ", " + player2.getName() + " " + player2Score;
  }

  @Override
  public String toString() {
    return player1.getName() + " versus " + player2.getName();
  }

  public void recordGameplayResult(GameplayResult gameplayResult) {
    if (player1 instanceof Human) {
      player1Score = gameplayResult.getPlayerScore();
      player2Score = gameplayResult.getComputerScore();
    } else {
      player1Score = gameplayResult.getComputerScore();
      player2Score = gameplayResult.getPlayerScore();
    }
  }

  public PlayerProfile getWinner() {
    if (player1Score > player2Score) {
      return player1;
    }
    return player2;
  }

  public PlayerProfile getLoser() {
    if (player1Score > player2Score) {
      return player2;
    }
    return player1;
  }
}
