package gameplay.ai.concreteais;

import gameplay.Paddle;
import gameplay.ai.abstractaitypes.AdvancedPredictiveAI;
import gameplay.ball.Ball;

class AILevel10 extends AdvancedPredictiveAI {

  private static final int VELOCITY_MAGNITUDE = 10;

  public AILevel10(Paddle paddle, Ball ball) {
    super(paddle, ball, VELOCITY_MAGNITUDE);
  }
}
