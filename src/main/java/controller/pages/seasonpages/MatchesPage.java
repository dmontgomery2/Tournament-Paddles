package controller.pages.seasonpages;


import static java.awt.Color.GREEN;
import static java.awt.Color.WHITE;

import java.awt.Graphics;
import java.util.List;
import seasonlogic.calendar.Matchup;

public abstract class MatchesPage extends SeasonStatsPage implements SeasonPage {

  private static final int DAY_POSITION_X = 20;
  private static final int DAY_POSITION_Y = 60;

  private static final int MATCHES_POSITION_X = 20;
  private static final int MATCHES_INITIAL_POSITION_Y = 100;
  private static final int MATCHES_SPACING = 30;

  public void drawDay(String day, Graphics g) {
    g.setColor(WHITE);
    g.drawString("Day " + day, DAY_POSITION_X, DAY_POSITION_Y);
  }

  public void drawMatches(List<Matchup> matchups, Graphics g) {
    g.setColor(WHITE);
    int y = MATCHES_INITIAL_POSITION_Y;
    for (Matchup matchup : matchups) {
      if (matchup.containsHuman()) {
        g.setColor(GREEN);
      }
      g.drawString(matchup.toString(), MATCHES_POSITION_X, y);
      y += MATCHES_SPACING;
      g.setColor(WHITE);
    }
  }

  public void drawResults(List<Matchup> matchups, Graphics g) {
    g.setColor(WHITE);
    int y = MATCHES_INITIAL_POSITION_Y;
    for (Matchup matchup : matchups) {
      if (matchup.containsHuman()) {
        g.setColor(GREEN);
      }
      g.drawString(matchup.getResult(), MATCHES_POSITION_X, y);
      y += MATCHES_SPACING;
      g.setColor(WHITE);
    }
  }

}
