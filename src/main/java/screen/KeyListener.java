package screen;

import controller.Controller;
import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

  @Override
  public void keyTyped(KeyEvent e) {
    // do nothing
  }

  @Override
  public void keyPressed(KeyEvent e) {
    Controller.getInstance().onKeyPressed(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    Controller.getInstance().onKeyReleased(e);
  }
}



