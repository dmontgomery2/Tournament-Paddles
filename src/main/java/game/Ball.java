package game;

import menus.Drawable;

import java.awt.*;

public class Ball implements Drawable {

    public static final int DIAMETER = 10;
    public static final int MAXIMUM_VELOCITY_Y = 30;

    private static final int INITIAL_POSITION_X = 30;
    private static final int INITIAL_POSITION_Y = 130;
    private static final int VELOCITY_X_MAGNITUDE = 7;
    private static final int INITIAL_VELOCITY_Y = 11;

    private int positionX;
    private int velocityX;
    private int positionY;
    private int velocityY;

    public Ball(){
        positionX = INITIAL_POSITION_X;
        positionY = INITIAL_POSITION_Y;
        velocityX = VELOCITY_X_MAGNITUDE;
        velocityY = -INITIAL_VELOCITY_Y;
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

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
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
        g.setColor(Color.RED);
        g.fillOval(positionX, positionY, DIAMETER, DIAMETER);
    }

    public void reset(){
        positionX = INITIAL_POSITION_X;
        positionY = INITIAL_POSITION_Y;
        velocityX = VELOCITY_X_MAGNITUDE;
        velocityY = -INITIAL_VELOCITY_Y;
    }

}
