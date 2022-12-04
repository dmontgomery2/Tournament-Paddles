package gameplay.ai.concreteais;

import gameplay.ball.Ball;
import gameplay.Paddle;
import gameplay.ai.abstractaitypes.PositionalAI;

class AILevel5 extends PositionalAI {
    private static final int VELOCITY_MAGNITUDE = 7;

    public AILevel5(Paddle paddle, Ball ball) {
        super(paddle, ball, VELOCITY_MAGNITUDE);
    }
}
