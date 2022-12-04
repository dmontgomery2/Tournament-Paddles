package menus.pages;

import java.awt.Graphics;
import java.util.function.Consumer;
import menus.Menus;
import menus.Page;
import menus.menuitems.CompositeMenuItem;
import menus.menuitems.MenuItem;
import menus.menuitems.SingleButton;

public class MainMenu implements Page {

  private static final int BUTTONS_POSITION_X = 200;
  private static final int FIRST_BUTTON_POSITION_Y = 65;
  private static final int BUTTONS_SPACING_Y = 140;

  private static final Consumer<Menus> EXHIBITION_BUTTON_ACTION = Menus::playExhibition;
  private static final String EXHIBITION_BUTTON_LABEL = "Exhibition";
  private static final int EXHIBITION_BUTTON_POSITION_X = BUTTONS_POSITION_X;
  private static final int EXHIBITION_BUTTON_POSITION_Y = FIRST_BUTTON_POSITION_Y;
  private static final Consumer<Menus> SEASON_BUTTON_ACTION = Menus::goToSeasonOptions;
  private static final String SEASON_BUTTON_LABEL = "Season";
  private static final int SEASON_BUTTON_POSITION_X = BUTTONS_POSITION_X;
  private static final int SEASON_BUTTON_POSITION_Y = FIRST_BUTTON_POSITION_Y + BUTTONS_SPACING_Y;
  private static final Consumer<Menus> CONTINUE_BUTTON_ACTION = Menus::onContinue;
  private static final String CONTINUE_BUTTON_LABEL = "Continue";
  private static final int CONTINUE_BUTTON_POSITION_X = BUTTONS_POSITION_X;
  private static final int CONTINUE_BUTTON_POSITION_Y =
      FIRST_BUTTON_POSITION_Y + 2 * BUTTONS_SPACING_Y;
  private static final Consumer<Menus> OPTIONS_BUTTON_ACTION = Menus::goToOptions;
  private static final String OPTIONS_BUTTON_LABEL = "Options";
  private static final int OPTIONS_BUTTON_POSITION_X = BUTTONS_POSITION_X;
  private static final int OPTIONS_BUTTON_POSITION_Y =
      FIRST_BUTTON_POSITION_Y + 3 * BUTTONS_SPACING_Y;
  private final MenuItem menuItems;


  public MainMenu() {

    MenuItem exhibitionButton = SingleButton.builder()
        .action(EXHIBITION_BUTTON_ACTION)
        .label(EXHIBITION_BUTTON_LABEL)
        .positionX(EXHIBITION_BUTTON_POSITION_X)
        .positionY(EXHIBITION_BUTTON_POSITION_Y)
        .build();

    MenuItem seasonButton = SingleButton.builder()
        .action(SEASON_BUTTON_ACTION)
        .label(SEASON_BUTTON_LABEL)
        .positionX(SEASON_BUTTON_POSITION_X)
        .positionY(SEASON_BUTTON_POSITION_Y)
        .build();

    MenuItem continueButton = SingleButton.builder()
        .action(CONTINUE_BUTTON_ACTION)
        .label(CONTINUE_BUTTON_LABEL)
        .positionX(CONTINUE_BUTTON_POSITION_X)
        .positionY(CONTINUE_BUTTON_POSITION_Y)
        .build();

    MenuItem optionsButton = SingleButton.builder()
        .action(OPTIONS_BUTTON_ACTION)
        .label(OPTIONS_BUTTON_LABEL)
        .positionX(OPTIONS_BUTTON_POSITION_X)
        .positionY(OPTIONS_BUTTON_POSITION_Y)
        .build();

    menuItems = new CompositeMenuItem(exhibitionButton, seasonButton, continueButton,
        optionsButton);
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
  public void onMouseReleased() {
    menuItems.onMouseReleased();
  }

  @Override
  public boolean isFrames() {
    return false;
  }
}
