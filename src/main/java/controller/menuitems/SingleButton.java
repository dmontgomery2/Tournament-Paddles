package controller.menuitems;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.GRAY;
import static java.awt.Color.GREEN;
import static java.awt.Color.WHITE;

import common.functionalinterfaces.ClickAction;
import controller.Controller;
import java.awt.Color;
import java.awt.Graphics;
import lombok.Builder;

@Builder
public class SingleButton implements MenuItem {

  private static final Color STANDARD_COLOR = GREEN;
  private static final Color HOVER_COLOR = WHITE;
  private static final Color ACTIVATED_COLOR = GRAY;
  private static final Color ACTIVATED_OUTSIDE_COLOR = BLUE;

  private boolean isActivated;

  private static final int WIDTH = 150;
  private static final int HEIGHT = 75;
  private Color color;
  private static final Color TEXT_COLOR = BLACK;
  private static final int ADDITIONAL_LABEL_BUFFER_X = 10;
  private final ClickAction action;
  private final String label;
  private final int positionX;
  private final int positionY;

  private boolean isInside(int x, int y) {
    return x >= positionX
        && x <= positionX + WIDTH
        && y >= positionY
        && y <= positionY + HEIGHT;
  }

  @Override
  public void drawSelf(Graphics g) {
    g.setColor(color);
    g.fillRect(positionX, positionY, WIDTH, HEIGHT);
    g.setColor(TEXT_COLOR);
    g.drawString(label, getLabelPositionX(), getLabelPositionY());
  }

  private int getLabelPositionX() {
    return positionX + getLabelBufferX();
  }

  private int getLabelBufferX() {
    return WIDTH / 2 - label.length() - ADDITIONAL_LABEL_BUFFER_X;
  }

  private int getLabelPositionY() {
    return positionY + HEIGHT / 2;
  }

  @Override
  public void onMouseMoved(int x, int y) {
    if (isInside(x, y)) {
      if (!isActivated) {
        setHoverColor();
      }
    } else {
      if (isActivated) {
        setActivatedOutsideColor();
      } else {
        setStandardColor();
      }
    }
  }

  @Override
  public void onDrag(int x, int y) {
    onMouseMoved(x, y);
  }

  private void setHoverColor() {
    setColor(HOVER_COLOR);
  }

  private void setStandardColor() {
    setColor(STANDARD_COLOR);
  }

  private void setActivatedColor() {
    setColor(ACTIVATED_COLOR);
  }

  private void setActivatedOutsideColor() {
    setColor(ACTIVATED_OUTSIDE_COLOR);
  }


  private void setColor(Color color) {
    this.color = color;
    Controller.getInstance().update();
  }

  @Override
  public void onMousePressed(int x, int y) {
    if (isInside(x, y)) {
      isActivated = true;
      setActivatedColor();
    }
  }

  @Override
  public void onMouseReleased(int x, int y) {
    if (isInside(x, y) && isActivated) {
      isActivated = false;
      setHoverColor();
      action.onClick(Controller.getInstance(), x, y);
    } else {
      isActivated = false;
      onMouseMoved(x, y);
    }
  }

}
