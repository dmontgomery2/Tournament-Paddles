package gameplay.ai.abstractaitypes;

import gameplay.Paddle;
import gameplay.ai.AI;
import gameplay.ball.Ball;

public class PositionalAI implements AI {

  private final int velocityMagnitude;

  private final Paddle paddle;
  private final Ball ball;

  public PositionalAI(Paddle paddle, Ball ball, int velocityMagnitude) {
    this.paddle = paddle;
    this.ball = ball;
    this.velocityMagnitude = velocityMagnitude;
  }

  @Override
  public void assess() {
    if (ball.getCenterY() < paddle.getPositionY()) {
      paddle.moveUp(velocityMagnitude);
    } else {
      paddle.moveDown(velocityMagnitude);
    }
  }
}
