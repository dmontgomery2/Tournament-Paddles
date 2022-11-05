package menus;

import menus.menuitems.Button;
import menus.menuitems.SingleButton;
import seasonlogic.Season;

import java.awt.*;

import static common.Utils.drawListOfStrings;

public class SeasonResults implements Page {

    private Season season;
    private Button button;

    public SeasonResults(Season season){
        button = new SingleButton.Builder()
                .action(Menus::advanceSeasonPages)
                .label("Next")
                .positionX(200)
                .positionY(420)
                .build();
        this.season = season;
    }

    @Override
    public void drawSelf(Graphics g) {
        button.drawSelf(g);
        drawListOfStrings(season.getDailyResults(), g);
    }


    @Override
    public void onDrag(int x, int y) {

    }

    @Override
    public void onMousePressed(int x, int y) {
        button.onMousePressed(x, y);
    }

    @Override
    public void onMouseReleased() {

    }

    @Override
    public void onKeyPressed(char keyChar) {

    }

    @Override
    public void onKeyReleased(char keyChar) {

    }
}
