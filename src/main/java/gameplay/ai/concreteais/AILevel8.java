package gameplay.ai.concreteais;

import gameplay.Paddle;
import gameplay.ai.abstractaitypes.AdvancedPredictiveAI;
import gameplay.ball.Ball;

class AILevel8 extends AdvancedPredictiveAI {

  private static final int VELOCITY_MAGNITUDE = 2;

  public AILevel8(Paddle paddle, Ball ball) {
    super(paddle, ball, VELOCITY_MAGNITUDE);
  }
}
