package screen;


import static common.Const.WINDOW_HEIGHT;
import static common.Const.WINDOW_WIDTH;

import java.awt.Frame;

public class Screen {

  private static final Screen INSTANCE = new Screen();
  private static final String SCREEN_TITLE = "Tournament Paddles";
  private final JPanel panel;

  private Screen() {
    panel = new JPanel();
    panel.addMouseListener(new MouseListener());
    panel.addMouseMotionListener(new MouseMotionListener());
    panel.addKeyListener(new KeyListener());
    panel.setFocusable(true);

    Frame f = new Frame(SCREEN_TITLE);
    f.add(panel);

    f.setLayout(null);
    f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    f.setVisible(true);
  }

  public static Screen getInstance() {
    return INSTANCE;
  }

  public void update() {
    panel.repaint();
  }

  public boolean isFrames() {
    return panel.isFrames();
  }

}