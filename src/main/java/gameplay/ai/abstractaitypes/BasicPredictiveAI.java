package gameplay.ai.abstractaitypes;

import static common.Const.WINDOW_HEIGHT;

import gameplay.Paddle;
import gameplay.ai.AI;
import gameplay.ball.Ball;

public abstract class BasicPredictiveAI implements AI {

  private final int velocityMagnitude;
  private final Paddle paddle;
  private final Ball ball;

  public BasicPredictiveAI(Paddle paddle, Ball ball, int velocityMagnitude) {
    this.paddle = paddle;
    this.ball = ball;
    this.velocityMagnitude = velocityMagnitude;
  }

  @Override
  public void assess() {
    if (ball.isMovingLeft()) {
      paddle.moveDown(getVelocityY(WINDOW_HEIGHT / 2 - paddle.getHeight() / 2));
    } else {
      paddle.moveDown(getVelocityY(getProjectedPositionY()));
    }
  }

  private int getVelocityY(int targetPositionY) {
    int delta = targetPositionY - paddle.getPositionY();
    if (Math.abs(delta) > velocityMagnitude) {
      return delta > 0 ? velocityMagnitude : -velocityMagnitude;
    }
    return delta;
  }

  private int getProjectedPositionY() {
    return ball.getCenterY()
        + (Ball.VELOCITY_X_MAGNITUDE / (paddle.getPositionX() - ball.getCenterX()))
        * ball.getVelocityY();
  }
}
