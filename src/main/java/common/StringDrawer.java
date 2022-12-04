package common;

import static java.awt.Color.GRAY;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Optional;
import lombok.Builder;

@Builder
public class StringDrawer {

  private static final Color DEFAULT_COLOR = GRAY;
  private static final int DEFAULT_FONT_SIZE = 5;
  private static final int DEFAULT_POSITION_X = 5;
  private static final int DEFAULT_INITIAL_POSITION_Y = 5;
  private static final int DEFAULT_SPACING = 5;

  private final Color color;
  private final int fontSize;
  private final int positionX;
  private final int initialPositionY;
  private final int spacing;

  public void drawListOfStrings(List<String> strings, Graphics g) {
    g.setColor(color);
    int y = initialPositionY;
    for (String string : strings) {
      g.drawString(string, positionX, y);
      y += spacing;
    }
  }

}
