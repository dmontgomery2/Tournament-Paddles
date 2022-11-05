package game;

import menus.Drawable;

import java.awt.*;

public class Paddle implements Drawable {

    public static final int WIDTH = 6;
    public static final int HEIGHT = 50;
    private static final int INITIAL_POSITION_Y = 160;

    private final int positionX;
    private final Color color;
    private int positionY;
    private int velocityY;

    public Paddle(int positionX, Color color){
        this.positionX = positionX;
        this.color = color;
        positionY = INITIAL_POSITION_Y;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public void moveUp(int velocityMagnitude) {
        velocityY = -velocityMagnitude;
    }

    public void moveDown(int velocityMagnitude){
        velocityY = velocityMagnitude;
    }

    public void updatePosition(){
        positionY += velocityY;
    }

    @Override
    public void drawSelf(Graphics g){
        g.setColor(color);
        g.fillRect(positionX, positionY, WIDTH, HEIGHT);
    }

    public void stop() {
        velocityY = 0;
    }

    public void reset(){
        positionY = INITIAL_POSITION_Y;
        velocityY = 0;
    }
}
