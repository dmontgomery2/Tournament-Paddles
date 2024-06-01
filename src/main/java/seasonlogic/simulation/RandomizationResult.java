package seasonlogic.simulation;

import java.io.Serializable;

class RandomizationResult implements Serializable {

  private final float percentageToLoss;
  private final boolean player1Won;

  public RandomizationResult(float percentageToLoss, boolean player1Won) {
    this.percentageToLoss = percentageToLoss;
    this.player1Won = player1Won;
  }

  public float getPercentageToLoss() {
    return percentageToLoss;
  }

  public boolean player1Won() {
    return player1Won;
  }
}
