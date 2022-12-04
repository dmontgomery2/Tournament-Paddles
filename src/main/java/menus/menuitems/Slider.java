package menus.menuitems;

import common.genericinterfaces.Draggable;

public interface Slider extends MenuItem, Draggable {
    boolean isActivated();

    void reset();

    void resetDefaults();

    void submit();
}
