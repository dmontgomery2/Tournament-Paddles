package game.ai;

import game.Ball;
import game.Paddle;

class AILevel1 implements AI {

    private static final int VELOCITY_MAGNITUDE = 5;

    private Paddle paddle;
    private Ball ball;

    public AILevel1(Paddle paddle, Ball ball){
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
