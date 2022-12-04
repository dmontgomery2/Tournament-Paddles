package gameplay;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import java.util.Map;
import java.util.function.Consumer;

class InputHandler {

  private static final int W_CODE = 87;
  private static final int S_CODE = 83;
  private static final int F_CODE = 70;
  private static final int ESC_CODE = 27;

  private static final Map<Integer, Consumer<PlayingField>> KEY_PRESSED_CODE_TO_ACTION = ofEntries(
      entry(W_CODE, PlayingField::movePlayerUp),
      entry(S_CODE, PlayingField::movePlayerDown),
      entry(F_CODE, PlayingField::unfreeze),
      entry(ESC_CODE, PlayingField::exit)
  );

  private static final Map<Integer, Consumer<PlayingField>> KEY_RELEASED_CODE_TO_ACTION = ofEntries(
      entry(W_CODE, PlayingField::stopPlayer),
      entry(S_CODE, PlayingField::stopPlayer)
  );

  private static final Consumer<PlayingField> DEFAULT_ACTION = pf -> {
  };

  private final PlayingField playingField;

  InputHandler(PlayingField playingField) {
    this.playingField = playingField;
  }


  void handleKeyPressed(int keyCode) {
    KEY_PRESSED_CODE_TO_ACTION
        .getOrDefault(keyCode, DEFAULT_ACTION)
        .accept(playingField);
  }

  void handleKeyReleased(int keyCode) {
    KEY_RELEASED_CODE_TO_ACTION
        .getOrDefault(keyCode, DEFAULT_ACTION)
        .accept(playingField);
  }

  void handleDrag(int x, int y) {
    //do nothing
  }

  void handleMousePressed(int x, int y) {
    //do nothing
  }

  void handleMouseReleased() {
    //do nothing
  }
}
