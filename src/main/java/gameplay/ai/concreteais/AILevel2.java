package gameplay.ai.concreteais;

import gameplay.Paddle;
import gameplay.ai.abstractaitypes.DirectionalAI;
import gameplay.ball.Ball;

class AILevel2 extends DirectionalAI {

  private static final int VELOCITY_MAGNITUDE = 5;

  public AILevel2(Paddle paddle, Ball ball) {
    super(paddle, ball, VELOCITY_MAGNITUDE);
  }
}
