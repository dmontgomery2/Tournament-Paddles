package game.ai;

import game.Ball;
import game.Paddle;

public class AILevel9 implements AI {

    private static final int VELOCITY_MAGNITUDE = 10;

    private Paddle paddle;
    private Ball ball;

    public AILevel9(Paddle paddle, Ball ball){
        this.paddle = paddle;
        this.ball = ball;
    }

    @Override
    public void assess(){
        if(ball.isMovingUp()){
            paddle.moveUp(VELOCITY_MAGNITUDE);
        }
        else{
            paddle.moveDown(VELOCITY_MAGNITUDE);
        }
    }
}