package controller.menuitems;

import common.generalinterfaces.Draggable;

public interface Slider extends MenuItem, Draggable {

  boolean isActivated();

  void reset();

  void resetDefaults();

  void submit();
}
