package game;

import static game.PlayingField.BOTTOM_BOUNDARY;
import static game.PlayingField.TOP_BOUNDARY;

class CollisionHandler {

    private final Ball ball;
    private final Paddle playerPaddle;
    private final Paddle computerPaddle;

    public CollisionHandler(Ball ball, Paddle playerPaddle, Paddle computerPaddle){
        this.ball = ball;
        this.playerPaddle = playerPaddle;
        this.computerPaddle = computerPaddle;
    }

    public void handleCollisions() {
        if(ball.getPositionY() >= BOTTOM_BOUNDARY && ball.isMovingDown()){
            ball.reflectY();
        }
        if(ball.getPositionY() <= TOP_BOUNDARY && ball.isMovingUp()){
            ball.reflectY();
        }
    }

    private void handleBallPaddleCollisions(){
        handlePlayerPaddleCollisions();
        handleComputerPaddleCollisions();
    }

    private void handlePlayerPaddleCollisions(){
        if(ballIsInsidePlayerPaddle() && !ball.isMovingRight()){
            ball.setVelocityY(getBallVelocityForPaddleCollision(playerPaddle.getPositionY()));
            ball.reflectX();
        }
    }

    private void handleComputerPaddleCollisions(){
        if(ballIsInsideComputerPaddle() && ball.isMovingRight()){
            ball.setVelocityY(getBallVelocityForPaddleCollision(computerPaddle.getPositionY()));
            ball.reflectX();
        }
    }

    private boolean ballIsInsidePaddle(Paddle paddle){
        return ball.getPositionX() >= paddle.getPositionX()
                && ball.getPositionX() <= paddle.getPositionX() + Paddle.WIDTH
                && ball.getPositionY() >= paddle.getPositionY()
                && ball.getPositionY() <= paddle.getPositionY() + Paddle.HEIGHT;
    }

    private boolean ballIsInsidePlayerPaddle(){
        return ballIsInsidePaddle(playerPaddle);
    }

    private boolean ballIsInsideComputerPaddle(){
        return ballIsInsidePaddle(computerPaddle);
    }

    private int getBallVelocityForPaddleCollision(int paddlePositionY){
        int paddleCenterY = paddlePositionY + Paddle.HEIGHT / 2;
        float distanceFromCenter = ball.getPositionY() + Ball.DIAMETER / 2 - paddleCenterY;
        float paddleHeightAsFloat = Paddle.HEIGHT;
        float percentageFromCenter = distanceFromCenter / paddleHeightAsFloat;
        return (int) (percentageFromCenter * Ball.MAXIMUM_VELOCITY_Y);
    }
}
