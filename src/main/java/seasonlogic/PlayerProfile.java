package seasonlogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerProfile implements Comparable<PlayerProfile>{
    public static final String DEFAULT_STREAK = "W0";

    private String name;
    private int difficulty;
    private int wins;
    private int losses;
    private List<WinLoss> record;

    public PlayerProfile(String name, int difficulty){
        this.name = name;
        this.difficulty = difficulty;
        record = new ArrayList<>();
    }

    public String getLast10(){
        int last10Wins = 0;
        int last10Losses = 0;
        for(int i = record.size() - 1; i >= Math.max(record.size() - 10, 0); i--){
            WinLoss winLoss = record.get(i);
            switch(winLoss){
                case WIN:
                    last10Wins++;
                    break;
                case LOSS:
                    last10Losses++;
                    break;
           }
        }
        return Integer.toString(last10Wins) + '-' + last10Losses;
    }

    public String getStreak(){
        int recordSize = record.size();
        if(recordSize == 0){
            return DEFAULT_STREAK;
        }
        StringBuilder stringBuilder = new StringBuilder();
        WinLoss firstResult = record.get(recordSize - 1);
        stringBuilder.append(firstResult);
        int streakCount = 1;
        for(int i = recordSize - 2; i >= 0; i--){
            if(record.get(i) != firstResult){
                break;
            }
            streakCount++;
        }
        stringBuilder.append(streakCount);
        return stringBuilder.toString();
    }

    public void receiveWin(){
        wins++;
        record.add(WinLoss.WIN);
    }

    public void receiveLoss(){
        losses++;
        record.add(WinLoss.LOSS);
    }

    public String playAgainst(PlayerProfile other){
        if(difficulty > other.difficulty){
            receiveWin();
            other.receiveLoss();
            return name + " beat " + other.name;
        }
        else{
            receiveLoss();
            other.receiveWin();
            return other.name + " beat " + name;
        }
    }

    @Override
    public String toString() {
        return "name=" + name +
                ", wins=" + wins +
                ", losses=" + losses +
                ", last10=" + getLast10() +
                ", streak=" + getStreak();
    }

    @Override
    public int compareTo(PlayerProfile o) {
        return Integer.compare(o.wins, wins);
    }
}
