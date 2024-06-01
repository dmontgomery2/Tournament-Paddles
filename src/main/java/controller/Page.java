package controller;

import common.generalinterfaces.Clickable;
import common.generalinterfaces.Draggable;
import common.generalinterfaces.Drawable;
import common.generalinterfaces.Keyable;

public interface Page extends Drawable, Clickable, Draggable, Keyable {

  boolean isFrames();

  default void onEnter(int mouseX, int mouseY) {

  }
}
