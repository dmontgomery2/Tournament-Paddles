package gameplay.ai.concreteais;

import gameplay.ball.Ball;
import gameplay.Paddle;
import gameplay.ai.abstractaitypes.BasicPredictiveAI;

class AILevel6 extends BasicPredictiveAI {

    private static final int VELOCITY_MAGNITUDE = 5;

    public AILevel6(Paddle paddle, Ball ball) {
        super(paddle, ball, VELOCITY_MAGNITUDE);
    }
}
