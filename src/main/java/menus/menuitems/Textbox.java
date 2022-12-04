package menus.menuitems;

import common.genericinterfaces.Keyable;
import menus.Menus;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class Textbox implements MenuItem, Keyable {

    private static final Color LABEL_COLOR = WHITE;
    private static final Color BOX_COLOR = WHITE;

    private static final int BACKSPACE_KEY_CODE = 8;
    private static final int DELETE_KEY_CODE = 127;

    private final int positionX;
    private final int positionY;
    private final String defaultText;
    private final String label;
    private StringBuilder text;
    private String previousText;


    private boolean activated;



    private static final int WIDTH = 150;
    private static final int HEIGHT = 25;

    private static final int TEXT_BUFFER_X = 5;
    private static final int TEXT_BUFFER_Y = 5;
    private static final int LABEL_BUFFER_X = -100;
    private static final int LABEL_BUFFER_Y = 5;

    private Textbox(Builder builder){
        positionX = builder.positionX;
        positionY = builder.positionY;
        defaultText = builder.defaultText;
        label = builder.label;
        previousText = defaultText;
        text = new StringBuilder(defaultText);
    }

    public static class Builder{
        private int positionX;
        private int positionY;
        private String defaultText;
        private String label;

        public Builder positionX(int positionX){
            this.positionX = positionX;
            return this;
        }

        public Builder positionY(int positionY){
            this.positionY = positionY;
            return this;
        }

        public Builder defaultText(String defaultText){
            this.defaultText = defaultText;
            return this;
        }

        public Builder label(String label){
            this.label = label;
            return this;
        }

        public Textbox build(){
            return new Textbox(this);
        }


    }


    @Override
    public void onMousePressed(int x, int y) {
        boolean isInside = isInside(x, y);
        if(activated && !isInside){
            Menus.getInstance().update();
            activated = false;
        }
        else if(!activated && isInside) {
            Menus.getInstance().update();
            activated = true;
        }
    }

    public String getText(){
        return text.toString();
    }


    private boolean isInside(int x, int y){
        return x >= positionX
                && x <= positionX + WIDTH
                && y >= positionY - 10
                && y <= positionY - 10 + HEIGHT;
    }

    @Override
    public void drawSelf(Graphics g) {
        g.setColor(LABEL_COLOR);
        drawLabel(g);
        g.setColor(BOX_COLOR);
        drawBox(g);
        g.setColor(getTextColor());
        drawText(g);
    }

    private void drawBox(Graphics g){
        if(activated){
            g.fillRect(positionX, positionY - 10, WIDTH, HEIGHT);
        }
        else{
            g.drawRect(positionX, positionY - 10, WIDTH, HEIGHT);
        }
    }

    private void drawText(Graphics g){
        g.drawString(text.toString(), positionX + TEXT_BUFFER_X, positionY + TEXT_BUFFER_Y);
    }

    private void drawLabel(Graphics g){
        g.drawString(label, positionX + LABEL_BUFFER_X, positionY + LABEL_BUFFER_Y);
    }

    private Color getTextColor(){
        return activated ? BLACK : WHITE;
    }

    @Override
    public void onKeyPressed(KeyEvent e){
        if(!activated){
            return;
        }
        if(isAlphanumericOrSpace(e.getKeyChar())){
            onStandardKeyPressed(e.getKeyChar());
        }
        if(e.getKeyCode() == BACKSPACE_KEY_CODE || e.getKeyCode() == DELETE_KEY_CODE){
            deleteChar();
        }
    }

    private boolean isAlphanumericOrSpace(char c){
        return Character.isAlphabetic(c)
                || Character.isDigit(c)
                || Character.isWhitespace(c);
    }

    private void onStandardKeyPressed(char keyChar){
        text.append(keyChar);
        Menus.getInstance().update();
    }

    private void deleteChar(){
        text.deleteCharAt(text.length() - 1);
        Menus.getInstance().update();
    }

    public void reset() {
        text = new StringBuilder(previousText);
    }

    public void resetDefaults() {
        text = new StringBuilder(defaultText);
    }

    public void submit() {
        previousText = text.toString();
    }

}
