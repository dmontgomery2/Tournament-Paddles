package gameplay.ai.concreteais;

import gameplay.ball.Ball;
import gameplay.Paddle;
import gameplay.ai.abstractaitypes.BasicPredictiveAI;

class AILevel7 extends BasicPredictiveAI {
    private static final int VELOCITY_MAGNITUDE = 10;

    public AILevel7(Paddle paddle, Ball ball) {
        super(paddle, ball, VELOCITY_MAGNITUDE);
    }
}
