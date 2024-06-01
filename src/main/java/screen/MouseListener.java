package screen;

import controller.Controller;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {

  @Override
  public void mousePressed(MouseEvent e) {
    Controller.getInstance().onMousePressed(e.getX(), e.getY());
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    Controller.getInstance().onMouseReleased(e.getX(), e.getY());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    Controller.getInstance().onMouseEntered(e.getX(), e.getY());
  }

  //remaining methods not used


  @Override
  public void mouseClicked(MouseEvent e) {
    //do nothing
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //do nothing
  }
}
