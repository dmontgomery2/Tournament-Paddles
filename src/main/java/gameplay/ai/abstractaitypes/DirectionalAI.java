package gameplay.ai.abstractaitypes;

import gameplay.Paddle;
import gameplay.ai.AI;
import gameplay.ball.Ball;

public abstract class DirectionalAI implements AI {

  private final int velocityMagnitude;
  private final Paddle paddle;
  private final Ball ball;

  public DirectionalAI(Paddle paddle, Ball ball, int velocityMagnitude) {
    this.paddle = paddle;
    this.ball = ball;
    this.velocityMagnitude = velocityMagnitude;
  }

  @Override
  public void assess() {
    if (ball.isMovingUp()) {
      paddle.moveUp(velocityMagnitude);
    } else {
      paddle.moveDown(velocityMagnitude);
    }
  }
}
