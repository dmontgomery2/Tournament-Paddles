package controller;

import controller.pages.MainMenu;
import controller.pages.Options;
import controller.pages.seasonpages.SeasonOptions;
import controller.pages.seasonpages.SeasonPage;
import controller.pages.seasonpages.SeasonStatusPage;
import filehandling.FileReader;
import filehandling.SaveData;
import gameplay.PlayingField;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import screen.Screen;
import settings.GlobalSettings;

public class Controller implements Page {

  private static final SettingsUpdater SETTINGS_UPDATER = new SettingsUpdater();

  private final GlobalSettings settings;
  private Page currentPage;
  private final Page mainMenu;

  private SeasonPage currentSeasonPage;
  private final Options options;
  private final SeasonOptions seasonOptions;
  private Page exhibition;
  private static final Controller INSTANCE = new Controller();

  private Controller() {
    settings = new GlobalSettings();
    mainMenu = new MainMenu();
    seasonOptions = new SeasonOptions(settings);
    currentSeasonPage = seasonOptions;
    options = new Options();
    currentPage = mainMenu;
    updateGlobalSettings();
  }

  private void setCurrentPage(Page page, int mouseX, int mouseY) {
    currentPage = page;
    currentPage.onEnter(mouseX, mouseY);
  }

  private void updateGlobalSettings() {
    SETTINGS_UPDATER.updateSettings(settings, options);
  }

  public void update() {
    updateScreen();
  }

  private void updateScreen() {
    Screen.getInstance().update();
  }


  public void goToMainMenu(int mouseX, int mouseY) {
    setCurrentPage(mainMenu, mouseX, mouseY);
    updateScreen();
  }

  public void goToOptions(int mouseX, int mouseY) {
    setCurrentPage(options, mouseX, mouseY);
    updateScreen();
  }

  public void exitOptionsWithUpdate(int mouseX, int mouseY) {
    options.submit();
    updateGlobalSettings();
    goToMainMenu(mouseX, mouseY);
  }

  public void exitOptionsWithoutUpdate(int mouseX, int mouseY) {
    options.resetPrevious();
    goToMainMenu(mouseX, mouseY);
  }

  public void goToSeasonOptions(int mouseX, int mouseY) {
    setCurrentPage(seasonOptions, mouseX, mouseY);
    updateScreen();
  }

  public void playExhibition(int mouseX, int mouseY) {
    exhibition = new PlayingField.Builder()
        .pointsToWin(settings.getPointsToWin())
        .paddleSize(settings.getPaddleSize())
        .difficulty(settings.getDifficulty())
        .mouseX(mouseX)
        .mouseY(mouseY)
        .build();
    setCurrentPage(exhibition, mouseX, mouseY);
    updateScreen();
  }

  public void advanceSeasonPages(int mouseX, int mouseY) {
    currentSeasonPage = currentSeasonPage.advance(mouseX, mouseY);
    setCurrentPage(currentSeasonPage, mouseX, mouseY);
    updateScreen();
  }

  @Override
  public void drawSelf(Graphics g) {
    currentPage.drawSelf(g);
  }

  public static Controller getInstance() {
    return INSTANCE;
  }

  public void endGameplay(int mouseX, int mouseY) {
    if (currentPage == exhibition) {
      goToMainMenu(mouseX, mouseY);
    } else {
      advanceSeasonPages(mouseX, mouseY);
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
    if (currentPage.dragActive()) {
      updateScreen();
    }
  }

  @Override
  public void onMousePressed(int x, int y) {
    currentPage.onMousePressed(x, y);
  }

  @Override
  public void onMouseReleased(int x, int y) {
    currentPage.onMouseReleased(x, y);
  }

  public void onMouseMoved(int x, int y) {
    currentPage.onMouseMoved(x, y);
  }

  public void resetGlobalDefaults(int mouseX, int mouseY) {
    options.resetDefaults();
    updateScreen();
  }

  public void resetSeasonDefaults(int mouseX, int mouseY) {
    seasonOptions.resetDefaults();
    updateScreen();
  }

  public void onContinue(int mouseX, int mouseY) {
    if (currentSeasonPage == seasonOptions) {
      try {
        SaveData saveData = FileReader.readFromFile();
        currentSeasonPage = new SeasonStatusPage(saveData.getSeason());
      } catch (Exception e) {
        return;
      }
    }
    setCurrentPage(currentSeasonPage, mouseX, mouseY);
    updateScreen();
  }

  public void startNewSeason(int mouseX, int mouseY) {
    currentSeasonPage = seasonOptions.advance(mouseX, mouseY);
    setCurrentPage(currentSeasonPage, mouseX, mouseY);
    updateScreen();
  }
}
