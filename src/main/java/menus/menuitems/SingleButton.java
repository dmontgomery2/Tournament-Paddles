package menus.menuitems;

import menus.Menus;

import java.awt.*;
import java.util.function.Consumer;

public class SingleButton implements Button {

    private static final int WIDTH = 150;
    private static final int HEIGHT = 75;
    private static final Color COLOR = Color.GREEN;
    private static final Color TEXT_COLOR = Color.BLACK;
    private final Consumer<Menus> action;
    private final String label;
    private final int positionX;
    private final int positionY;

    private SingleButton(Builder builder){
        action = builder.action;
        label = builder.label;
        positionX = builder.positionX;
        positionY = builder.positionY;
    }

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

    @Override
    public void onMouseReleased() {

    }

    public static class Builder {
        private Consumer<Menus> action;
        private String label;
        private int positionX;
        private int positionY;

        public Builder action(Consumer<Menus> action){
            this.action = action;
            return this;
        }

        public Builder label(String label){
            this.label = label;
            return this;
        }

        public Builder positionX(int positionX){
            this.positionX = positionX;
            return this;
        }

        public Builder positionY(int positionY){
            this.positionY = positionY;
            return this;
        }

        public SingleButton build(){
            return new SingleButton(this);
        }
    }
}
