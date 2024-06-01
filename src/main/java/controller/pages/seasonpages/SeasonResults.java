package controller.pages.seasonpages;

import filehandling.FileWriter;
import filehandling.SaveData;
import java.awt.Graphics;
import seasonlogic.Season;

public class SeasonResults extends MatchesPage implements SeasonPage {

  private Season season;

  public SeasonResults(Season season) {
    this.season = season;
  }

  @Override
  public void drawSelf(Graphics g) {
    drawDay(season.getDay(), g);
    drawResults(season.getMatchups(), g);
    drawButtons(g);
  }

  @Override
  public SeasonPage advance(int mouseX, int mouseY) {
    advanceSeason();
    saveSeason();
    return new SeasonStatusPage(season);
  }

  private void advanceSeason() {
    season = season.advance();
  }

  private void saveSeason() {
    FileWriter.writeToFile(SaveData.builder()
        .season(season)
        .build());
  }

  @Override
  public boolean isFrames() {
    return false;
  }
}
