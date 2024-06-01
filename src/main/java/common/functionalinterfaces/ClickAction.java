package common.functionalinterfaces;

import controller.Controller;

@FunctionalInterface
public interface ClickAction {

  void onClick(Controller controller, int x, int y);

}
