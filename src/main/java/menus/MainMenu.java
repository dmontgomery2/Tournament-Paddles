package menus;

import menus.menuitems.Button;
import menus.menuitems.Slider;

import java.awt.*;

public class MainMenu implements Page {

    private static final Color TITLE_COLOR = Color.WHITE;
    private static final int TITLE_POSITION_X = 200;
    private static final int TITLE_POSITION_Y = 200;
    private String title;
    private Button button;
    private Slider slider;


    public MainMenu(String title, Button button, Slider slider){
        this.title = title;
        this.button = button;
        this.slider = slider;
    }

    @Override
    public void drawSelf(Graphics g) {
        g.setColor(TITLE_COLOR);
        g.drawString(title, TITLE_POSITION_X, TITLE_POSITION_Y);
        button.drawSelf(g);
        if(slider != null) {
            slider.drawSelf(g);
        }
    }

    @Override
    public void onDrag(int x, int y) {
        if(slider != null){
            slider.onDrag(x, y);
        }
    }

    @Override
    public void onMousePressed(int x, int y) {
        button.onMousePressed(x, y);
        if(slider != null) {
            slider.onMousePressed(x, y);
        }
    }

    @Override
    public void onMouseReleased() {
        button.onMouseReleased();
        if(slider != null) {
            slider.onMouseReleased();
        }
    }

    @Override
    public void onKeyPressed(char keyChar) {

    }

    @Override
    public void onKeyReleased(char keyChar) {

    }
}
