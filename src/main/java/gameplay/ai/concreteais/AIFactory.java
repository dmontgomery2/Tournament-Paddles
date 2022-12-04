package gameplay.ai.concreteais;

import gameplay.ball.Ball;
import gameplay.Paddle;
import gameplay.ai.AI;

import java.util.Map;
import java.util.function.BiFunction;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class AIFactory {

    private static final Map<Integer, BiFunction<Paddle, Ball, AI>> DIFFICULTY_TO_CONSTRUCTOR = ofEntries(
            entry(0, AILevel1::new),
            entry(1, AILevel1::new),
            entry(2, AILevel2::new),
            entry(3, AILevel3::new),
            entry(4, AILevel4::new),
            entry(5, AILevel5::new),
            entry(6, AILevel6::new),
            entry(7, AILevel7::new),
            entry(8, AILevel8::new),
            entry(9, AILevel9::new),
            entry(10, AILevel10::new)
    );

    public static AI getAI(int difficulty, Paddle paddle, Ball ball) {
        return DIFFICULTY_TO_CONSTRUCTOR
                .get(difficulty)
                .apply(paddle, ball);
    }
}
