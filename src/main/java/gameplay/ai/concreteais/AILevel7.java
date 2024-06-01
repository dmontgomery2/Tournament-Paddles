package gameplay.ai.concreteais;

import gameplay.Paddle;
import gameplay.ai.abstractaitypes.BasicPredictiveAI;
import gameplay.ball.Ball;

class AILevel7 extends BasicPredictiveAI {

  private static final int VELOCITY_MAGNITUDE = 10;

  public AILevel7(Paddle paddle, Ball ball) {
    super(paddle, ball, VELOCITY_MAGNITUDE);
  }
}
