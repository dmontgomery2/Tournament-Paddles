package menus.pages.seasonpages;

import java.awt.Graphics;
import java.util.function.Consumer;
import menus.Menus;
import menus.menuitems.CompositeMenuItem;
import menus.menuitems.MenuItem;
import menus.menuitems.SingleButton;

public abstract class SeasonStatsPage implements SeasonPage {

  private static final int BUTTONS_POSITION_Y = 480;
  private static final Consumer<Menus> BACK_BUTTON_ACTION = Menus::goToMainMenu;
  private static final String BACK_BUTTON_LABEL = "Back";
  private static final int BACK_BUTTON_POSITION_X = 20;
  private static final int BACK_BUTTON_POSITION_Y = BUTTONS_POSITION_Y;
  private static final Consumer<Menus> NEXT_BUTTON_ACTION = Menus::advanceSeasonPages;
  private static final String NEXT_BUTTON_LABEL = "Next";
  private static final int NEXT_BUTTON_POSITION_X = 200;
  private static final int NEXT_BUTTON_POSITION_Y = BUTTONS_POSITION_Y;

  private MenuItem buttons;

  public SeasonStatsPage(){
    MenuItem backButton = SingleButton.builder()
        .action(BACK_BUTTON_ACTION)
        .label(BACK_BUTTON_LABEL)
        .positionX(BACK_BUTTON_POSITION_X)
        .positionY(BACK_BUTTON_POSITION_Y)
        .build();

    MenuItem nextButton = SingleButton.builder()
        .action(NEXT_BUTTON_ACTION)
        .label(NEXT_BUTTON_LABEL)
        .positionX(NEXT_BUTTON_POSITION_X)
        .positionY(NEXT_BUTTON_POSITION_Y)
        .build();

    buttons = new CompositeMenuItem(backButton, nextButton);
  }

  public void drawButtons(Graphics g){
    buttons.drawSelf(g);
  }

  @Override
  public void onMousePressed(int x, int y) {
    buttons.onMousePressed(x, y);
  }

  @Override
  public boolean isFrames() {
    return false;
  }

}
