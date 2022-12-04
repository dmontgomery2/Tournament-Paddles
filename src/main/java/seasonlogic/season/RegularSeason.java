package seasonlogic.season;

import gameplay.GameplayResult;
import seasonlogic.*;
import seasonlogic.calendar.Calendar;
import seasonlogic.calendar.Matchup;
import seasonlogic.models.Human;
import seasonlogic.models.PlayerProfile;
import seasonlogic.playoffs.Playoffs;
import seasonlogic.setup.DifficultyPickingStrategy;
import seasonlogic.setup.SimpleDifficultyPickingStrategy;
import settings.SeasonSettings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static seasonlogic.models.Names.NAMES;

public class RegularSeason implements Season {
    private Calendar calendar;

    private static final DifficultyPickingStrategy DIFFICULTY_PICKING_STRATEGY = new SimpleDifficultyPickingStrategy();
    private List<PlayerProfile> computers;
    private Human human;

    private List<PlayerProfile> allProfiles;

    private SeasonSettings settings;

    private SeasonStatus status;

    public RegularSeason() {
        settings = new SeasonSettings();
    }

    @Override
    public String getDay(){
        return Integer.toString(calendar.getDayNumber());
    }
    public void setSettings(SeasonSettings seasonSettings){
        this.settings = seasonSettings;
        setInitialState();
    }

    @Override
    public List<String> getResultsStrings(){
        return calendar.getResultsStrings();
    }

    @Override
    public SeasonStatus getStatus(){
        return status;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlayerProfile computer : computers) {
            stringBuilder.append(computer);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public Season advance() {
        recordResults();
        calendar.advanceDay();
        if (calendar.getDayNumber() > settings.getNumberOfGames()) {
            return getPlayoffs();
        }
        return this;
    }

    private Playoffs getPlayoffs(){
        Collections.sort(allProfiles);
        List<PlayerProfile> playoffParticipants = allProfiles.subList(0, getNumberOfPlayoffParticipants());
        return new Playoffs(calendar, playoffParticipants, settings);
    }

    private void recordResults(){
        calendar.getMatchups().forEach(this::processCompletedMatchup);
    }

    private void processCompletedMatchup(Matchup completedMatchup){
        completedMatchup.getWinner().receiveSeasonWin();
        completedMatchup.getLoser().receiveSeasonLoss();
    }

    public boolean isHumanActive(){
        return true;
    }

    @Override
    public PlayerProfile getChampion() {
        return null;
    }

    @Override
    public int getPaddleSize() {
        return settings.getPaddleSize();
    }

    public List<String> getMatchupsStrings(){
        return calendar.getMatchupsStrings();
    }

    private void setInitialState(){
        human = new Human(settings.getPlayerName());
        computers = new ArrayList<>();
        List<String> names = new ArrayList<>(NAMES);
        Collections.shuffle(names);
        for (int i = 0; i < settings.getNumberOfPlayers() - 1; i++) {
            computers.add(new PlayerProfile(names.get(i), getRandomComputerDifficulty()));
        }
        allProfiles = new ArrayList<>();
        allProfiles.add(human);
        allProfiles.addAll(computers);
        status = new SeasonStandings(allProfiles);
        calendar = new Calendar.Builder()
                .numberOfGames(settings.getNumberOfGames())
                .computers(computers)
                .human(human)
                .build();
    }

    private int getNumberOfPlayoffParticipants(){
        if(settings.getNumberOfPlayers() > 10){
            return 8;
        }
        else if(settings.getNumberOfPlayers() > 6){
            return 4;
        }
        return 2;
    }

    private int getRandomComputerDifficulty(){
        return DIFFICULTY_PICKING_STRATEGY.getComputerDifficulty(settings.getDifficulty());
    }

    @Override
    public void recordGameplayResult(GameplayResult gameplayResult) {
        calendar.recordGameplayResult(gameplayResult);
    }

    @Override
    public Optional<PlayerProfile> getOpponentForHuman() {
        return calendar.getOpponentForHuman();
    }

    @Override
    public int getPointsToWin() {
        return settings.getPointsToWin();
    }

    @Override
    public void simulateMatchups() {
        calendar.simulateMatchups(settings.getPointsToWin());
        //computerComputerMatchups.simulateAll(matchSimulator);
    }
}
