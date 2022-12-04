package menus.pages.seasonpages;

import seasonlogic.Season;

import java.awt.*;

public class SeasonResults extends MatchesPage implements SeasonPage {

    private Season season;

    public SeasonResults(Season season){
        this.season = season;
    }

    @Override
    public void drawSelf(Graphics g) {
        drawDay(season.getDay(), g);
        drawMatches(season.getResultsStrings(), g);
        drawButtons(g);
    }

    @Override
    public SeasonPage advance() {
        return new SeasonStatusPage(season.advance());
    }

    @Override
    public boolean isFrames() {
        return false;
    }
}
