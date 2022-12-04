package menus.pages.seasonpages;

import menus.Menus;
import menus.menuitems.MenuItem;
import menus.menuitems.CompositeMenuItem;
import menus.menuitems.SingleButton;
import seasonlogic.Season;

import java.awt.*;

public class SeasonStatusPage extends SeasonStatsPage implements SeasonPage {
    private Season season;

    public SeasonStatusPage(Season season){
        this.season = season;
    }

    @Override
    public void drawSelf(Graphics g) {
        drawButtons(g);
        season.getStatus().drawSelf(g);
    }

    @Override
    public SeasonPage advance(){
        if(season.getChampion() != null){
            return new ChampionPage(season);
        }
        return new SeasonMatchups(season);
    }
}
