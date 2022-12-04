package screen;

import menus.Menus;

import java.awt.event.MouseEvent;

public class MouseMotionListener implements java.awt.event.MouseMotionListener{
    @Override
    public void mouseDragged(MouseEvent e) {
        Menus.getInstance().onDrag(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //do nothing
    }
}
