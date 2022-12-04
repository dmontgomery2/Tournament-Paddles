package menus.pages.seasonpages;

import seasonlogic.Season;

import java.awt.*;

public class SeasonMatchups extends MatchesPage implements SeasonPage {

    private Season season;
    public SeasonMatchups(Season season){
        this.season = season;
    }

    @Override
    public void drawSelf(Graphics g) {
        drawDay(season.getDay(), g);
        drawMatches(season.getMatchupsStrings(), g);
        drawButtons(g);
    }

    @Override
    public SeasonPage advance() {
        season.simulateMatchups();
        if(season.isHumanActive()){
            return new SeasonPlayingField(season);
        }
        return new SeasonResults(season);
    }

}
