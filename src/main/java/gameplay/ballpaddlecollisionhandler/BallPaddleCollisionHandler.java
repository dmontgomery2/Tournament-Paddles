package gameplay.ballpaddlecollisionhandler;

import gameplay.Paddle;
import gameplay.ball.Ball;

public class BallPaddleCollisionHandler {

  private final Ball ball;
  private final Paddle playerPaddle;
  private final Paddle computerPaddle;

  public BallPaddleCollisionHandler(Ball ball, Paddle playerPaddle, Paddle computerPaddle) {
    this.ball = ball;
    this.playerPaddle = playerPaddle;
    this.computerPaddle = computerPaddle;
  }

  public void handleBallPaddleCollisions() {
    handlePlayerPaddleCollisions();
    handleComputerPaddleCollisions();
  }

  private boolean ballIntersectsRegion(int x, int y, int w, int h) {
    return new BallPolygon(ball.getCenterX(), ball.getCenterY())
        .intersects(x, y, w, h);
  }

  private void handlePlayerPaddleCollisions() {
    if (ballIsInsidePlayerPaddle() && ball.isMovingLeft()) {
      ball.setVelocityY(getBallVelocityForPaddleCollision(playerPaddle.getPositionY()));
      ball.reflectX();
    }
  }

  private void handleComputerPaddleCollisions() {
    if (ballIsInsideComputerPaddle() && ball.isMovingRight()) {
      ball.setVelocityY(getBallVelocityForPaddleCollision(computerPaddle.getPositionY()));
      ball.reflectX();
    }
  }

  private boolean ballIsInsidePaddle(Paddle paddle) {
    return ballIntersectsRegion(paddle.getPositionX(), paddle.getPositionY(), Paddle.WIDTH,
        paddle.getHeight());
  }

  private boolean ballIsInsidePlayerPaddle() {
    return ballIsInsidePaddle(playerPaddle);
  }

  private boolean ballIsInsideComputerPaddle() {
    return ballIsInsidePaddle(computerPaddle);
  }

  private int getBallVelocityForPaddleCollision(int paddlePositionY) {
    int height = playerPaddle.getHeight();
    int paddleCenterY = paddlePositionY + height / 2;
    float distanceFromCenter = ball.getCenterY() + Ball.DIAMETER / 2 - paddleCenterY;
    float paddleHeightAsFloat = height;
    float halfPaddleHeightAsFloat = paddleHeightAsFloat / 2;
    float percentageFromCenter = distanceFromCenter / halfPaddleHeightAsFloat;
    if (percentageFromCenter > 1) {
      percentageFromCenter = 1;
    } else if (percentageFromCenter < -1) {
      percentageFromCenter = -1;
    }
    return Math.round(percentageFromCenter * Ball.MAXIMUM_VELOCITY_Y);
  }
}
