package gameplay.ai.abstractaitypes;

import gameplay.Paddle;
import gameplay.PlayingField;
import gameplay.ai.AI;
import gameplay.ball.Ball;
import java.util.Random;

public abstract class RandomAI implements AI {

  private static final int EXTRA_BUFFER = 10;
  private static final Random RANDOM = new Random();
  private final int velocityMagnitude;
  private final Paddle paddle;
  private final Ball ball;

  private int count;

  private int movementPeriod;

  public RandomAI(Paddle paddle, Ball ball, int velocityMagnitude) {
    this.paddle = paddle;
    this.ball = ball;
    this.velocityMagnitude = velocityMagnitude;
    count = 0;
    movementPeriod = RANDOM.nextInt(3) + 1;
    paddle.moveDown(getRandomVelocity());
  }

  @Override
  public void assess() {
    count++;
    if (count >= movementPeriod || outOfBounds()) {
      count = 0;
      movementPeriod = getRandomMovementPeriod();
      paddle.moveDown(getRandomVelocity());
    }
  }

  private boolean outOfBounds() {
    return paddle.getPositionY() < PlayingField.TOP_BOUNDARY + EXTRA_BUFFER
        || paddle.getPositionY() + paddle.getHeight() > PlayingField.BOTTOM_BOUNDARY - EXTRA_BUFFER;
  }

  private int getRandomMovementPeriod() {
    return RANDOM.nextInt(25) + 1;
  }

  private int getRandomVelocity() {
    return RANDOM.nextBoolean() ? velocityMagnitude : -velocityMagnitude;
  }
}
