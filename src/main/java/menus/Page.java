package menus;

import common.genericinterfaces.Clickable;
import common.genericinterfaces.Draggable;
import common.genericinterfaces.Drawable;
import common.genericinterfaces.Keyable;

public interface Page extends Drawable, Clickable, Draggable, Keyable {
    boolean isFrames();
}
