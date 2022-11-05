package common;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static <T> List<T> getModifiableCopy(List<T> list){
        List<T> copy = new ArrayList<>();
        for(T t : list){
            copy.add(t);
        }
        return copy;
    }

    public static void drawListOfStrings(List<String> strings, Graphics g){
        g.setColor(Color.WHITE);
        int y = 60;
        for(String string : strings){
            g.drawString(string, 20, y);
            y += 10;
        }
    }

}
