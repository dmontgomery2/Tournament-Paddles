package gameplay;

import static common.Const.PLAYER_PADDLE_VELOCITY_MAGNITUDE;
import static common.Const.WINDOW_HEIGHT;
import static common.Const.WINDOW_WIDTH;

import gameplay.ai.AI;
import gameplay.ai.concreteais.AIFactory;
import gameplay.ball.Ball;
import gameplay.ballpaddlecollisionhandler.BallPaddleCollisionHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import menus.Menus;
import menus.Page;

public class PlayingField implements Page {

  private static final Color PLAYER_PADDLE_COLOR = Color.BLUE;
  private static final Color COMPUTER_PADDLE_COLOR = Color.GREEN;
  private static final int VERTICAL_BOUNDARY_BUFFER = 30;
  public static final int TOP_BOUNDARY = VERTICAL_BOUNDARY_BUFFER;
  public static final int BOTTOM_BOUNDARY = WINDOW_HEIGHT - VERTICAL_BOUNDARY_BUFFER;
  private static final int HORIZONTAL_BOUNDARY_BUFFER = 30;
  private static final int LEFT_BOUNDARY = VERTICAL_BOUNDARY_BUFFER;
  private static final int RIGHT_BOUNDARY = WINDOW_WIDTH - HORIZONTAL_BOUNDARY_BUFFER;
  private static final int PLAYER_PADDLE_POSITION_X = 60;
  private static final int COMPUTER_PADDLE_POSITION_X = 530;


  private final Paddle playerPaddle;
  private final Paddle computerPaddle;
  private final Ball ball;
  private final AI ai;
  private final GameplayScore gameplayScore;
  private final BallPaddleCollisionHandler ballPaddleCollisionHandler;
  private final InputHandler inputHandler;


  private boolean frozen;
  private boolean gameOver;

  public PlayingField(Builder builder) {
    frozen = false;
    playerPaddle = new Paddle(PLAYER_PADDLE_COLOR, builder.paddleSize, PLAYER_PADDLE_POSITION_X);
    computerPaddle = new Paddle(COMPUTER_PADDLE_COLOR, builder.paddleSize,
        COMPUTER_PADDLE_POSITION_X);
    ball = new Ball();
    ai = AIFactory.getAI(builder.difficulty, computerPaddle, ball);
    gameplayScore = new GameplayScore(builder.pointsToWin);
    ballPaddleCollisionHandler = new BallPaddleCollisionHandler(ball, playerPaddle, computerPaddle);
    inputHandler = new InputHandler(this);
  }

  public void exit(){
    Menus.getInstance().goToMainMenu();
  }

  public static class Builder {

    private int pointsToWin;
    private int paddleSize;
    private int difficulty;

    public Builder pointsToWin(int pointsToWin) {
      this.pointsToWin = pointsToWin;
      return this;
    }

    public Builder paddleSize(int paddleSize) {
      this.paddleSize = paddleSize;
      return this;
    }

    public Builder difficulty(int difficulty) {
      this.difficulty = difficulty;
      return this;
    }

    public PlayingField build() {
      return new PlayingField(this);
    }
  }

  public GameplayResult getGameplayResult() {
    if (!gameOver) {
      throw new IllegalStateException("getResult was called on a game that wasn't finished.");
    }
    return gameplayScore.getResult();
  }

  private void handleCollisions() {
    ballPaddleCollisionHandler.handleBallPaddleCollisions();
    handleBallBoundaryCollisions();
  }

  private void handleBallBoundaryCollisions() {
    handleLeftRightBoundaryCollisions();
    handleTopBottomBoundaryCollisions();
  }

  private void handleLeftRightBoundaryCollisions() {
    if (computerJustScored()) {
      onComputerScored();
    }
    if (playerJustScored()) {
      onPlayerScored();
    }
  }

  private void handleTopBottomBoundaryCollisions() {
    if (ballJustCollidedTop() || ballJustCollidedBottom()) {
      ball.reflectY();
    }
  }

  private boolean ballJustCollidedTop() {
    return ball.getCenterY() <= TOP_BOUNDARY && ball.isMovingUp();
  }

  private boolean ballJustCollidedBottom() {
    return ball.getCenterY() >= BOTTOM_BOUNDARY && ball.isMovingDown();
  }

  private void onPlayerScored() {
    gameplayScore.onPlayerScored();
    onPlayerOrComputerScored();
  }

  private void onComputerScored() {
    gameplayScore.onComputerScored();
    onPlayerOrComputerScored();
  }

  private void onPlayerOrComputerScored() {
    checkGameOver();
    if (!gameOver) {
      freeze();
    }
  }

  private boolean computerJustScored() {
    return ball.getCenterX() <= LEFT_BOUNDARY
        && ball.isMovingLeft()
        && !frozen
        && !gameOver;
  }

  private boolean playerJustScored() {
    return ball.getCenterX() >= RIGHT_BOUNDARY
        && ball.isMovingRight()
        && !frozen
        && !gameOver;
  }

  @Override
  public void drawSelf(Graphics g) {
    handleCollisions();
    ai.assess();
    ball.drawSelf(g);
    playerPaddle.drawSelf(g);
    computerPaddle.drawSelf(g);
    gameplayScore.drawSelf(g);
    if (!frozen && !gameOver) {
      ball.updatePosition();
      playerPaddle.updatePosition();
      computerPaddle.updatePosition();
    }
  }

  private void checkGameOver() {
    if (gameplayScore.playerOrComputerHasReachedScoreToWin()) {
      gameOver = true;
    }
  }

  void movePlayerUp() {
    playerPaddle.moveUp(PLAYER_PADDLE_VELOCITY_MAGNITUDE);
  }

  void movePlayerDown() {
    playerPaddle.moveDown(PLAYER_PADDLE_VELOCITY_MAGNITUDE);
  }

  void stopPlayer() {
    playerPaddle.stop();
  }

  public void tryUnfreeze() {
    if (!frozen) {
      return;
    }
    frozen = false;
    reset();
  }

  public void freeze() {
    frozen = true;
  }

  public void reset() {
    playerPaddle.reset();
    computerPaddle.reset();
    ball.reset();
  }

  void unfreeze() {
    tryUnfreeze();
    if (gameOver) {
      Menus.getInstance().endGameplay();
    }
  }

  @Override
  public void onKeyPressed(KeyEvent e) {
    inputHandler.handleKeyPressed(e.getKeyCode());
  }

  @Override
  public void onKeyReleased(KeyEvent e) {
    inputHandler.handleKeyReleased(e.getKeyCode());
  }

  @Override
  public void onDrag(int x, int y) {
    inputHandler.handleDrag(x, y);
  }

  @Override
  public void onMousePressed(int x, int y) {
    inputHandler.handleMousePressed(x, y);
  }

  @Override
  public void onMouseReleased() {
    inputHandler.handleMouseReleased();
  }

  @Override
  public boolean isFrames() {
    return !gameOver && !frozen;
  }
}
