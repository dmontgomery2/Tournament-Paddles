package menus;

import menus.menuitems.Button;
import menus.menuitems.SingleButton;
import menus.menuitems.SingleSlider;
import menus.menuitems.Slider;

import java.awt.*;

public class OptionsMenu implements Page {

    private static final Color TITLE_COLOR = Color.WHITE;
    private static final int TITLE_POSITION_X = 200;
    private static final int TITLE_POSITION_Y = 200;
    private static final String TITLE = "";
    private Button backButton;
    private Slider pointsToWin;
    private Slider numberOfPlayers;
    private Slider difficulty;
    public OptionsMenu(){
        backButton = new SingleButton.Builder()
                .action(Menus::exitOptions)
                .label("Back")
                .positionX(20)
                .positionY(420)
                .build();
        numberOfPlayers = new SingleSlider(100, "number of players");
        pointsToWin = new SingleSlider(200, "points to win");
        difficulty = new SingleSlider(300, "difficulty");
    }

    @Override
    public void onMousePressed(int x, int y) {
        backButton.onMousePressed(x, y);
        numberOfPlayers.onMousePressed(x, y);
        pointsToWin.onMousePressed(x, y);
        difficulty.onMousePressed(x, y);
    }

    @Override
    public void onMouseReleased() {
        backButton.onMouseReleased();
        numberOfPlayers.onMouseReleased();
        pointsToWin.onMouseReleased();
        difficulty.onMouseReleased();
    }

    @Override
    public void onDrag(int x, int y) {
        numberOfPlayers.onDrag(x, y);
        pointsToWin.onDrag(x, y);
        difficulty.onDrag(x, y);
    }

    @Override
    public void drawSelf(Graphics g) {
        g.setColor(TITLE_COLOR);
        g.drawString(TITLE, TITLE_POSITION_X, TITLE_POSITION_Y);
        backButton.drawSelf(g);
        numberOfPlayers.drawSelf(g);
        pointsToWin.drawSelf(g);
        difficulty.drawSelf(g);
    }

    public int getNumberOfPlayers(){
        return numberOfPlayers.getValue();
    }

    public int getPointsToWin(){
        return pointsToWin.getValue();
    }
    public int getDifficulty(){
        return difficulty.getValue();
    }

    @Override
    public void onKeyPressed(char keyChar) {

    }

    @Override
    public void onKeyReleased(char keyChar) {

    }
}
