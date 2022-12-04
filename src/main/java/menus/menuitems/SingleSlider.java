package menus.menuitems;

import java.awt.*;

import static java.awt.Color.*;

public class SingleSlider implements Slider {
    private boolean activated;

    private static final Color TEXT_COLOR = WHITE;
    private static final Color RECTANGLE_COLOR = BLUE;
    private static final Color CIRCLE_COLOR = RED;
    private static final int RECTANGLE_LEFT_OFFSET = -2;
    private static final int RECTANGLE_LENGTH_OFFSET = 7;

    private static final int CIRCLE_OFFSET = -3;
    private final int maxValue;
    private final int minValue;
    private static final int DEFAULT_MAX_VALUE = 10;
    private static final int DEFAULT_MIN_VALUE = 1;
    private static final int DEFAULT_DEFAULT_VALUE = 5;
    private static final int DEFAULT_INCREMENT = 1;
    private static final String DEFAULT_LABEL = "DEFAULT_LABEL";
    private static final int DEFAULT_POSITION_Y = 20;
    private final int valueRange;

    private final int increment;

    private final int defaultValue;

    private static final int LENGTH = 325;
    private static final int LEFT_X = 140;
    private static final int RIGHT_X = LEFT_X + LENGTH;

    private static final int LABEL_BUFFER = -100;
    private static final int VALUE_BUFFER = 40;

    private final int positionY;
    private final String label;

    private static final int CIRCLE_DIAMETER = 20;
    private static final int CIRCLE_RADIUS = CIRCLE_DIAMETER / 2;
    private static final int RECTANGLE_HEIGHT = 10;

    private int value;
    private int position;
    private int prevPosition;

    private SingleSlider(Builder builder){
        positionY = builder.positionY;
        label = builder.label;
        minValue = builder.minValue;
        maxValue = builder.maxValue;
        valueRange = maxValue - minValue;
        defaultValue = builder.defaultValue;
        increment = builder.increment;
        activated = false;
        value = defaultValue;
        position = calculatePositionBasedOnValue(value);
        prevPosition = position;
    }

    public static class Builder{
        private int positionY = DEFAULT_POSITION_Y;
        private String label = DEFAULT_LABEL;
        private int minValue = DEFAULT_MIN_VALUE;
        private int maxValue = DEFAULT_MAX_VALUE;
        private int defaultValue = DEFAULT_DEFAULT_VALUE;
        private int increment = DEFAULT_INCREMENT;

        public Builder positionY(int positionY){
            this.positionY = positionY;
            return this;
        }

        public Builder label(String label){
            this.label = label;
            return this;
        }

        public Builder minValue(int minValue){
            this.minValue = minValue;
            return this;
        }

        public Builder maxValue(int maxValue){
            this.maxValue = maxValue;
            return this;
        }

        public Builder defaultValue(int defaultValue){
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder increment(int increment){
            this.increment = increment;
            return this;
        }

        public SingleSlider build(){
            return new SingleSlider(this);
        }

    }

    private int calculatePositionBasedOnValue(int value){
        float fromMaximum = ((float)value - (float) minValue) / (float) valueRange;
        return (int)(fromMaximum * LENGTH + LEFT_X);
    }

    public int getValue(){
        return value;
    }

    @Override
    public void drawSelf(Graphics g) {
        g.setColor(TEXT_COLOR);
        g.drawString(label, LEFT_X + LABEL_BUFFER, positionY + RECTANGLE_HEIGHT);
        g.drawString(Integer.toString(value), RIGHT_X + VALUE_BUFFER, positionY + RECTANGLE_HEIGHT);
        g.setColor(RECTANGLE_COLOR);
        g.fillRect(LEFT_X + RECTANGLE_LEFT_OFFSET, positionY
                , LENGTH + RECTANGLE_LENGTH_OFFSET, RECTANGLE_HEIGHT);
        g.setColor(CIRCLE_COLOR);
        g.fillOval((int)(position - CIRCLE_RADIUS * Math.sqrt(2) / 2), positionY + CIRCLE_OFFSET
                , CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }

    public void recalculateValue(){
        float fromEnd = ((float)position - (float)LEFT_X) / (float)LENGTH;
        value = Math.round(valueRange * fromEnd) + minValue;
        value = value - (value - minValue) % increment;
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
        }
    }

    @Override
    public void onMouseReleased(){
        activated = false;
    }

    private boolean isInside(int x, int y){
        int leftX = doMathOn(position);
        int topY = doMathOn(positionY);
        return x >= leftX
                && x <= leftX + CIRCLE_DIAMETER
                && y >= topY
                && y <= topY + CIRCLE_DIAMETER;
    }

    private int doMathOn(int x){
        return (int)(x - (CIRCLE_RADIUS - CIRCLE_RADIUS * (Math.sqrt(2)) / 2));
    }
    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public void reset() {
        position = prevPosition;
        recalculateValue();
    }

    @Override
    public void resetDefaults() {
        value = defaultValue;
        position = calculatePositionBasedOnValue(defaultValue);
    }

    @Override
    public void submit(){
        prevPosition = position;
    }
}
