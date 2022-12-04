package settings;

public class SeasonSettings {

    private String playerName;
    private int numberOfPlayers;
    private int numberOfGames;
    private int pointsToWin;

    private int difficulty;
    private int round1;
    private int round2;
    private int round3;
    private int paddleSize;

    public String getPlayerName(){
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getPointsToWin() {
        return pointsToWin;
    }

    public void setPointsToWin(int pointsToWin) {
        this.pointsToWin = pointsToWin;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getRound1(){
        return round1;
    }

    public void setRound1(int round1) {
        this.round1 = round1;
    }

    public int getRound2(){
        return round2;
    }
    public void setRound2(int round2) {
        this.round2 = round2;
    }
    public int getRound3(){
        return round3;
    }
    public void setRound3(int round3){
        this.round3 = round3;
    }

    public int getPaddleSize(){
        return paddleSize;
    }

    public void setPaddleSize(int paddleSize) {
        this.paddleSize = paddleSize;
    }

}
