package screen;

import static common.Const.WINDOW_HEIGHT;
import static common.Const.WINDOW_WIDTH;
import static java.awt.Color.BLACK;

import controller.Controller;
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class JPanel extends javax.swing.JPanel {

  private static final Color BACKGROUND_COLOR = BLACK;
  private static final Lock RENDER_LOCK = new ReentrantLock();

  public JPanel() {
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
  }

  @Override
  public void paintComponent(Graphics g) {
    try {
      RENDER_LOCK.lock();
      doPaintComponent(g);
    } finally {
      RENDER_LOCK.unlock();
    }
  }

  private void doPaintComponent(Graphics g) {
    drawBackground(g);
    Controller.getInstance().drawSelf(g);
  }

  private void drawBackground(Graphics g) {
    g.setColor(BACKGROUND_COLOR);
    g.fillRect(0, 0, getWidth(), getHeight());
  }

  public boolean isFrames() {
    return Controller.getInstance().isFrames();
  }
}