package common.generalinterfaces;

public interface Clickable {

  default void onMouseMoved(int x, int y) {
    //do nothing
  }

  default void onMousePressed(int x, int y) {
    //do nothing
  }

  default void onMouseReleased(int x, int y) {
    //do nothing
  }

  default void onMouseEntered(int x, int y) {
    //do nothing
  }
}
