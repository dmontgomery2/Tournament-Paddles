package screen;

import controller.Controller;
import java.awt.event.MouseEvent;

public class MouseMotionListener implements java.awt.event.MouseMotionListener {

  @Override
  public void mouseDragged(MouseEvent e) {
    Controller.getInstance().onDrag(e.getX(), e.getY());
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    Controller.getInstance().onMouseMoved(e.getX(), e.getY());
  }

}
