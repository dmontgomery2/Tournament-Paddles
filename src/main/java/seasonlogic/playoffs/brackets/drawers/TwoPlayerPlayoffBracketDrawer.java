package seasonlogic.playoffs.brackets.drawers;

import static java.awt.Color.WHITE;

import java.awt.Graphics;
import java.util.List;
import lombok.Builder;
import seasonlogic.playoffs.PlayoffSeries;
import seasonlogic.playoffs.brackets.PlayoffBracketDrawer;

@Builder
public
class TwoPlayerPlayoffBracketDrawer implements PlayoffBracketDrawer {

  private final List<PlayoffSeries> firstRoundSeries;

  @Override
  public void draw(Graphics g){
    g.setColor(WHITE);
    drawFirstRoundSeries(g);
    drawLines(g);
  }

  private void drawLines(Graphics g) {
    g.drawLine(100, 100, 300, 100);
    g.drawLine(100, 300, 300, 300);
    g.drawLine(300, 100, 300, 300);
    g.drawLine(300, 200, 400, 200);
  }

  private void drawFirstRoundSeries(Graphics g) {
    g.drawString(firstRoundSeries.get(0).getPlayer1().getName(), 105, 95);
    g.drawString(Integer.toString(firstRoundSeries.get(0).getPlayer1Wins()), 290, 95);
    g.drawString(firstRoundSeries.get(0).getPlayer2().getName(), 105, 295);
    g.drawString(Integer.toString(firstRoundSeries.get(0).getPlayer2Wins()), 290, 295);
  }

}
