package screen;

import screen.Screen;

public class Renderer {

    private static final Renderer INSTANCE = new Renderer();

    private Screen screen;

    private Renderer(){
        screen = Screen.getInstance();
    }

    public static Renderer getInstance(){
        return INSTANCE;
    }

    public void renderFrames(){
        while(true){
            if(screen.isFrames()) {
                screen.update();
            }
            sleep();
        }
    }

    private void sleep(){
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
