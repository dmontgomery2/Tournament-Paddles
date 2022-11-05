package game;

import java.util.Map;
import java.util.function.Consumer;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

class InputHandler {

    private static final Map<Character, Consumer<PlayingField>> KEY_PRESSED_TO_ACTION = ofEntries(
            entry('w', PlayingField::movePlayerUp),
            entry('s', PlayingField::movePlayerDown),
            entry('f', PlayingField::unfreeze)
    );

    private static final Map<Character, Consumer<PlayingField>> KEY_RELEASED_TO_ACTION = ofEntries(
            entry('w', PlayingField::stopPlayer),
            entry('s', PlayingField::stopPlayer)
    );

    private final PlayingField playingField;

    private static final Consumer<PlayingField> DEFAULT_ACTION = pf -> {};

    InputHandler(PlayingField playingField) {
        this.playingField = playingField;
    }


    void handleKeyPressed(char keyChar) {
        KEY_PRESSED_TO_ACTION.getOrDefault(keyChar, DEFAULT_ACTION).accept(playingField);
    }

    void handleKeyReleased(char keyChar) {
        KEY_RELEASED_TO_ACTION.getOrDefault(keyChar, DEFAULT_ACTION).accept(playingField);
    }

    void handleDrag(int x, int y) {
    }

    void handleMousePressed(int x, int y) {
    }

    void handleMouseReleased() {
    }
}
