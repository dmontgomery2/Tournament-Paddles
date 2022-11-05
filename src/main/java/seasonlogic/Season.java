package seasonlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;
import static seasonlogic.Names.NAMES;
import static common.Utils.getModifiableCopy;

public class Season {
    private static final Random RANDOM = new Random();
    private List<String> dailyResults;
    private List<PlayerProfile> players;

    private Human human;

    private int numberOfPlayers;

    public Season() {
        setInitialState();
    }

    public List<String> getDailyResults(){
        return dailyResults;
    }

    public List<String> getStandings(){
        Collections.sort(players);
        return players.stream()
                .map(Object::toString)
                .collect(toList());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlayerProfile player : players) {
            stringBuilder.append(player);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void advance() {
        dailyResults = new ArrayList<>();
        Collections.shuffle(players);
        for (int j = 0; j < players.size() / 2; j++) {
            PlayerProfile player1 = players.get(j);
            PlayerProfile player2 = players.get(players.size() - 1 - j);
            dailyResults.add(player1.playAgainst(player2));
        }
    }

    private void setInitialState(){
        dailyResults = new ArrayList<>();
        players = new ArrayList<>();
        List<String> names = getModifiableCopy(NAMES);
        Collections.shuffle(names);
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new PlayerProfile(names.get(i), RANDOM.nextInt(9) + 1));
        }
    }

    public void reset(int difficulty) {
        numberOfPlayers = difficulty;
        reset();
    }

    public void reset() {
        setInitialState();
    }
}
