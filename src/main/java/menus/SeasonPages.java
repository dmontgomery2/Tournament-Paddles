package menus;

import seasonlogic.Season;

import java.awt.*;

public class SeasonPages implements Page {

    private Page currentPage;
    private Page seasonOptionsPage;
    private Page seasonStatus;
    private Page seasonResults;
    private Page playingField;
    private Season season;

    public SeasonPages(Season season){
        this.season = season;
        seasonStatus = new SeasonStandings(season);
        seasonResults = new SeasonResults(season);
        currentPage = seasonStatus;
    }

    public void advance(){
        if(currentPage == seasonStatus){
            currentPage = seasonResults;
            season.advance();
        }
        else{
            currentPage = seasonStatus;
        }
    }

    @Override
    public void drawSelf(Graphics g) {
        currentPage.drawSelf(g);
    }

    @Override
    public void onMousePressed(int x, int y) {
        currentPage.onMousePressed(x, y);
    }







    @Override
    public void onMouseReleased() {

    }

    @Override
    public void onDrag(int x, int y) {

    }

    @Override
    public void onKeyPressed(char keyChar) {

    }

    @Override
    public void onKeyReleased(char keyChar) {

    }
}
