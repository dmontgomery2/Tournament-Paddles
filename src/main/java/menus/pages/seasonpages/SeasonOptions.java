package menus.pages.seasonpages;

import java.awt.Graphics;
import java.util.function.Consumer;
import menus.Menus;
import menus.menuitems.CompositeMenuItem;
import menus.menuitems.CompositeSlider;
import menus.menuitems.MenuItem;
import menus.menuitems.SingleButton;
import menus.menuitems.SingleSlider;
import menus.menuitems.Slider;
import seasonlogic.season.RegularSeason;
import settings.GlobalSettings;
import settings.SeasonSettings;

public class SeasonOptions implements SeasonPage {

  private final GlobalSettings globalSettings;

  private static final int BUTTONS_POSITION_Y = 480;

  // back button properties
  private static final Consumer<Menus> BACK_BUTTON_ACTION = Menus::goToMainMenu;
  private static final String BACK_BUTTON_LABEL = "Back";
  private static final int BACK_BUTTON_POSITION_X = 20;
  private static final int BACK_BUTTON_POSITION_Y = BUTTONS_POSITION_Y;


  // next button properties
  private static final Consumer<Menus> NEXT_BUTTON_ACTION = Menus::startNewSeason;
  private static final String NEXT_BUTTON_LABEL = "Next";
  private static final int NEXT_BUTTON_POSITION_X = 200;
  private static final int NEXT_BUTTON_POSITION_Y = BUTTONS_POSITION_Y;

  // reset defaults button properties
  private static final Consumer<Menus> RESET_DEFAULTS_BUTTON_ACTION = Menus::resetSeasonDefaults;
  private static final String RESET_DEFAULTS_BUTTON_LABEL = "Reset";
  private static final int RESET_DEFAULTS_BUTTON_POSITION_X = 380;
  private static final int RESET_DEFAULTS_BUTTON_POSITION_Y = BUTTONS_POSITION_Y;


  private static final int FIRST_SLIDER_POSITION_Y = 70;
  private static final int SLIDERS_SPACING = 70;

  // number of players slider properties
  private static final int NUMBER_OF_PLAYERS_SLIDER_POSITION_Y = FIRST_SLIDER_POSITION_Y;
  private static final String NUMBER_OF_PLAYERS_SLIDER_LABEL = "number of players";
  private static final int NUMBER_OF_PLAYERS_SLIDER_DEFAULT_VALUE = 6;
  private static final int NUMBER_OF_PLAYERS_SLIDER_MIN_VALUE = 2;
  private static final int NUMBER_OF_PLAYERS_SLIDER_MAX_VALUE = 16;
  private static final int NUMBER_OF_PLAYERS_SLIDER_INCREMENT = 2;

  // difficulty slider properties

  private static final int DIFFICULTY_SLIDER_POSITION_Y = FIRST_SLIDER_POSITION_Y + SLIDERS_SPACING;
  private static final String DIFFICULTY_SLIDER_LABEL = "difficulty";
  private static final int DIFFICULTY_SLIDER_DEFAULT_VALUE = 5;
  private static final int DIFFICULTY_SLIDER_MIN_VALUE = 1;
  private static final int DIFFICULTY_SLIDER_MAX_VALUE = 10;

  // number of games slider properties

  private static final int NUMBER_OF_GAMES_SLIDER_POSITION_Y =
      FIRST_SLIDER_POSITION_Y + 2 * SLIDERS_SPACING;
  private static final String NUMBER_OF_GAMES_SLIDER_LABEL = "number of games";
  private static final int NUMBER_OF_GAMES_SLIDER_DEFAULT_VALUE = 5;
  private static final int NUMBER_OF_GAMES_SLIDER_MIN_VALUE = 1;
  private static final int NUMBER_OF_GAMES_SLIDER_MAX_VALUE = 10;

  // round 1 slider properties

  private static final int ROUND_1_SLIDER_POSITION_Y =
      FIRST_SLIDER_POSITION_Y + 3 * SLIDERS_SPACING;
  private static final String ROUND_1_SLIDER_LABEL = "round 1";
  private static final int ROUND_1_SLIDER_DEFAULT_VALUE = 3;
  private static final int ROUND_1_SLIDER_MIN_VALUE = 1;
  private static final int ROUND_1_SLIDER_MAX_VALUE = 7;
  private static final int ROUND_1_SLIDER_INCREMENT = 2;

  // round 2 slider properties

  private static final int ROUND_2_SLIDER_POSITION_Y =
      FIRST_SLIDER_POSITION_Y + 4 * SLIDERS_SPACING;
  private static final String ROUND_2_SLIDER_LABEL = "round 2";
  private static final int ROUND_2_SLIDER_DEFAULT_VALUE = 3;
  private static final int ROUND_2_SLIDER_MIN_VALUE = 1;
  private static final int ROUND_2_SLIDER_MAX_VALUE = 7;
  private static final int ROUND_2_SLIDER_INCREMENT = 2;

  // round 3 slider properties

  private static final int ROUND_3_SLIDER_POSITION_Y =
      FIRST_SLIDER_POSITION_Y + 5 * SLIDERS_SPACING;
  private static final String ROUND_3_SLIDER_LABEL = "round 3";
  private static final int ROUND_3_SLIDER_DEFAULT_VALUE = 3;
  private static final int ROUND_3_SLIDER_MIN_VALUE = 1;
  private static final int ROUND_3_SLIDER_MAX_VALUE = 7;
  private static final int ROUND_3_SLIDER_INCREMENT = 2;

  private final RegularSeason regularSeason;

  private final MenuItem menuItems;
  private final SingleSlider numberOfPlayers;
  private final SingleSlider difficulty;
  private final SingleSlider numberOfGames;
  private final SingleSlider round1;
  private final SingleSlider round2;
  private final SingleSlider round3;

  private final Slider sliders;

  public SeasonOptions(GlobalSettings globalSettings) {
    this.globalSettings = globalSettings;
    regularSeason = new RegularSeason();
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
    MenuItem resetDefaultsButton = SingleButton.builder()
        .action(RESET_DEFAULTS_BUTTON_ACTION)
        .label(RESET_DEFAULTS_BUTTON_LABEL)
        .positionX(RESET_DEFAULTS_BUTTON_POSITION_X)
        .positionY(RESET_DEFAULTS_BUTTON_POSITION_Y)
        .build();
    numberOfPlayers = new SingleSlider.Builder()
        .positionY(NUMBER_OF_PLAYERS_SLIDER_POSITION_Y)
        .label(NUMBER_OF_PLAYERS_SLIDER_LABEL)
        .defaultValue(NUMBER_OF_PLAYERS_SLIDER_DEFAULT_VALUE)
        .minValue(NUMBER_OF_PLAYERS_SLIDER_MIN_VALUE)
        .maxValue(NUMBER_OF_PLAYERS_SLIDER_MAX_VALUE)
        .increment(NUMBER_OF_PLAYERS_SLIDER_INCREMENT)
        .build();
    difficulty = new SingleSlider.Builder()
        .positionY(DIFFICULTY_SLIDER_POSITION_Y)
        .label(DIFFICULTY_SLIDER_LABEL)
        .defaultValue(DIFFICULTY_SLIDER_DEFAULT_VALUE)
        .minValue(DIFFICULTY_SLIDER_MIN_VALUE)
        .maxValue(DIFFICULTY_SLIDER_MAX_VALUE)
        .build();
    numberOfGames = new SingleSlider.Builder()
        .positionY(NUMBER_OF_GAMES_SLIDER_POSITION_Y)
        .label(NUMBER_OF_GAMES_SLIDER_LABEL)
        .defaultValue(NUMBER_OF_GAMES_SLIDER_DEFAULT_VALUE)
        .minValue(NUMBER_OF_GAMES_SLIDER_MIN_VALUE)
        .maxValue(NUMBER_OF_GAMES_SLIDER_MAX_VALUE)
        .build();
    round1 = new SingleSlider.Builder()
        .positionY(ROUND_1_SLIDER_POSITION_Y)
        .label(ROUND_1_SLIDER_LABEL)
        .defaultValue(ROUND_1_SLIDER_DEFAULT_VALUE)
        .minValue(ROUND_1_SLIDER_MIN_VALUE)
        .maxValue(ROUND_1_SLIDER_MAX_VALUE)
        .increment(ROUND_1_SLIDER_INCREMENT)
        .build();
    round2 = new SingleSlider.Builder()
        .positionY(ROUND_2_SLIDER_POSITION_Y)
        .label(ROUND_2_SLIDER_LABEL)
        .defaultValue(ROUND_2_SLIDER_DEFAULT_VALUE)
        .minValue(ROUND_2_SLIDER_MIN_VALUE)
        .maxValue(ROUND_2_SLIDER_MAX_VALUE)
        .increment(ROUND_2_SLIDER_INCREMENT)
        .build();
    round3 = new SingleSlider.Builder()
        .positionY(ROUND_3_SLIDER_POSITION_Y)
        .label(ROUND_3_SLIDER_LABEL)
        .defaultValue(ROUND_3_SLIDER_DEFAULT_VALUE)
        .minValue(ROUND_3_SLIDER_MIN_VALUE)
        .maxValue(ROUND_3_SLIDER_MAX_VALUE)
        .increment(ROUND_3_SLIDER_INCREMENT)
        .build();
    sliders = new CompositeSlider(numberOfPlayers, difficulty, numberOfGames, round1, round2,
        round3);
    menuItems = new CompositeMenuItem(nextButton, backButton, resetDefaultsButton, sliders);

  }

  public void setSettings() {
    SeasonSettings settings = new SeasonSettings();
    settings.setNumberOfPlayers(numberOfPlayers.getValue());
    settings.setPointsToWin(globalSettings.getPointsToWin());
    settings.setDifficulty(difficulty.getValue());
    settings.setNumberOfGames(numberOfGames.getValue());
    settings.setRound1(round1.getValue());
    settings.setRound2(round2.getValue());
    settings.setRound3(round3.getValue());
    settings.setPlayerName(globalSettings.getPlayerName());
    settings.setPaddleSize(globalSettings.getPaddleSize());
    regularSeason.setSettings(settings);
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
  public void onDrag(int x, int y) {
    sliders.onDrag(x, y);
  }

  @Override
  public void drawSelf(Graphics g) {
    menuItems.drawSelf(g);
  }

  @Override
  public SeasonPage advance() {
    setSettings();
    return new SeasonStatusPage(regularSeason);
  }

  @Override
  public boolean isFrames() {
    return false;
  }

  @Override
  public boolean dragActive() {
    return sliders.isActivated();
  }

  public void resetDefaults() {
    sliders.resetDefaults();
  }
}
