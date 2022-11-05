package settings;

public class Settings {
    private final GlobalSettings globalSettings;
    private final SeasonSettings seasonSettings;
    public Settings(){
        globalSettings = new GlobalSettings();
        seasonSettings = new SeasonSettings();
    }

    public int getNumberOfPlayers(){
        return seasonSettings.getNumberOfPlayers();
    }

    public void setNumberOfPlayers(int numberOfPlayers){
        seasonSettings.setNumberOfPlayers(numberOfPlayers);
    }
    public int getDifficulty(){
        return globalSettings.getDifficulty();
    }

    public void setDifficulty(int difficulty){
        globalSettings.setDifficulty(difficulty);
    }

    public int getPointsToWin(){
        return globalSettings.getPointsToWin();
    }
    public void setPointsToWin(int pointsToWin) {
        globalSettings.setPointsToWin(pointsToWin);
    }



}
