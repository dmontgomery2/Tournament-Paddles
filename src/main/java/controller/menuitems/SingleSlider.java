package controller.menuitems;

import static common.Const.TEXTBOX_AND_SLIDER_LABEL_BUFFER_X;
import static common.Const.TEXTBOX_AND_SLIDER_POSITION_X;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;

import java.awt.Color;
import java.awt.Graphics;

public class SingleSlider implements Slider {

  private boolean activated;

  private static final int CIRCLE_DIAMETER = 20;
  private static final int CIRCLE_RADIUS = CIRCLE_DIAMETER / 2;
  private static final int RECTANGLE_HEIGHT = 10;

  private static final Color TEXT_COLOR = WHITE;
  private static final Color RECTANGLE_COLOR = BLUE;
  private static final Color CIRCLE_COLOR = RED;
  private static final int RECTANGLE_LEFT_OFFSET = -2;
  private static final int RECTANGLE_UP_OFFSET = -RECTANGLE_HEIGHT / 2;
  private static final int RECTANGLE_LENGTH_OFFSET = 7;
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
  private static final int LEFT_X = TEXTBOX_AND_SLIDER_POSITION_X;
  private static final int RIGHT_X = LEFT_X + LENGTH;

  private static final int LABEL_BUFFER_X = TEXTBOX_AND_SLIDER_LABEL_BUFFER_X;
  private static final int VALUE_BUFFER = 40;

  private final String label;


  private int value;
  private int positionX;
  private int prevPositionX;
  private final int positionY;
  private int clickOffset;

  private SingleSlider(Builder builder) {
    positionY = builder.positionY;
    label = builder.label;
    minValue = builder.minValue;
    maxValue = builder.maxValue;
    valueRange = maxValue - minValue;
    defaultValue = builder.defaultValue;
    increment = builder.increment;
    activated = false;
    value = defaultValue;
    positionX = calculatePositionBasedOnValue(value);
    prevPositionX = positionX;
  }

  public static class Builder {

    private int positionY = DEFAULT_POSITION_Y;
    private String label = DEFAULT_LABEL;
    private int minValue = DEFAULT_MIN_VALUE;
    private int maxValue = DEFAULT_MAX_VALUE;
    private int defaultValue = DEFAULT_DEFAULT_VALUE;
    private int increment = DEFAULT_INCREMENT;

    public Builder positionY(int positionY) {
      this.positionY = positionY;
      return this;
    }

    public Builder label(String label) {
      this.label = label;
      return this;
    }

    public Builder minValue(int minValue) {
      this.minValue = minValue;
      return this;
    }

    public Builder maxValue(int maxValue) {
      this.maxValue = maxValue;
      return this;
    }

    public Builder defaultValue(int defaultValue) {
      this.defaultValue = defaultValue;
      return this;
    }

    public Builder increment(int increment) {
      this.increment = increment;
      return this;
    }

    public SingleSlider build() {
      return new SingleSlider(this);
    }

  }

  private int calculatePositionBasedOnValue(int value) {
    float fromMaximum = ((float) value - (float) minValue) / (float) valueRange;
    return (int) (fromMaximum * LENGTH + LEFT_X);
  }

  public int getValue() {
    return value;
  }

  @Override
  public void drawSelf(Graphics g) {
    g.setColor(TEXT_COLOR);
    g.drawString(label, LEFT_X + LABEL_BUFFER_X,
        positionY + RECTANGLE_HEIGHT / 2);
    g.drawString(Integer.toString(value), RIGHT_X + VALUE_BUFFER, positionY + RECTANGLE_HEIGHT / 2);
    g.setColor(RECTANGLE_COLOR);
    g.fillRect(LEFT_X + RECTANGLE_LEFT_OFFSET, positionY + RECTANGLE_UP_OFFSET
        , LENGTH + RECTANGLE_LENGTH_OFFSET, RECTANGLE_HEIGHT);
    g.setColor(CIRCLE_COLOR);
    g.fillOval(positionX - CIRCLE_RADIUS,
        positionY - CIRCLE_RADIUS
        , CIRCLE_DIAMETER, CIRCLE_DIAMETER);
  }

  public void recalculateValue() {
    float fromEnd = ((float) positionX - (float) LEFT_X) / (float) LENGTH;
    value = Math.round(valueRange * fromEnd) + minValue;
    value = value - (value - minValue) % increment;
    value = constrainValue(value);
  }

  @Override
  public void onDrag(int x, int y) {
    if (activated) {
      x = constrainPosition(x);
      positionX = x + clickOffset;
      recalculateValue();
    }
  }

  private int constrainPosition(int position) {
    position = Math.min(position, RIGHT_X);
    return Math.max(position, LEFT_X);
  }

  private int constrainValue(int value) {
    value = Math.min(value, maxValue);
    return Math.max(value, minValue);
  }

  @Override
  public void onMousePressed(int x, int y) {
    if (isInsideCircle(x, y)) {
      activated = true;
      clickOffset = positionX - x;
    }
  }

  @Override
  public void onMouseReleased(int x, int y) {
    activated = false;
  }

  private boolean isInsideCircle(int x, int y) {
    return Math.pow(Math.abs(x - positionX), 2) + Math.pow(Math.abs(y - positionY), 2)
        <= Math.pow(CIRCLE_RADIUS, 2);
  }

  @Override
  public boolean isActivated() {
    return activated;
  }

  @Override
  public void reset() {
    positionX = prevPositionX;
    recalculateValue();
  }

  @Override
  public void resetDefaults() {
    value = defaultValue;
    positionX = calculatePositionBasedOnValue(defaultValue);
  }

  @Override
  public void submit() {
    prevPositionX = positionX;
  }
}
