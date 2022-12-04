package seasonlogic.playoffs.brackets.drawers;

import static java.awt.Color.WHITE;

import java.awt.Graphics;
import java.util.List;
import lombok.Builder;
import seasonlogic.playoffs.PlayoffSeries;
import seasonlogic.playoffs.brackets.PlayoffBracketDrawer;

@Builder
public
class FourPlayerPlayoffBracketDrawer implements PlayoffBracketDrawer {

  private final List<PlayoffSeries> firstRoundSeries;
  private final List<PlayoffSeries> secondRoundSeries;

  @Override
  public void draw(Graphics g) {
    g.setColor(WHITE);
    drawFirstRoundSeries(g);
    if(!secondRoundSeries.isEmpty()){
      drawSecondRoundSeries(g);
    }

    drawLines(g);
  }

  private void drawLines(Graphics g) {
    g.drawLine(100, 100, 200, 100);
    g.drawLine(100, 200, 200, 200);
    g.drawLine(200, 100, 200, 200);

    g.drawLine(100, 300, 200, 300);
    g.drawLine(100, 400, 200, 400);
    g.drawLine(200, 300, 200, 400);

    g.drawLine(200, 150, 300, 150);
    g.drawLine(200, 350, 300, 350);
    g.drawLine(300, 150, 300, 350);

    g.drawLine(300, 250, 400, 250);
  }

  private void drawFirstRoundSeries(Graphics g) {
    g.drawString(firstRoundSeries.get(0).getPlayer1().getName(), 105, 95);
    g.drawString(Integer.toString(firstRoundSeries.get(0).getPlayer1Wins()), 200, 100);
    g.drawString(firstRoundSeries.get(0).getPlayer2().getName(), 105, 195);
    g.drawString(Integer.toString(firstRoundSeries.get(0).getPlayer2Wins()), 200, 200);

    g.drawString(firstRoundSeries.get(1).getPlayer1().getName(), 105, 295);
    g.drawString(Integer.toString(firstRoundSeries.get(1).getPlayer1Wins()), 200, 300);
    g.drawString(firstRoundSeries.get(1).getPlayer2().getName(), 105, 395);
    g.drawString(Integer.toString(firstRoundSeries.get(1).getPlayer2Wins()), 200, 400);
  }

  private void drawSecondRoundSeries(Graphics g) {
    g.drawString(secondRoundSeries.get(0).getPlayer1().getName(), 205, 145);
    g.drawString(Integer.toString(secondRoundSeries.get(0).getPlayer1Wins()), 300, 150);
    g.drawString(secondRoundSeries.get(0).getPlayer2().getName(), 205, 345);
    g.drawString(Integer.toString(secondRoundSeries.get(0).getPlayer2Wins()), 300, 350);
  }

}
