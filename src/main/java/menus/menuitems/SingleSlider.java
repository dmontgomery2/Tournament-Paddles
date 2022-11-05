package menus.menuitems;

import java.awt.*;

public class SingleSlider implements Slider {

    private boolean activated;
    private int clickX;
    private int clickY;

    private static final int MAX_VALUE = 10;
    private static final int MIN_VALUE = 0;

    private static final int LENGTH = 350;
    private static final int LEFT_X = 150;
    private static final int RIGHT_X = LEFT_X + LENGTH;

    private static final int LABEL_BUFFER = 100;
    private static final int VALUE_BUFFER = 40;

    private final int positionY;
    private final String label;

    private static final int CIRCLE_DIAMETER = 20;
    private static final int RECTANGLE_HEIGHT = 10;

    private int value;
    private int position;

    public SingleSlider(int positionY, String label){
        this.positionY = positionY;
        this.label = label;
        activated = false;
        value = 0;
        position = LEFT_X;
    }

    @Override
    public void drawSelf(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString(label, LEFT_X - LABEL_BUFFER, positionY + RECTANGLE_HEIGHT);
        g.drawString(Integer.toString(value), RIGHT_X + VALUE_BUFFER, positionY + RECTANGLE_HEIGHT);
        g.setColor(Color.BLUE);
        g.fillRect(LEFT_X, positionY, LENGTH, RECTANGLE_HEIGHT);
        g.setColor(Color.RED);
        g.fillOval(position, positionY, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }

    public void recalculateValue(){
        float fromEnd = ((float)position - (float)LEFT_X) / (float)LENGTH;
        value = Math.round(MAX_VALUE * fromEnd);
    }

    @Override
    public void onDrag(int x, int y) {
        if(activated && isValidPosition(x)) {
            position = x;
            recalculateValue();
        }
    }

    private boolean isValidPosition(int position){
        return position <= RIGHT_X && position >= LEFT_X;
    }

    @Override
    public void onMousePressed(int x, int y) {
        if(isInside(x, y)){
            activated = true;
            clickX = x;
            clickY = y;
        }
    }

    @Override
    public void onMouseReleased(){
        activated = false;
    }

    private boolean isInside(int x, int y){
        return x >= position
                && x <= position + CIRCLE_DIAMETER
                && y >= positionY
                && y <= positionY + CIRCLE_DIAMETER;
    }

    @Override
    public int getValue() {
        return value;
    }
}
