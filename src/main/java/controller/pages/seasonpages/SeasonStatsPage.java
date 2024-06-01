package controller.pages.seasonpages;

import static java.awt.Color.GREEN;

import common.functionalinterfaces.ClickAction;
import controller.Controller;
import controller.menuitems.CompositeMenuItem;
import controller.menuitems.MenuItem;
import controller.menuitems.SingleButton;
import java.awt.Graphics;

public abstract class SeasonStatsPage implements SeasonPage {

  private static final int BUTTONS_POSITION_Y = 480;
  private static final ClickAction BACK_BUTTON_ACTION = Controller::goToMainMenu;
  private static final String BACK_BUTTON_LABEL = "Back";
  private static final int BACK_BUTTON_POSITION_X = 20;
  private static final int BACK_BUTTON_POSITION_Y = BUTTONS_POSITION_Y;
  private static final ClickAction NEXT_BUTTON_ACTION = Controller::advanceSeasonPages;
  private static final String NEXT_BUTTON_LABEL = "Next";
  private static final int NEXT_BUTTON_POSITION_X = 200;
  private static final int NEXT_BUTTON_POSITION_Y = BUTTONS_POSITION_Y;

  private final MenuItem buttons;

  public SeasonStatsPage() {
    MenuItem backButton = SingleButton.builder()
        .action(BACK_BUTTON_ACTION)
        .label(BACK_BUTTON_LABEL)
        .positionX(BACK_BUTTON_POSITION_X)
        .positionY(BACK_BUTTON_POSITION_Y)
        .color(GREEN)
        .build();

    MenuItem nextButton = SingleButton.builder()
        .action(NEXT_BUTTON_ACTION)
        .label(NEXT_BUTTON_LABEL)
        .positionX(NEXT_BUTTON_POSITION_X)
        .positionY(NEXT_BUTTON_POSITION_Y)
        .color(GREEN)
        .build();

    buttons = new CompositeMenuItem(backButton, nextButton);
  }

  public void drawButtons(Graphics g) {
    buttons.drawSelf(g);
  }

  @Override
  public void onMousePressed(int x, int y) {
    buttons.onMousePressed(x, y);
  }

  @Override
  public void onMouseMoved(int x, int y) {
    buttons.onMouseMoved(x, y);
  }

  @Override
  public void onMouseReleased(int x, int y) {
    buttons.onMouseReleased(x, y);
  }

  @Override
  public void onEnter(int mouseX, int mouseY) {
    buttons.onMouseMoved(mouseX, mouseY);
  }

  @Override
  public boolean isFrames() {
    return false;
  }

}
