package seasonlogic.playoffs.playoffbracket;

import static java.awt.Color.GREEN;
import static java.awt.Color.WHITE;

import java.awt.Graphics;
import java.util.Optional;
import lombok.Builder;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.PlayoffSeries;

@Builder
public class SingleSeriesDrawer {

  private static final int NAME_LEFT_X_BUFFER = 2;
  private static final int TEXT_BOTTOM_Y_BUFFER = 5;
  private static final int WINS_RIGHT_X_BUFFER = 10;

  private final Graphics graphics;
  private final DrawParameters dp;
  private final PlayoffSeries playoffSeries;


  public void drawPlayoffSeries() {
    graphics.drawLine(dp.getX(), dp.getY(), dp.getX() + dp.getRectangleXSide(), dp.getY());
    drawPlayerName(playoffSeries.getPlayer1(), dp);
    drawPlayer1Wins(dp);
    graphics.drawLine(dp.getX() + dp.getRectangleXSide(), dp.getY(),
        dp.getX() + dp.getRectangleXSide(), dp.getY() + dp.getRectangleYSide());
    dp.setY(dp.getY() + dp.getRectangleYSide());
    graphics.drawLine(dp.getX(), dp.getY(), dp.getX() + dp.getRectangleXSide(), dp.getY());
    drawPlayerName(playoffSeries.getPlayer2(), dp);
    drawPlayer2Wins(dp);
    dp.setY(dp.getY() + dp.getRectangleYSide());
  }

  private void drawPlayerName(PlayerProfile playerProfile, DrawParameters drawParameters) {
    if (playerProfile instanceof Human) {
      graphics.setColor(GREEN);
    }
    graphics.drawString(getPlayerNameString(playerProfile),
        drawParameters.getX() + NAME_LEFT_X_BUFFER, drawParameters.getY() - TEXT_BOTTOM_Y_BUFFER);
    graphics.setColor(WHITE);
  }

  private void drawPlayer1Wins(DrawParameters drawParameters) {
    if (playoffSeries.getPlayer1() instanceof Human) {
      graphics.setColor(GREEN);
    }
    drawWins(getPlayer1Wins(), drawParameters);
    graphics.setColor(WHITE);
  }

  private void drawPlayer2Wins(DrawParameters drawParameters) {
    if (playoffSeries.getPlayer2() instanceof Human) {
      graphics.setColor(GREEN);
    }
    drawWins(getPlayer2Wins(), drawParameters);
    graphics.setColor(WHITE);
  }

  private void drawWins(String wins, DrawParameters drawParameters) {
    graphics.drawString(wins,
        drawParameters.getX() + drawParameters.getRectangleXSide() - WINS_RIGHT_X_BUFFER,
        drawParameters.getY() - TEXT_BOTTOM_Y_BUFFER);
  }

  private String getPlayer1Wins() {
    return optionalIntegerToString(playoffSeries.getPlayer1Wins());
  }

  private String getPlayer2Wins() {
    return optionalIntegerToString(playoffSeries.getPlayer2Wins());
  }

  private String optionalIntegerToString(Optional<Integer> optionalInteger) {
    return optionalInteger.map(Object::toString).orElse("");
  }

  private String getPlayerNameString(PlayerProfile playerProfile) {
    return getPlayerSeedString(playerProfile) + playerProfile.getName();
  }

  private String getPlayerSeedString(PlayerProfile playerProfile) {
    return playerProfile.getSeed()
        .map(s -> "(" + s + ") ")
        .orElse("");
  }

}
