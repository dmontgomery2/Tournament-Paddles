package menus;

import game.PlayingField;

public interface Keyable {
    void onKeyPressed(char keyChar);

    void onKeyReleased(char keyChar);
}
