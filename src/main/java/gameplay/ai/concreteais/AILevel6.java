package gameplay.ai.concreteais;

import gameplay.Paddle;
import gameplay.ai.abstractaitypes.BasicPredictiveAI;
import gameplay.ball.Ball;

class AILevel6 extends BasicPredictiveAI {

  private static final int VELOCITY_MAGNITUDE = 5;

  public AILevel6(Paddle paddle, Ball ball) {
    super(paddle, ball, VELOCITY_MAGNITUDE);
  }
}
