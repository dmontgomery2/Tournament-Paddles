package controller.menuitems;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

public class CompositeMenuItem implements MenuItem {

  private final List<MenuItem> menuItems;

  public CompositeMenuItem(MenuItem... menuItems) {
    this.menuItems = Arrays.asList(menuItems);
  }

  @Override
  public void drawSelf(Graphics g) {
    menuItems.forEach(item -> item.drawSelf(g));
  }

  @Override
  public void onMousePressed(int x, int y) {
    menuItems.forEach(item -> item.onMousePressed(x, y));
  }

  @Override
  public void onMouseReleased(int x, int y) {
    menuItems.forEach(menuItem -> menuItem.onMouseReleased(x, y));
  }

  @Override
  public void onMouseMoved(int x, int y) {
    menuItems.forEach(item -> item.onMouseMoved(x, y));
  }
}
