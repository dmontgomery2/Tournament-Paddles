package menus.pages.seasonpages;

import static java.awt.Color.WHITE;

import java.awt.Graphics;
import java.util.function.Consumer;
import menus.Menus;
import menus.menuitems.MenuItem;
import menus.menuitems.SingleButton;
import seasonlogic.Season;

public class ChampionPage implements SeasonPage {

  private static final int MESSAGE_POSITION_X = 200;
  private static final int MESSAGE_POSITION_Y = 300;
  private static final Consumer<Menus> BACK_BUTTON_ACTION = Menus::goToMainMenu;
  private static final String BACK_BUTTON_LABEL = "Back";
  private static final int BACK_BUTTON_POSITION_X = 20;
  private static final int BACK_BUTTON_POSITION_Y = 420;
  private final MenuItem backButton;
  private final Season season;

  public ChampionPage(Season season) {
    backButton = SingleButton.builder()
        .action(BACK_BUTTON_ACTION)
        .label(BACK_BUTTON_LABEL)
        .positionX(BACK_BUTTON_POSITION_X)
        .positionY(BACK_BUTTON_POSITION_Y)
        .build();
    this.season = season;
  }

  @Override
  public void drawSelf(Graphics g) {
    g.setColor(WHITE);
    g.drawString(season.getChampion().getName() + " is the champion!", MESSAGE_POSITION_X,
        MESSAGE_POSITION_Y);
    backButton.drawSelf(g);
  }

  @Override
  public boolean isFrames() {
    return false;
  }

  @Override
  public SeasonPage advance() {
    return this;
  }

  @Override
  public void onMousePressed(int x, int y) {
    backButton.onMousePressed(x, y);
  }
}
