package screen;

import java.awt.*;

import static common.Const.WINDOW_HEIGHT;
import static common.Const.WINDOW_WIDTH;

public class Screen {

    private static final Screen INSTANCE = new Screen();
    private static final String SCREEN_TITLE = "Tournament Paddles";

    private Canvas canvas;

    private Screen() {
        canvas = new Canvas();
        canvas.addMouseListener(new MouseListener());
        canvas.addMouseMotionListener(new MouseMotionListener());
        canvas.addKeyListener(new KeyListener());
        Frame f = new Frame(SCREEN_TITLE);
        f.add(canvas);

        f.setLayout(null);
        f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        f.setVisible(true);
    }

    public static Screen getInstance(){
        return INSTANCE;
    }

    public void update(){
        canvas.repaint();
    }

    public boolean isFrames(){
        return canvas.isFrames();
    }

}