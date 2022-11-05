package game;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

class InputHandler {

    private final Map<Character, Runnable> KEY_PRESSED_TO_ACTION = ofEntries(
            entry('w', this::movePlayerUp),
            entry('s', this::movePlayerDown),
            entry('f', this::onFPressed)
    );

    private final Map<Character, Runnable> KEY_RELEASED_TO_ACTION = ofEntries(
            entry('w', this::stopPlayer),
            entry('s', this::stopPlayer)
    );


}
