package controller.pages;

import common.functionalinterfaces.ClickAction;
import controller.Controller;
import controller.Page;
import controller.menuitems.CompositeMenuItem;
import controller.menuitems.MenuItem;
import controller.menuitems.SingleButton;
import java.awt.Color;
import java.awt.Graphics;

public class MainMenu implements Page {

  private static final int BUTTONS_POSITION_X = 200;
  private static final int FIRST_BUTTON_POSITION_Y = 65;
  private static final int BUTTONS_SPACING_Y = 140;

  private static final ClickAction EXHIBITION_BUTTON_ACTION = Controller::playExhibition;
  private static final String EXHIBITION_BUTTON_LABEL = "Exhibition";
  private static final int EXHIBITION_BUTTON_POSITION_X = BUTTONS_POSITION_X;
  private static final int EXHIBITION_BUTTON_POSITION_Y = FIRST_BUTTON_POSITION_Y;
  private static final ClickAction SEASON_BUTTON_ACTION = Controller::goToSeasonOptions;
  private static final String SEASON_BUTTON_LABEL = "Season";
  private static final int SEASON_BUTTON_POSITION_X = BUTTONS_POSITION_X;
  private static final int SEASON_BUTTON_POSITION_Y = FIRST_BUTTON_POSITION_Y + BUTTONS_SPACING_Y;
  private static final ClickAction CONTINUE_BUTTON_ACTION = Controller::onContinue;
  private static final String CONTINUE_BUTTON_LABEL = "Continue";
  private static final int CONTINUE_BUTTON_POSITION_X = BUTTONS_POSITION_X;
  private static final int CONTINUE_BUTTON_POSITION_Y =
      FIRST_BUTTON_POSITION_Y + 2 * BUTTONS_SPACING_Y;
  private static final ClickAction OPTIONS_BUTTON_ACTION = Controller::goToOptions;
  private static final String OPTIONS_BUTTON_LABEL = "Options";
  private static final int OPTIONS_BUTTON_POSITION_X = BUTTONS_POSITION_X;
  private static final int OPTIONS_BUTTON_POSITION_Y =
      FIRST_BUTTON_POSITION_Y + 3 * BUTTONS_SPACING_Y;
  private final MenuItem menuItems;


  public MainMenu() {

    MenuItem exhibitionButton = buildExhibitionButton();
    MenuItem seasonButton = buildSeasonButton();
    MenuItem continueButton = buildContinueButton();
    MenuItem optionsButton = buildOptionsButton();

    menuItems = new CompositeMenuItem(exhibitionButton, seasonButton, continueButton,
        optionsButton);
  }

  private SingleButton buildExhibitionButton() {
    return SingleButton.builder()
        .action(EXHIBITION_BUTTON_ACTION)
        .label(EXHIBITION_BUTTON_LABEL)
        .positionX(EXHIBITION_BUTTON_POSITION_X)
        .positionY(EXHIBITION_BUTTON_POSITION_Y)
        .color(Color.GREEN)
        .build();
  }

  private SingleButton buildSeasonButton() {
    return SingleButton.builder()
        .action(SEASON_BUTTON_ACTION)
        .label(SEASON_BUTTON_LABEL)
        .positionX(SEASON_BUTTON_POSITION_X)
        .positionY(SEASON_BUTTON_POSITION_Y)
        .color(Color.GREEN)
        .build();
  }

  private SingleButton buildContinueButton() {
    return SingleButton.builder()
        .action(CONTINUE_BUTTON_ACTION)
        .label(CONTINUE_BUTTON_LABEL)
        .positionX(CONTINUE_BUTTON_POSITION_X)
        .positionY(CONTINUE_BUTTON_POSITION_Y)
        .color(Color.GREEN)
        .build();
  }

  private SingleButton buildOptionsButton() {
    return SingleButton.builder()
        .action(OPTIONS_BUTTON_ACTION)
        .label(OPTIONS_BUTTON_LABEL)
        .positionX(OPTIONS_BUTTON_POSITION_X)
        .positionY(OPTIONS_BUTTON_POSITION_Y)
        .color(Color.GREEN)
        .build();
  }

  @Override
  public void drawSelf(Graphics g) {
    menuItems.drawSelf(g);
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
  public boolean isFrames() {
    return false;
  }
  @Override
  public void onEnter(int mouseX, int mouseY) {
    menuItems.onMouseMoved(mouseX, mouseY);
  }
}
