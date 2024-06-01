package controller.pages.seasonpages;

import static java.awt.Color.GREEN;
import static java.awt.Color.WHITE;

import common.functionalinterfaces.ClickAction;
import controller.Controller;
import controller.menuitems.MenuItem;
import controller.menuitems.SingleButton;
import java.awt.Graphics;
import seasonlogic.Season;
import seasonlogic.models.Human;

public class ChampionPage implements SeasonPage {

  private static final int MESSAGE_POSITION_X = 200;
  private static final int MESSAGE_POSITION_Y = 300;
  private static final ClickAction BACK_BUTTON_ACTION = Controller::goToMainMenu;
  private static final String BACK_BUTTON_LABEL = "Back";
  private static final int BACK_BUTTON_POSITION_X = 20;
  private static final int BACK_BUTTON_POSITION_Y = 480;
  private final MenuItem backButton;
  private final Season season;

  public ChampionPage(Season season) {
    backButton = SingleButton.builder()
        .action(BACK_BUTTON_ACTION)
        .label(BACK_BUTTON_LABEL)
        .positionX(BACK_BUTTON_POSITION_X)
        .positionY(BACK_BUTTON_POSITION_Y)
        .color(GREEN)
        .build();
    this.season = season;
  }

  @Override
  public void drawSelf(Graphics g) {
    boolean humanWon = season.getChampion() instanceof Human;
    g.setColor(humanWon ? GREEN : WHITE);
    g.drawString(season.getChampion().getName() + " is the champion!", MESSAGE_POSITION_X,
        MESSAGE_POSITION_Y);
    backButton.drawSelf(g);
    if (humanWon && season.isMaximumDifficulty()) {
      drawSecretMessage(g);
    }
  }

  private void drawSecretMessage(Graphics g) {
    g.setColor(GREEN);
    g.drawString("Created by Daniel Montgomery", 200, 400);
    g.drawString("Thanks so much for playing!", 200, 500);
  }

  @Override
  public boolean isFrames() {
    return false;
  }

  @Override
  public SeasonPage advance(int mouseX, int mouseY) {
    return this;
  }

  @Override
  public void onMousePressed(int x, int y) {
    backButton.onMousePressed(x, y);
  }

  @Override
  public void onMouseMoved(int x, int y) {
    backButton.onMouseMoved(x, y);
  }

  @Override
  public void onMouseReleased(int x, int y) {
    backButton.onMouseReleased(x, y);
  }

  @Override
  public void onEnter(int mouseX, int mouseY) {
    backButton.onMouseMoved(mouseX, mouseY);
  }
}
