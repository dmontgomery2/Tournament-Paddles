package settings;

public class GlobalSettings {

  private int pointsToWin;
  private int difficulty;
  private int paddleSize;

  private String playerName;

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getPointsToWin() {
    return pointsToWin;
  }

  public void setPointsToWin(int pointsToWin) {
    this.pointsToWin = pointsToWin;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public int getPaddleSize() {
    return paddleSize;
  }

  public void setPaddleSize(int paddleSize) {
    this.paddleSize = paddleSize;
  }
}
