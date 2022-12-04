package seasonlogic.playoffs.brackets.drawers;

import static java.awt.Color.WHITE;

import java.awt.Graphics;
import java.util.List;
import lombok.Builder;
import seasonlogic.playoffs.PlayoffSeries;
import seasonlogic.playoffs.brackets.PlayoffBracketDrawer;

@Builder
public
class EightPlayerPlayoffBracketDrawer implements PlayoffBracketDrawer {

  private final List<PlayoffSeries> firstRoundSeries;
  private final List<PlayoffSeries> secondRoundSeries;
  private final List<PlayoffSeries> thirdRoundSeries;

  @Override
  public void draw(Graphics g){
    g.setColor(WHITE);
    drawFirstRoundSeries(g);
    if(!secondRoundSeries.isEmpty()){
      drawSecondRoundSeries(g);
    }
    if(!thirdRoundSeries.isEmpty()){
      drawThirdRoundSeries(g);
    }

    drawLines(g);
    
  }

  private void drawLines(Graphics g){
    g.drawLine(50, 50, 150, 50);
    g.drawLine(50, 100, 150, 100);

    g.drawLine(50, 150, 150, 150);
    g.drawLine(50, 200, 150, 200);

    g.drawLine(50, 250, 150, 250);
    g.drawLine(50, 300, 150, 300);


    g.drawLine(50, 350, 150, 350);
    g.drawLine(50, 400, 150, 400);

    g.drawLine(150, 50, 150, 100);
    g.drawLine(150, 150, 150, 200);
    g.drawLine(150, 250, 150, 300);
    g.drawLine(150, 350, 150, 400);


    g.drawLine(150, 75, 250, 75);
    g.drawLine(150, 175, 250, 175);


    g.drawLine(150, 275, 250, 275);
    g.drawLine(150, 375, 250, 375);

    g.drawLine(250, 75, 250, 175);
    g.drawLine(250, 275, 250, 375);


    g.drawLine(250, 125, 350, 125);
    g.drawLine(250, 325, 350, 325);

    g.drawLine(350, 125, 350, 325);

    g.drawLine(350, 225, 450, 225);
  }
  private void drawFirstRoundSeries(Graphics g){
    PlayoffSeries firstRound1 = firstRoundSeries.get(0);
    g.drawString(firstRound1.getPlayer1().getName(), 55, 45);
    g.drawString(Integer.toString(firstRound1.getPlayer1Wins()), 140, 45);
    g.drawString(firstRound1.getPlayer2().getName(), 55, 95);
    g.drawString(Integer.toString(firstRound1.getPlayer2Wins()), 140, 95);

    PlayoffSeries firstRound2 = firstRoundSeries.get(1);
    g.drawString(firstRound2.getPlayer1().getName(), 55, 145);
    g.drawString(Integer.toString(firstRound2.getPlayer1Wins()), 140, 145);
    g.drawString(firstRound2.getPlayer2().getName(), 55, 195);
    g.drawString(Integer.toString(firstRound2.getPlayer2Wins()), 140, 195);

    PlayoffSeries firstRound3 = firstRoundSeries.get(2);
    g.drawString(firstRound3.getPlayer1().getName(), 55, 245);
    g.drawString(Integer.toString(firstRound3.getPlayer1Wins()), 140, 245);
    g.drawString(firstRound3.getPlayer2().getName(), 55, 295);
    g.drawString(Integer.toString(firstRound3.getPlayer2Wins()), 140, 295);

    PlayoffSeries firstRound4 = firstRoundSeries.get(3);
    g.drawString(firstRound4.getPlayer1().getName(), 55, 345);
    g.drawString(Integer.toString(firstRound4.getPlayer1Wins()), 140, 345);
    g.drawString(firstRound4.getPlayer2().getName(), 55, 395);
    g.drawString(Integer.toString(firstRound4.getPlayer2Wins()), 140, 395);

  }
  private void drawSecondRoundSeries(Graphics g){
    PlayoffSeries secondRound1 = secondRoundSeries.get(0);
    g.drawString(secondRound1.getPlayer1().getName(), 155, 65);
    g.drawString(Integer.toString(secondRound1.getPlayer1Wins()), 250, 75);
    g.drawString(secondRound1.getPlayer2().getName(), 155, 165);
    g.drawString(Integer.toString(secondRound1.getPlayer2Wins()), 250, 175);

    PlayoffSeries secondRound2 = secondRoundSeries.get(1);
    g.drawString(secondRound2.getPlayer1().getName(), 155, 270);
    g.drawString(Integer.toString(secondRound2.getPlayer1Wins()), 250, 270);
    g.drawString(secondRound2.getPlayer2().getName(), 155, 370);
    g.drawString(Integer.toString(secondRound2.getPlayer2Wins()), 250, 370);

  }
  private void drawThirdRoundSeries(Graphics g){
    PlayoffSeries thirdRound1 = thirdRoundSeries.get(0);
    g.drawString(thirdRound1.getPlayer1().getName(), 250, 120);
    g.drawString(Integer.toString(thirdRound1.getPlayer1Wins()), 350, 125);
    g.drawString(thirdRound1.getPlayer2().getName(), 250, 320);
    g.drawString(Integer.toString(thirdRound1.getPlayer2Wins()), 350, 325);
  }
}
