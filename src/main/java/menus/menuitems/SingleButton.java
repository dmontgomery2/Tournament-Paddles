package menus.menuitems;

import lombok.Builder;
import menus.Menus;

import java.awt.*;
import java.util.function.Consumer;

import static java.awt.Color.BLACK;
import static java.awt.Color.GREEN;

@Builder
public class SingleButton implements MenuItem {

    private static final int WIDTH = 150;
    private static final int HEIGHT = 75;
    private static final Color COLOR = GREEN;
    private static final Color TEXT_COLOR = BLACK;
    private final Consumer<Menus> action;
    private final String label;
    private final int positionX;
    private final int positionY;

    private boolean isInside(int x, int y){
        return x >= positionX
                && x <= positionX + WIDTH
                && y >= positionY
                && y <= positionY + HEIGHT;
    }

    @Override
    public void drawSelf(Graphics g) {
        g.setColor(COLOR);
        g.fillRect(positionX, positionY, WIDTH, HEIGHT);
        g.setColor(TEXT_COLOR);
        g.drawString(label, getTextPositionX(), getTextPositionY());
    }

    private int getTextPositionX(){
        return positionX + WIDTH / 2 - 15;
    }

    private int getTextPositionY(){
        return positionY + HEIGHT / 2;
    }

    @Override
    public void onMousePressed(int x, int y) {
        if(isInside(x, y)) {
            action.accept(Menus.getInstance());
        }
    }
}
