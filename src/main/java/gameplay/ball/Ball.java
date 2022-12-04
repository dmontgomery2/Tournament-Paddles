package gameplay.ball;

import common.genericinterfaces.Drawable;

import java.awt.*;

import static java.awt.Color.RED;

public class Ball implements Drawable {
    public static final int DIAMETER = 10;
    public static final int RADIUS = DIAMETER / 2;
    public static final int MAXIMUM_VELOCITY_Y = 20;
    public static final int VELOCITY_X_MAGNITUDE = 7;

    private static final VelocityRandomizer VELOCITY_RANDOMIZER = new VelocityRandomizer();
    private static final int INITIAL_POSITION_X = 430;
    private static final int INITIAL_POSITION_Y = 130;

    private int positionX;
    private int velocityX;
    private int positionY;
    private int velocityY;

    public Ball(){
        reset();
    }

    public int getVelocityY(){
        return velocityY;
    }

    public boolean isMovingRight(){
        return velocityX > 0;
    }
    public boolean isMovingLeft(){
        return velocityX < 0;
    }
    public boolean isMovingUp(){
        return velocityY < 0;
    }
    public boolean isMovingDown() {
        return velocityY > 0;
    }

    public void updatePosition(){
        positionX += velocityX;
        positionY += velocityY;
    }

    public int getCenterX() {
        return (int)(positionX + 0.5 * RADIUS * Math.sqrt(2));

    }

    public int getCenterY() {
        return (int)(positionY + 0.5 * RADIUS * Math.sqrt(2));
    }

    public void setVelocityY(int velocityY){
        this.velocityY = velocityY;
    }

    public void reflectX(){
        velocityX = -velocityX;
    }

    public void reflectY(){
        velocityY = -velocityY;
    }

    @Override
    public void drawSelf(Graphics g){
        g.setColor(RED);
        g.fillOval(positionX, positionY, DIAMETER, DIAMETER);
    }

    public void reset(){
        positionX = INITIAL_POSITION_X;
        positionY = INITIAL_POSITION_Y;
        velocityX = VELOCITY_RANDOMIZER.getVelocityX();
        velocityY = VELOCITY_RANDOMIZER.getVelocityY();
    }

}
