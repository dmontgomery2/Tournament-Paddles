package common.genericinterfaces;

import java.awt.event.KeyEvent;

public interface Keyable {

  default void onKeyPressed(KeyEvent e) {
    //do nothing
  }

  default void onKeyReleased(KeyEvent e) {
    //do nothing
  }
}
