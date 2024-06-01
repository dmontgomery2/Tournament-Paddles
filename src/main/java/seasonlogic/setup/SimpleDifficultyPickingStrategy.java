package seasonlogic.setup;

import java.util.Random;

public class SimpleDifficultyPickingStrategy implements DifficultyPickingStrategy {

  private static final Random RANDOM = new Random();

  @Override
  public int getComputerDifficulty(int seasonDifficulty) {
    return RANDOM.nextInt(seasonDifficulty) + 1;
  }
}
