package gameplay;

public class GameplayResult {

  private final int playerScore;
  private final int computerScore;

  public GameplayResult(int playerScore, int computerScore) {
    this.playerScore = playerScore;
    this.computerScore = computerScore;
  }

  public int getPlayerScore() {
    return playerScore;
  }

  public int getComputerScore() {
    return computerScore;
  }
}
