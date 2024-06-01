package gameplay.ai.concreteais;

import gameplay.Paddle;
import gameplay.ai.abstractaitypes.RandomAI;
import gameplay.ball.Ball;

class AILevel1 extends RandomAI {

  private static final int VELOCITY_MAGNITUDE = 8;

  public AILevel1(Paddle paddle, Ball ball) {
    super(paddle, ball, VELOCITY_MAGNITUDE);
  }
}
