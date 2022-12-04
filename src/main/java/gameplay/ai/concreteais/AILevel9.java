package gameplay.ai.concreteais;

import gameplay.ball.Ball;
import gameplay.Paddle;
import gameplay.ai.abstractaitypes.AdvancedPredictiveAI;

class AILevel9 extends AdvancedPredictiveAI {
    private static final int VELOCITY_MAGNITUDE = 3;

    public AILevel9(Paddle paddle, Ball ball) {
        super(paddle, ball, VELOCITY_MAGNITUDE);
    }
}
