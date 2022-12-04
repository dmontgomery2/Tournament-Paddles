package menus.pages.seasonpages;

import common.StringDrawer;
import menus.Menus;
import menus.menuitems.CompositeMenuItem;
import menus.menuitems.MenuItem;
import menus.menuitems.SingleButton;


import java.awt.*;
import java.util.List;

import static java.awt.Color.WHITE;

public abstract class MatchesPage extends SeasonStatsPage implements SeasonPage {

    private static final Color COLOR = WHITE;
    private static final int DAY_POSITION_X = 20;
    private static final int DAY_POSITION_Y = 60;

    private static final int MATCHES_POSITION_X = 20;
    private static final int MATCHES_INITIAL_POSITION_Y = 100;
    private static final int MATCHES_SPACING = 30;

    private static final StringDrawer MATCHES_DRAWER = StringDrawer.builder()
        .color(COLOR)
        .positionX(MATCHES_POSITION_X)
        .initialPositionY(MATCHES_INITIAL_POSITION_Y)
        .spacing(MATCHES_SPACING)
        .build();

    public void drawDay(String day, Graphics g){
        g.setColor(COLOR);
        g.drawString("Day " + day, DAY_POSITION_X, DAY_POSITION_Y);
    }

    public void drawMatches(List<String> matches, Graphics g){
        MATCHES_DRAWER.drawListOfStrings(matches, g);
    }

}
