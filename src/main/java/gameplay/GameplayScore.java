package gameplay;

import static common.Const.WINDOW_HEIGHT;
import static common.Const.WINDOW_WIDTH;
import static java.awt.Color.WHITE;

import common.generalinterfaces.Drawable;
import java.awt.Graphics;

class GameplayScore implements Drawable {

  private static final int SCORES_POSITION_Y = WINDOW_HEIGHT / 2;
  private static final int CENTER_LINE_POSITION_X = WINDOW_WIDTH / 2;
  private static final int SCORE_LEFT_BUFFER = 10;
  private static final int SCORE_RIGHT_BUFFER = 20;
  private static final int PLAYER_SCORE_POSITION_X = CENTER_LINE_POSITION_X - SCORE_RIGHT_BUFFER;
  private static final int COMPUTER_SCORE_POSITION_X = CENTER_LINE_POSITION_X + SCORE_LEFT_BUFFER;
  private static final int CENTER_LINE_POSITION_Y1 = 0;
  private static final int CENTER_LINE_POSITION_Y2 = WINDOW_HEIGHT;
  private final int pointsToWin;
  private int playerScore;
  private int computerScore;

  public GameplayScore(int pointsToWin) {
    this.pointsToWin = pointsToWin;
    playerScore = 0;
    computerScore = 0;
  }

  public void onPlayerScored() {
    playerScore++;
  }

  public void onComputerScored() {
    computerScore++;
  }

  @Override
  public void drawSelf(Graphics g) {
    g.setColor(WHITE);
    g.drawString(Integer.toString(playerScore)
        , PLAYER_SCORE_POSITION_X, SCORES_POSITION_Y);
    g.drawString(Integer.toString(computerScore)
        , COMPUTER_SCORE_POSITION_X, SCORES_POSITION_Y);
    g.drawLine(CENTER_LINE_POSITION_X, CENTER_LINE_POSITION_Y1
        , CENTER_LINE_POSITION_X, CENTER_LINE_POSITION_Y2);
  }

  private boolean hasPlayerWon() {
    return playerScore >= pointsToWin;
  }

  private boolean hasComputerWon() {
    return computerScore >= pointsToWin;
  }

  public boolean playerOrComputerHasReachedScoreToWin() {
    return hasPlayerWon() || hasComputerWon();
  }

  public GameplayResult getResult() {
    return new GameplayResult(playerScore, computerScore);
  }

}
