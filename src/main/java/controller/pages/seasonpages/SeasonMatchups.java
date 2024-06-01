package controller.pages.seasonpages;

import java.awt.Graphics;
import seasonlogic.Season;

public class SeasonMatchups extends MatchesPage implements SeasonPage {

  private final Season season;

  public SeasonMatchups(Season season) {
    this.season = season;
  }

  @Override
  public void drawSelf(Graphics g) {
    drawDay(season.getDay(), g);
    drawMatches(season.getMatchups(), g);
    drawButtons(g);
  }

  @Override
  public SeasonPage advance(int mouseX, int mouseY) {
    season.simulateMatchups();
    if (season.isHumanActive()) {
      return new SeasonPlayingField(season, mouseX, mouseY);
    }
    return new SeasonResults(season);
  }

}
