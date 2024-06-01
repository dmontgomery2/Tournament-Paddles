package seasonlogic.regularseason;

import static java.awt.Color.GREEN;
import static java.awt.Color.WHITE;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import seasonlogic.SeasonStatus;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;

class SeasonStandings implements SeasonStatus, Serializable {

  private static final String NAME_COLUMN_LABEL = "Name";
  private static final String WINS_COLUMN_LABEL = "W";
  private static final String LOSSES_COLUMN_LABEL = "L";
  private static final String LAST_10_COLUMN_LABEL = "L10";
  private static final String STREAK_COLUMN_LABEL = "Strk";
  private static final int ROW_HEIGHT = 20;
  private static final int FIRST_ROW_HEIGHT = 30;
  private static final int COLUMN_WIDTH = 100;

  private static final int INITIAL_X = 30;
  private static final int NAME_X = INITIAL_X;
  private static final int W_X = INITIAL_X + COLUMN_WIDTH;
  private static final int L_X = INITIAL_X + 2 * COLUMN_WIDTH;
  private static final int L10_X = INITIAL_X + 3 * COLUMN_WIDTH;
  private static final int STRK_X = INITIAL_X + 4 * COLUMN_WIDTH;
  private static final int INITIAL_Y = 80;


  private final List<PlayerProfile> players;

  public SeasonStandings(List<PlayerProfile> players) {
    this.players = players;
  }

  @Override
  public void drawSelf(Graphics g) {
    Collections.sort(players);
    int currentY = INITIAL_Y;
    g.setColor(WHITE);
    g.drawString(NAME_COLUMN_LABEL, NAME_X, currentY);
    g.drawString(WINS_COLUMN_LABEL, W_X, currentY);
    g.drawString(LOSSES_COLUMN_LABEL, L_X, currentY);
    g.drawString(LAST_10_COLUMN_LABEL, L10_X, currentY);
    g.drawString(STREAK_COLUMN_LABEL, STRK_X, currentY);
    currentY += FIRST_ROW_HEIGHT;
    for (PlayerProfile player : players) {
      if (player instanceof Human) {
        g.setColor(GREEN);
      }
      g.drawString(player.getName(), NAME_X, currentY);
      g.drawString(player.getSeasonWins(), W_X, currentY);
      g.drawString(player.getSeasonLosses(), L_X, currentY);
      g.drawString(player.getLast10(), L10_X, currentY);
      g.drawString(player.getStreak(), STRK_X, currentY);
      currentY += ROW_HEIGHT;
      g.setColor(WHITE);
    }
  }
}
