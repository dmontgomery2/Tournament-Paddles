package menus;

import menus.menuitems.Button;
import menus.menuitems.CompositeButton;
import menus.menuitems.SingleButton;
import seasonlogic.Season;

import java.awt.*;

import static common.Utils.drawListOfStrings;


public class SeasonStandings implements Page {

    private Button button;
    private Season season;

    public SeasonStandings(Season season){
        this.season = season;

        Button backButton = new SingleButton.Builder()
                .action(Menus::goToMainMenu)
                .label("Back")
                .positionX(20)
                .positionY(420)
                .build();

        menus.menuitems.Button nextButton = new SingleButton.Builder()
                .action(Menus::advanceSeasonPages)
                .label("Next")
                .positionX(200)
                .positionY(420)
                .build();

        menus.menuitems.Button resetButton = new SingleButton.Builder()
                .action(Menus::reset)
                .label("Reset")
                .positionX(380)
                .positionY(420)
                .build();

        button = new CompositeButton(backButton, nextButton, resetButton);
    }

    @Override
    public void drawSelf(Graphics g) {
        button.drawSelf(g);
        drawListOfStrings(season.getStandings(), g);
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
