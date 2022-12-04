package seasonlogic.playoffs;

import gameplay.GameplayResult;
import seasonlogic.Season;
import seasonlogic.SeasonStatus;
import seasonlogic.calendar.Calendar;
import seasonlogic.calendar.Matchup;
import seasonlogic.calendar.Matchups;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.brackets.PlayoffBracketFactory;
import settings.SeasonSettings;

import java.util.List;
import java.util.Optional;

public class Playoffs implements Season {

    private Calendar calendar;
    private final SeasonSettings settings;
    private PlayoffBracket playoffBracket;
    private PlayerProfile champion;
    public Playoffs(Calendar calendar, List<PlayerProfile> players, SeasonSettings settings){
        this.calendar = calendar;
        for(int i = 0; i < players.size(); i++){
            players.get(i).setSeed(i + 1);
        }
        this.settings = settings;
        playoffBracket = PlayoffBracketFactory.getPlayoffBracket(calendar, players, settings);
        calendar.scheduleInitialSeries(playoffBracket.getCurrentSeries());
    }

    @Override
    public Season advance(){
        recordResults();
        calendar.advanceDay();
        playoffBracket.advance();
        if(playoffBracket.getChampion() != null){
            champion = playoffBracket.getChampion();
            return this;
        }
        if(playoffBracket.justAdvancedRound()){
            playoffBracket.setJustAdvancedRound(false);
            calendar.scheduleInitialSeries(playoffBracket.getCurrentSeries());
        }
        return this;
    }

    private void recordResults(){
        Matchups completedMatchups = calendar.getMatchups();
        for (Matchup matchup : completedMatchups) {
            playoffBracket.recordMatchup(matchup);
        }
    }

    @Override
    public void simulateMatchups() {
        calendar.simulateMatchups(settings.getPointsToWin());
    }

    @Override
    public void recordGameplayResult(GameplayResult gameplayResult) {
        calendar.recordGameplayResult(gameplayResult);
    }

    @Override
    public int getPointsToWin() {
        return settings.getPointsToWin();
    }

    @Override
    public Optional<PlayerProfile> getOpponentForHuman() {
        return calendar.getOpponentForHuman();
    }

    @Override
    public String getDay() {
        return Integer.toString(calendar.getDayNumber());
    }

    @Override
    public List<String> getResultsStrings() {
        return calendar.getResultsStrings();
    }

    @Override
    public SeasonStatus getStatus() {
        return playoffBracket;
    }
    @Override
    public List<String> getMatchupsStrings() {
        return calendar.getMatchupsStrings();
    }

    @Override
    public int getPaddleSize() {
        return settings.getPaddleSize();
    }

    @Override
    public boolean isHumanActive() {
        return playoffBracket.isHumanActive();
    }

    @Override
    public PlayerProfile getChampion() {
        return champion;
    }
}
