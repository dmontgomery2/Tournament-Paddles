package menus;

import game.PlayingField;
import menus.menuitems.Button;
import menus.menuitems.CompositeButton;
import menus.menuitems.SingleButton;
import screen.Screen;
import seasonlogic.Season;
import settings.Settings;

import java.awt.*;


public class Menus implements Page {

    private SettingsUpdater settingsUpdater;

    private Settings settings;
    private Page currentPage;
    private Page mainMenu;

    private SeasonPages seasonPages;
    private OptionsMenu options;
    private Page exhibition;
    private Season season;
    private static final Menus INSTANCE = new Menus();

    private Menus() {

        settings = new Settings();
        settingsUpdater = new SettingsUpdater();

        Button exhibitionButton = new SingleButton.Builder()
                .action(Menus::playExhibition)
                .label("Exhibition")
                .positionX(200)
                .positionY(90)
                .build();

        Button playGameButton = new SingleButton.Builder()
                .action(Menus::startSeason)
                .label("Season")
                .positionX(200)
                .positionY(260)
                .build();

        Button optionsButton = new SingleButton.Builder()
                .action(Menus::goToOptionsMenu)
                .label("Options")
                .positionX(200)
                .positionY(430)
                .build();
        season = new Season();
        mainMenu = new MainMenu("", new CompositeButton(playGameButton, optionsButton, exhibitionButton), null);
        seasonPages = new SeasonPages(season);
        options = new OptionsMenu();
        currentPage = mainMenu;
    }

    public void startSeason() {
        season.reset(settings.getNumberOfPlayers());
        currentPage = seasonPages;
        updateScreen();
    }

    public void playExhibition() {
        exhibition = new PlayingField(settings.getPointsToWin(), settings.getDifficulty());
        currentPage = exhibition;
        updateScreen();
    }
    public void reset() {
        season.reset();
        updateScreen();
    }

    public void advanceSeasonPages() {
        seasonPages.advance();
        updateScreen();
    }

    @Override
    public void drawSelf(Graphics g) {
        currentPage.drawSelf(g);
    }

    public static Menus getInstance() {
        return INSTANCE;
    }

    private void updateScreen() {
        Screen.getInstance().update();
    }

    public void goToMainMenu() {
        currentPage = mainMenu;
        updateScreen();
    }

    public void goToOptionsMenu() {
        currentPage = options;
        updateScreen();
    }

    @Override
    public void onKeyPressed(char keyChar) {
        currentPage.onKeyPressed(Character.toLowerCase(keyChar));
    }

    @Override
    public void onKeyReleased(char keyChar) {
        currentPage.onKeyReleased(keyChar);
    }

    @Override
    public void onDrag(int x, int y) {
        currentPage.onDrag(x, y);
        updateScreen();
    }

    @Override
    public void onMousePressed(int x, int y) {
        currentPage.onMousePressed(x, y);
    }

    @Override
    public void onMouseReleased() {
        currentPage.onMouseReleased();
    }

    public void exitOptions() {
        settingsUpdater.updateSettings(settings, options);
        currentPage = mainMenu;
        updateScreen();
    }

    public boolean isFrames() {
        return currentPage == exhibition;
    }
}
