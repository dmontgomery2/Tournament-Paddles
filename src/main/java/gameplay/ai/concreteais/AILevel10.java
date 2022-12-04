package gameplay.ai.concreteais;

import gameplay.ball.Ball;
import gameplay.Paddle;
import gameplay.ai.abstractaitypes.AdvancedPredictiveAI;

class AILevel10 extends AdvancedPredictiveAI {
    private static final int VELOCITY_MAGNITUDE = 10;
    public AILevel10(Paddle paddle, Ball ball) {
        super(paddle, ball, VELOCITY_MAGNITUDE);
    }
}
