package common.genericinterfaces;

public interface Clickable {

  default void onMousePressed(int x, int y) {
    //do nothing
  }

  default void onMouseReleased() {
    //do nothing
  }
}
