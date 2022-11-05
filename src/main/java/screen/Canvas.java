package screen;

import menus.Menus;

import java.awt.*;

import static common.Const.WINDOW_HEIGHT;
import static common.Const.WINDOW_WIDTH;

class Canvas extends java.awt.Canvas
{
    public Canvas() {
        setBackground (Color.BLACK);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void paint(Graphics g)
    {
        Menus.getInstance().drawSelf(g);
    }

    public boolean isFrames() {
        return Menus.getInstance().isFrames();
    }
}