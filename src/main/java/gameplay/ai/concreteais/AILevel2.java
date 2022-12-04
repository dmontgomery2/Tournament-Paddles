package gameplay.ai.concreteais;

import gameplay.ball.Ball;
import gameplay.Paddle;
import gameplay.ai.abstractaitypes.DirectionalAI;

class AILevel2 extends DirectionalAI {
    private static final int VELOCITY_MAGNITUDE = 5;

    public AILevel2(Paddle paddle, Ball ball) {
        super(paddle, ball, VELOCITY_MAGNITUDE);
    }
}
