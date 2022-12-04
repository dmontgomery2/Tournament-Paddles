package seasonlogic.playoffs;

import seasonlogic.calendar.Matchup;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;

public class PlayoffSeries {

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

    public PlayoffSeries(PlayerProfile player1, PlayerProfile player2, int roundLength){
        this.player1 = player1;
        this.player2 = player2;
        gamesToWin = roundLength / 2 + 1;
    }
    private void checkForSeriesWinner(){
        if(player1Wins >= gamesToWin){
            winner = player1;
        }
        else if(player2Wins >= gamesToWin){
            winner = player2;
        }
    }

    public PlayerProfile getWinner(){
        return winner;
    }

    public boolean isActive(){
        return winner == null;
    }

    @Override
    public String toString(){
        return player1.getName() + " " + "(" + player1.getSeed()
                + ") " + player1Wins + ", " + player2.getName()
                + " " + "(" + player2.getSeed() + ") " + player2Wins;
    }

    public boolean hasHuman(){
        return player1 instanceof Human || player2 instanceof Human;
    }


    public Matchup convertToMatchup() {
        return new Matchup(player1, player2);
    }

    public int getGamesToWin(){
        return gamesToWin;
    }

    public void giveWinTo(PlayerProfile winner) {
        if(player1.equals(winner)){
            player1Wins++;
        }
        else{
            player2Wins++;
        }
        checkForSeriesWinner();
    }

    public PlayerProfile getPlayer1() {
        return player1;
    }

    public PlayerProfile getPlayer2() {
        return player2;
    }

    public int getPlayer1Wins() {
        return player1Wins;
    }

    public int getPlayer2Wins() {
        return player2Wins;
    }
}
