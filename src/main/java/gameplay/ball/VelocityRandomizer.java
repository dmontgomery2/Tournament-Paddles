package gameplay.ball;

import java.util.Random;

class VelocityRandomizer {

  private static final Random RANDOM = new Random();
  private static final int MAXIMUM_RANDOM_VELOCITY_Y = 4;

  public int getVelocityX() {
    return RANDOM.nextBoolean() ? Ball.VELOCITY_X_MAGNITUDE : -Ball.VELOCITY_X_MAGNITUDE;
  }

  public int getVelocityY() {
    int magnitude = (int) (RANDOM.nextFloat() * MAXIMUM_RANDOM_VELOCITY_Y);
    boolean isNegative = RANDOM.nextBoolean();
    return isNegative ? -magnitude : magnitude;
  }

}
