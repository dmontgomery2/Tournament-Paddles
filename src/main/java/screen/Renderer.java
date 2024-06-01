package screen;

public class Renderer {

  private static final Renderer INSTANCE = new Renderer();

  private static final int SLEEP_TIME_MILLIS = 16;

  private final Screen screen;

  private Renderer() {
    screen = Screen.getInstance();
  }

  public static Renderer getInstance() {
    return INSTANCE;
  }

  public void renderFrames() {
    while (true) {
      if (screen.isFrames()) {
        screen.update();
      }
      sleep();
    }
  }

  private void sleep() {
    try {
      Thread.sleep(SLEEP_TIME_MILLIS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
