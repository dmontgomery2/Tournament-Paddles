package controller.pages.seasonpages;

import java.awt.Graphics;
import seasonlogic.Season;

public class SeasonStatusPage extends SeasonStatsPage implements SeasonPage {

  private final Season season;

  public SeasonStatusPage(Season season) {
    this.season = season;
  }

  @Override
  public void drawSelf(Graphics g) {
    drawButtons(g);
    season.getStatus().drawSelf(g);
  }

  @Override
  public SeasonPage advance(int mouseX, int mouseY) {
    if (season.getChampion() != null) {
      return new ChampionPage(season);
    }
    return new SeasonMatchups(season);
  }
}
