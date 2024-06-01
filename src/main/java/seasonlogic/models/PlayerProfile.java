package seasonlogic.models;

import static seasonlogic.WinLoss.LOSS;
import static seasonlogic.WinLoss.WIN;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import seasonlogic.WinLoss;

public class PlayerProfile implements Comparable<PlayerProfile>, Serializable {

  private static final String DEFAULT_STREAK = "W0";

  private Integer seed;

  private final String name;
  private final int difficulty;
  private int seasonWins;
  private int seasonLosses;
  private final List<WinLoss> seasonRecord;

  public PlayerProfile(String name, int difficulty) {
    this.name = name;
    this.difficulty = difficulty;
    seasonRecord = new ArrayList<>();
  }

  public Optional<Integer> getSeed() {
    return Optional.ofNullable(seed);
  }

  public void setSeed(int seed) {
    this.seed = seed;
  }

  public String getName() {
    return name;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public String getLast10() {
    int last10Wins = 0;
    int last10Losses = 0;
    for (int i = seasonRecord.size() - 1; i >= Math.max(seasonRecord.size() - 10, 0); i--) {
      WinLoss winLoss = seasonRecord.get(i);
      switch (winLoss) {
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

  public String getStreak() {
    int recordSize = seasonRecord.size();
    if (recordSize == 0) {
      return DEFAULT_STREAK;
    }
    StringBuilder stringBuilder = new StringBuilder();
    WinLoss firstResult = seasonRecord.get(recordSize - 1);
    stringBuilder.append(firstResult);
    int streakCount = 1;
    for (int i = recordSize - 2; i >= 0; i--) {
      if (seasonRecord.get(i) != firstResult) {
        break;
      }
      streakCount++;
    }
    stringBuilder.append(streakCount);
    return stringBuilder.toString();
  }

  public void receiveSeasonWin() {
    seasonWins++;
    seasonRecord.add(WIN);
  }

  public void receiveSeasonLoss() {
    seasonLosses++;
    seasonRecord.add(LOSS);
  }

  @Override
  public String toString() {
    return "name=" + name +
        ", wins=" + seasonWins +
        ", losses=" + seasonLosses +
        ", last10=" + getLast10() +
        ", streak=" + getStreak() +
        ", difficulty=" + difficulty;
  }

  @Override
  public int compareTo(PlayerProfile o) {
    int comparingWins = Integer.compare(o.seasonWins, seasonWins);
    if (comparingWins != 0) {
      return comparingWins;
    }
    return String.CASE_INSENSITIVE_ORDER.compare(name, o.name);
  }

  public String getSeasonWins() {
    return Integer.toString(seasonWins);
  }

  public String getSeasonLosses() {
    return Integer.toString(seasonLosses);
  }
}
