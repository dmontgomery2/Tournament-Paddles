package controller.pages.seasonpages;

import gameplay.PlayingField;
import seasonlogic.Season;
import seasonlogic.models.PlayerProfile;

public class SeasonPlayingField extends PlayingField implements SeasonPage {

  private static final int DEFAULT_DIFFICULTY = 5;
  private final Season season;

  public SeasonPlayingField(Season season, int mouseX, int mouseY) {
    super(new PlayingField.Builder()
        .pointsToWin(season.getPointsToWin())
        .paddleSize(season.getPaddleSize())
        .difficulty(season
            .getOpponentForHuman()
            .map(PlayerProfile::getDifficulty)
            .orElse(DEFAULT_DIFFICULTY))
        .mouseX(mouseX)
        .mouseY(mouseY));
    this.season = season;
  }

  @Override
  public SeasonPage advance(int mouseX, int mouseY) {
    season.recordGameplayResult(getGameplayResult());
    return new SeasonResults(season);
  }

}
