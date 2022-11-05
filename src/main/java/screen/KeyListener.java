package screen;

import menus.Menus;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener{
    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Menus.getInstance().onKeyPressed(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Menus.getInstance().onKeyReleased(e.getKeyChar());
    }
}



