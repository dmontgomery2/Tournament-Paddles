package seasonlogic.playoffs;

import java.io.Serializable;
import java.util.Optional;
import seasonlogic.calendar.Matchup;
import seasonlogic.models.Human;
import seasonlogic.models.MissingPlayer;
import seasonlogic.models.PlayerProfile;

public class PlayoffSeries implements Serializable {

  private boolean advanced;
  private final int seriesIndex;

  private final int gamesToWin;
  private PlayerProfile player1;
  private PlayerProfile player2;

  public void setPlayer1(PlayerProfile player1) {
    this.player1 = player1;
  }

  public void setPlayer2(PlayerProfile player2) {
    this.player2 = player2;
  }

  private int player1Wins;
  private int player2Wins;
  private PlayerProfile winner;

  public PlayoffSeries(int seriesIndex, int roundLength) {
    this.seriesIndex = seriesIndex;
    gamesToWin = roundLength / 2 + 1;
  }

  public int getSeriesIndex() {
    return seriesIndex;
  }

  public boolean isNotAdvanced() {
    return !advanced;
  }

  private void checkForSeriesWinner() {
    if (player1Wins >= gamesToWin) {
      winner = player1;
    } else if (player2Wins >= gamesToWin) {
      winner = player2;
    }
  }

  public PlayerProfile getWinner() {
    return winner;
  }

  public boolean isActive() {
    return winner == null;
  }

  public boolean isFinished() {
    return !isActive();
  }

  public boolean hasHuman() {
    return player1 instanceof Human || player2 instanceof Human;
  }


  public Matchup convertToMatchup() {
    return new Matchup(player1, player2);
  }

  public int getGamesToWin() {
    return gamesToWin;
  }

  public void giveWinTo(PlayerProfile winner) {
    if (player1.equals(winner)) {
      player1Wins++;
    } else {
      player2Wins++;
    }
    checkForSeriesWinner();
  }

  public PlayerProfile getPlayer1() {
    return Optional.ofNullable(player1).orElse(new MissingPlayer());
  }

  public PlayerProfile getPlayer2() {
    return Optional.ofNullable(player2).orElse(new MissingPlayer());
  }

  public Optional<Integer> getPlayer1Wins() {
    return Optional.ofNullable(player1).map(player1 -> player1Wins);
  }

  public Optional<Integer> getPlayer2Wins() {
    return Optional.ofNullable(player2).map(player2 -> player2Wins);
  }
}
