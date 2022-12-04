package gameplay.ball;

import java.util.Random;

class VelocityRandomizer {
    private static final Random RANDOM = new Random();

    public int getVelocityX(){
        return RANDOM.nextBoolean() ? Ball.VELOCITY_X_MAGNITUDE : -Ball.VELOCITY_X_MAGNITUDE;
    }

    public int getVelocityY() {
        int magnitude = (int)(RANDOM.nextFloat() * Ball.MAXIMUM_VELOCITY_Y);
        boolean isNegative = RANDOM.nextBoolean();
        return isNegative ? -magnitude : magnitude;
    }

}
