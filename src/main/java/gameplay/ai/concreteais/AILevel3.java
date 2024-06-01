package gameplay.ai.concreteais;

import gameplay.Paddle;
import gameplay.ai.abstractaitypes.PositionalAI;
import gameplay.ball.Ball;

class AILevel3 extends PositionalAI {

  private static final int VELOCITY_MAGNITUDE = 3;

  public AILevel3(Paddle paddle, Ball ball) {
    super(paddle, ball, VELOCITY_MAGNITUDE);
  }
}
