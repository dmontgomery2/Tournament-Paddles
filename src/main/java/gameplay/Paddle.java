package gameplay;

import common.genericinterfaces.Drawable;

import java.awt.*;

public class Paddle implements Drawable {

    public static final int WIDTH = 6;
    private static final int INITIAL_POSITION_Y = 160;
    private final Color color;
    private final int height;
    private final int positionX;
    private int positionY;
    private int velocityY;

    public Paddle(Color color, int height, int positionX){
        this.positionX = positionX;
        this.height = height;
        this.color = color;
        positionY = INITIAL_POSITION_Y;
    }

    public int getHeight(){
        return height;
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
        g.fillRect(positionX, positionY, WIDTH, height);
    }

    public void stop() {
        velocityY = 0;
    }

    public void reset(){
        positionY = INITIAL_POSITION_Y;
        velocityY = 0;
    }
}
