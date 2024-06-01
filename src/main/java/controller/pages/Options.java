package controller.pages;

import static common.Const.TEXTBOX_AND_SLIDER_POSITION_X;
import static java.awt.Color.GREEN;
import static java.awt.Color.WHITE;

import common.functionalinterfaces.ClickAction;
import controller.Controller;
import controller.Page;
import controller.menuitems.CompositeMenuItem;
import controller.menuitems.CompositeSlider;
import controller.menuitems.MenuItem;
import controller.menuitems.SingleButton;
import controller.menuitems.SingleSlider;
import controller.menuitems.Slider;
import controller.menuitems.Textbox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Options implements Page {

  private static final String DEFAULT_PLAYER_NAME = "Player";

  private static final int BUTTONS_POSITION_Y = 480;
  private static final int INITIAL_POSITION_Y = 80;
  private static final int SPACING = 100;

  // player name textbox properties
  private static final int PLAYER_NAME_TEXTBOX_POSITION_X = TEXTBOX_AND_SLIDER_POSITION_X;
  private static final int PLAYER_NAME_TEXTBOX_POSITION_Y = INITIAL_POSITION_Y;
  private static final String PLAYER_NAME_TEXTBOX_DEFAULT_TEXT = "Player";
  private static final String PLAYER_NAME_TEXTBOX_LABEL_TEXT = "player name";


  // points to win slider properties
  private static final int POINTS_TO_WIN_SLIDER_POSITION_Y = INITIAL_POSITION_Y + SPACING;
  private static final String POINTS_TO_WIN_SLIDER_LABEL = "points to win";
  private static final int POINTS_TO_WIN_SLIDER_DEFAULT_VALUE = 3;
  private static final int POINTS_TO_WIN_SLIDER_MIN_VALUE = 1;
  public static final int MAX_POINTS_TO_WIN = 10;

  // difficulty slider properties
  private static final int DIFFICULTY_SLIDER_POSITION_Y = INITIAL_POSITION_Y + 2 * SPACING;
  private static final String DIFFICULTY_SLIDER_LABEL = "difficulty";
  private static final int DIFFICULTY_SLIDER_DEFAULT_VALUE = 5;
  private static final int DIFFICULTY_SLIDER_MIN_VALUE = 1;
  private static final int DIFFICULTY_SLIDER_MAX_VALUE = 10;

  // paddle size slider properties
  private static final int PADDLE_SIZE_SLIDER_POSITION_Y =
      INITIAL_POSITION_Y + 3 * SPACING;
  private static final String PADDLE_SIZE_SLIDER_LABEL = "paddle size";
  private static final int PADDLE_SIZE_SLIDER_DEFAULT_VALUE = 50;
  private static final int PADDLE_SIZE_SLIDER_MIN_VALUE = 1;
  private static final int PADDLE_SIZE_SLIDER_MAX_VALUE = 100;


  //back button properties
  private static final ClickAction BACK_BUTTON_ACTION = Controller::exitOptionsWithoutUpdate;
  private static final String BACK_BUTTON_LABEL = "Back";
  private static final int BACK_BUTTON_POSITION_X = 20;
  private static final int BACK_BUTTON_POSITION_Y = BUTTONS_POSITION_Y;

  //ok button properties
  private static final ClickAction OK_BUTTON_ACTION = Controller::exitOptionsWithUpdate;
  private static final String OK_BUTTON_LABEL = "OK";
  private static final int OK_BUTTON_POSITION_X = 200;
  private static final int OK_BUTTON_POSITION_Y = BUTTONS_POSITION_Y;

  // reset defaults button properties
  private static final ClickAction RESET_DEFAULTS_BUTTON_ACTION = Controller::resetGlobalDefaults;
  private static final String RESET_DEFAULTS_BUTTON_LABEL = "Reset";
  private static final int RESET_DEFAULTS_BUTTON_POSITION_X = 380;
  private static final int RESET_DEFAULTS_BUTTON_POSITION_Y = BUTTONS_POSITION_Y;


  private static final Color TITLE_COLOR = WHITE;
  private static final int TITLE_POSITION_X = 200;
  private static final int TITLE_POSITION_Y = 200;
  private static final String TITLE = "";
  private final MenuItem menuItems;
  private final SingleSlider pointsToWin;
  private final SingleSlider difficulty;
  private final SingleSlider paddleSize;
  private final Slider sliders;

  private final Textbox playerName;

  public Options() {
    playerName = buildPlayerNameTextbox();

    pointsToWin = buildPointsToWinSlider();
    difficulty = buildDifficultySlider();
    paddleSize = buildPaddleSizeSlider();
    sliders = new CompositeSlider(pointsToWin, difficulty, paddleSize);

    MenuItem backButton = buildBackButton();
    MenuItem okButton = buildOkButton();
    MenuItem resetDefaultsButton = buildResetDefaultsButton();

    menuItems = new CompositeMenuItem(backButton, okButton, resetDefaultsButton, sliders,
        playerName);
  }

  private Textbox buildPlayerNameTextbox() {
    return new Textbox.Builder()
        .positionX(PLAYER_NAME_TEXTBOX_POSITION_X)
        .positionY(PLAYER_NAME_TEXTBOX_POSITION_Y)
        .defaultText(PLAYER_NAME_TEXTBOX_DEFAULT_TEXT)
        .label(PLAYER_NAME_TEXTBOX_LABEL_TEXT)
        .build();
  }

  private SingleButton buildBackButton() {
    return SingleButton.builder()
        .action(BACK_BUTTON_ACTION)
        .label(BACK_BUTTON_LABEL)
        .positionX(BACK_BUTTON_POSITION_X)
        .positionY(BACK_BUTTON_POSITION_Y)
        .color(GREEN)
        .build();
  }

  private SingleButton buildOkButton() {
    return SingleButton.builder()
        .action(OK_BUTTON_ACTION)
        .label(OK_BUTTON_LABEL)
        .positionX(OK_BUTTON_POSITION_X)
        .positionY(OK_BUTTON_POSITION_Y)
        .color(GREEN)
        .build();
  }

  private SingleButton buildResetDefaultsButton() {
    return SingleButton.builder()
        .action(RESET_DEFAULTS_BUTTON_ACTION)
        .label(RESET_DEFAULTS_BUTTON_LABEL)
        .positionX(RESET_DEFAULTS_BUTTON_POSITION_X)
        .positionY(RESET_DEFAULTS_BUTTON_POSITION_Y)
        .color(GREEN)
        .build();
  }

  private SingleSlider buildPointsToWinSlider() {
    return new SingleSlider.Builder()
        .positionY(POINTS_TO_WIN_SLIDER_POSITION_Y)
        .label(POINTS_TO_WIN_SLIDER_LABEL)
        .defaultValue(POINTS_TO_WIN_SLIDER_DEFAULT_VALUE)
        .minValue(POINTS_TO_WIN_SLIDER_MIN_VALUE)
        .maxValue(MAX_POINTS_TO_WIN)
        .build();
  }

  private SingleSlider buildDifficultySlider() {
    return new SingleSlider.Builder()
        .positionY(DIFFICULTY_SLIDER_POSITION_Y)
        .label(DIFFICULTY_SLIDER_LABEL)
        .defaultValue(DIFFICULTY_SLIDER_DEFAULT_VALUE)
        .minValue(DIFFICULTY_SLIDER_MIN_VALUE)
        .maxValue(DIFFICULTY_SLIDER_MAX_VALUE)
        .build();
  }

  private SingleSlider buildPaddleSizeSlider() {
    return new SingleSlider.Builder()
        .positionY(PADDLE_SIZE_SLIDER_POSITION_Y)
        .label(PADDLE_SIZE_SLIDER_LABEL)
        .defaultValue(PADDLE_SIZE_SLIDER_DEFAULT_VALUE)
        .minValue(PADDLE_SIZE_SLIDER_MIN_VALUE)
        .maxValue(PADDLE_SIZE_SLIDER_MAX_VALUE)
        .build();
  }

  @Override
  public void onKeyPressed(KeyEvent e) {
    playerName.onKeyPressed(e);
  }

  @Override
  public void onMousePressed(int x, int y) {
    menuItems.onMousePressed(x, y);
  }

  @Override
  public void onMouseReleased(int x, int y) {
    menuItems.onMouseReleased(x, y);
  }

  @Override
  public void onMouseMoved(int x, int y) {
    menuItems.onMouseMoved(x, y);
  }

  @Override
  public void onDrag(int x, int y) {
    sliders.onDrag(x, y);
  }

  @Override
  public void drawSelf(Graphics g) {
    g.setColor(TITLE_COLOR);
    g.drawString(TITLE, TITLE_POSITION_X, TITLE_POSITION_Y);
    menuItems.drawSelf(g);
  }

  public String getPlayerName() {
    return playerName.getText();
  }

  public int getPointsToWin() {
    return pointsToWin.getValue();
  }

  public int getDifficulty() {
    return difficulty.getValue();
  }

  public int getPaddleSize() {
    return paddleSize.getValue();
  }

  @Override
  public boolean isFrames() {
    return false;
  }

  @Override
  public boolean dragActive() {
    return sliders.isActivated();
  }

  @Override
  public void onEnter(int mouseX, int mouseY) {
    menuItems.onMouseMoved(mouseX, mouseY);
  }

  public void submit() {
    playerName.submit();
    sliders.submit();
  }

  public void resetPrevious() {
    playerName.reset();
    sliders.reset();
  }

  public void resetDefaults() {
    playerName.resetDefaults();
    sliders.resetDefaults();
  }
}
