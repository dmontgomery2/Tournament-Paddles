package seasonlogic.playoffs.playoffbracket;

import static java.awt.Color.GREEN;
import static java.awt.Color.WHITE;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.PlayoffSeries;


@Builder
class PlayoffBracketDrawer implements Serializable {

  private static final int TEXT_BOTTOM_Y_BUFFER = 5;
  private static final int NAME_LEFT_X_BUFFER = 2;
  private static final int WINS_RIGHT_X_BUFFER = 10;
  private static final int INITIAL_X = 50;

  private static final int HEIGHT = 400;


  private final List<List<PlayoffSeries>> playoffSeries;
  private final int initialY;
  private final int initialRectangleXSide;
  private final int initialRectangleYSide;
  private final float scalingRateX;
  private static final float SCALING_RATE_Y = 2;

  public void draw(Graphics g) {
    g.setColor(WHITE);
    DrawParameters drawParameters = getInitialDrawParameters();
    playoffSeries.forEach(playoffSeriesList -> {
          int roundInitialY = drawParameters.getY();
          playoffSeriesList.forEach(playoffSeries ->
              drawSeries(g, drawParameters, playoffSeries)
          );
          drawParameters.setX(drawParameters.getX() + drawParameters.getRectangleXSide());
          drawParameters.setY(roundInitialY + drawParameters.getRectangleYSide() / 2);
          drawParameters.setRectangleXSide(
              (int) (drawParameters.getRectangleXSide() * scalingRateX));
          drawParameters.setRectangleYSide(
              (int) (drawParameters.getRectangleYSide() * SCALING_RATE_Y));
        }
    );
    g.drawLine(drawParameters.getX(), drawParameters.getY(),
        drawParameters.getX() + drawParameters.getRectangleXSide(), drawParameters.getY());
    drawChampion(g, drawParameters);
  }

  private void drawSeries(Graphics g, DrawParameters drawParameters, PlayoffSeries playoffSeries) {
    SingleSeriesDrawer.builder()
        .graphics(g)
        .dp(drawParameters)
        .playoffSeries(playoffSeries)
        .build()
        .drawPlayoffSeries();
  }

  private DrawParameters getInitialDrawParameters() {
    return DrawParameters.builder()
        .x(INITIAL_X)
        .y(initialY)
        .rectangleXSide(initialRectangleXSide)
        .rectangleYSide(initialRectangleYSide)
        .build();
  }

  private void drawChampion(Graphics g, DrawParameters drawParameters) {
    getChampion().ifPresent(champion -> drawPlayerName(g, champion, drawParameters));
  }

  private void drawPlayerName(Graphics g, PlayerProfile playerProfile,
      DrawParameters drawParameters) {
    if (playerProfile instanceof Human) {
      g.setColor(GREEN);
    }
    g.drawString(getPlayerNameString(playerProfile), drawParameters.getX() + NAME_LEFT_X_BUFFER,
        drawParameters.getY() - TEXT_BOTTOM_Y_BUFFER);
    g.setColor(WHITE);
  }

  private String getPlayerNameString(PlayerProfile playerProfile) {
    return getPlayerSeedString(playerProfile) + playerProfile.getName();
  }

  private String getPlayerSeedString(PlayerProfile playerProfile) {
    return playerProfile.getSeed()
        .map(s -> "(" + s + ") ")
        .orElse("");
  }

  private Optional<PlayerProfile> getChampion() {
    return Optional.ofNullable(getLastSeries().getWinner());
  }

  private PlayoffSeries getLastSeries() {
    return playoffSeries.get(playoffSeries.size() - 1).get(0);
  }
}
