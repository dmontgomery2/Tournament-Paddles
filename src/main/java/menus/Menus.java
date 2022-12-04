package menus;

import gameplay.PlayingField;
import menus.pages.MainMenu;
import menus.pages.Options;
import menus.pages.seasonpages.SeasonOptions;
import menus.pages.seasonpages.SeasonPage;
import screen.Screen;
import settings.GlobalSettings;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Menus implements Page {

    private static final SettingsUpdater SETTINGS_UPDATER = new SettingsUpdater();

    private final GlobalSettings settings;
    private Page currentPage;
    private final Page mainMenu;

    private SeasonPage currentSeasonPage;
    private final Options options;
    private final SeasonOptions seasonOptions;
    private Page exhibition;
    private static final Menus INSTANCE = new Menus();

    private Menus() {
        settings = new GlobalSettings();
        mainMenu = new MainMenu();
        seasonOptions = new SeasonOptions(settings);
        currentSeasonPage = seasonOptions;
        options = new Options();
        currentPage = mainMenu;
        updateGlobalSettings();
    }

    private void updateGlobalSettings(){
        SETTINGS_UPDATER.updateSettings(settings, options);
    }

    public void update() {
        updateScreen();
    }
    private void updateScreen() {
        Screen.getInstance().update();
    }


    public void goToMainMenu() {
        currentPage = mainMenu;
        updateScreen();
    }

    public void goToOptions() {
        currentPage = options;
        updateScreen();
    }

    public void exitOptionsWithUpdate() {
        options.submit();
        updateGlobalSettings();
        goToMainMenu();
    }

    public void exitOptionsWithoutUpdate() {
        options.resetPrevious();
        goToMainMenu();
    }

    public void goToSeasonOptions() {
        currentPage = seasonOptions;
        updateScreen();
    }

    public void playExhibition() {
        exhibition = new PlayingField.Builder()
                .pointsToWin(settings.getPointsToWin())
                .paddleSize(settings.getPaddleSize())
                .difficulty(settings.getDifficulty())
                .build();
        currentPage = exhibition;
        updateScreen();
    }

    public void advanceSeasonPages() {
        currentSeasonPage = currentSeasonPage.advance();
        currentPage = currentSeasonPage;
        updateScreen();
    }

    @Override
    public void drawSelf(Graphics g) {
        currentPage.drawSelf(g);
    }

    public static Menus getInstance() {
        return INSTANCE;
    }

    public void endGameplay(){
        if(currentPage == exhibition){
            goToMainMenu();
        }
        else{
            advanceSeasonPages();
        }
    }

    public boolean isFrames() {
        return currentPage.isFrames();
    }

    @Override
    public boolean dragActive() {
        return currentPage.dragActive();
    }


    @Override
    public void onKeyPressed(KeyEvent e) {
        currentPage.onKeyPressed(e);
    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        currentPage.onKeyReleased(e);
    }

    @Override
    public void onDrag(int x, int y) {
        currentPage.onDrag(x, y);
        if(currentPage.dragActive()) {
            updateScreen();
        }
    }

    @Override
    public void onMousePressed(int x, int y) {
        currentPage.onMousePressed(x, y);
    }

    @Override
    public void onMouseReleased() {
        currentPage.onMouseReleased();
    }


    public void resetGlobalDefaults() {
        options.resetDefaults();
        updateScreen();
    }

    public void resetSeasonDefaults() {
        seasonOptions.resetDefaults();
        updateScreen();
    }

    public void onContinue() {
        currentPage = currentSeasonPage;
        updateScreen();
    }

    public void startNewSeason() {
        currentSeasonPage = seasonOptions.advance();
        currentPage = currentSeasonPage;
        updateScreen();
    }
}
