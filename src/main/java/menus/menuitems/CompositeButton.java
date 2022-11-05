package menus.menuitems;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CompositeButton implements Button{

    private List<Button> buttons;

    public CompositeButton(Button ... buttons){
        this.buttons = Arrays.asList(buttons);
    }

    @Override
    public void drawSelf(Graphics g) {
        buttons.forEach(b -> b.drawSelf(g));
    }

    @Override
    public void onMousePressed(int x, int y) {
        buttons.forEach(b -> b.onMousePressed(x, y));
    }

    @Override
    public void onMouseReleased() {
        buttons.forEach(b -> b.onMouseReleased());
    }
}
