package gameplay.ai.abstractaitypes;

import static common.Const.WINDOW_HEIGHT;

import gameplay.Paddle;
import gameplay.PlayingField;
import gameplay.ai.AI;
import gameplay.ball.Ball;

public abstract class AdvancedPredictiveAI implements AI {

  private static final int WIGGLE_ROOM = 1;
  private static final double VERT_HORIZ_RADIUS_COMPONENT = 0.5 * Ball.RADIUS * Math.sqrt(2);
  private final int velocityMagnitude;
  private boolean predicted;

  private int optimalPositionY;

  private final Paddle paddle;
  private final Ball ball;

  public AdvancedPredictiveAI(Paddle paddle, Ball ball, int velocityMagnitude) {
    this.paddle = paddle;
    this.ball = ball;
    this.velocityMagnitude = velocityMagnitude;
  }

  @Override
  public void assess() {
    if (ball.isMovingLeft()) {
      predicted = false;
      paddle.moveDown(getVelocityY(WINDOW_HEIGHT / 2 - paddle.getHeight() / 2));
    } else {
      if (!predicted) {
        optimalPositionY = getOptimalPositionY();
        predicted = true;
      }
      paddle.moveDown(getVelocityY(optimalPositionY));
    }
  }

  private int getVelocityY(int targetPositionY) {
    int delta = targetPositionY - paddle.getPositionY();
    if (Math.abs(delta) > velocityMagnitude) {
      return delta > 0 ? velocityMagnitude : -velocityMagnitude;
    }
    return delta;
  }

  private int getOptimalPositionY() {
    int simBallCtrX = ball.getCenterX();
    int simBallCtrY = ball.getCenterY();
    int simBallVelY = ball.getVelocityY();
    while (simBallCtrX < paddle.getPositionX() - VERT_HORIZ_RADIUS_COMPONENT + WIGGLE_ROOM) {
      simBallCtrX += Ball.VELOCITY_X_MAGNITUDE;
      simBallVelY = afterCollisions(simBallCtrY, simBallVelY);
      simBallCtrY += simBallVelY;
    }
    if (shouldHitTopEdge(simBallCtrY)) {
      return (int) (simBallCtrY + 0.5 * VERT_HORIZ_RADIUS_COMPONENT - WIGGLE_ROOM);
    }
    return (int) (simBallCtrY - paddle.getHeight() - VERT_HORIZ_RADIUS_COMPONENT + WIGGLE_ROOM);
  }

  private boolean shouldHitTopEdge(int simBallCtrY) {
    return Math.abs(paddle.getPositionY() - simBallCtrY - VERT_HORIZ_RADIUS_COMPONENT)
        < Math.abs(
        paddle.getPositionY() + paddle.getHeight() - simBallCtrY + VERT_HORIZ_RADIUS_COMPONENT);
  }

  private int afterCollisions(int simBallCtrY, int simBallVelY) {
    if (collidedTop(simBallCtrY, simBallVelY) || collidedBottom(simBallCtrY, simBallVelY)) {
      return -simBallVelY;
    }
    return simBallVelY;
  }

  private boolean collidedTop(int simBallCtrY, int simBallVelY) {
    return simBallCtrY <= PlayingField.TOP_BOUNDARY && simBallVelY < 0;
  }

  private boolean collidedBottom(int simBallCtrY, int simBallVelY) {
    return simBallCtrY >= PlayingField.BOTTOM_BOUNDARY && simBallVelY > 0;
  }
}
