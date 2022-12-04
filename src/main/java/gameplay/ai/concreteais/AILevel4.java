package gameplay.ai.concreteais;

import gameplay.ball.Ball;
import gameplay.Paddle;
import gameplay.ai.abstractaitypes.PositionalAI;

class AILevel4 extends PositionalAI {
    private static final int VELOCITY_MAGNITUDE = 5;

    public AILevel4(Paddle paddle, Ball ball) {
        super(paddle, ball, VELOCITY_MAGNITUDE);
    }
}
