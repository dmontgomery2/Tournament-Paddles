package common.generalinterfaces;

public interface Draggable {

  default void onDrag(int x, int y) {
    //do nothing
  }

  default boolean dragActive() {
    return false;
  }
}
