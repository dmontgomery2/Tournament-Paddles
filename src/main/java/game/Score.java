package game;

import menus.Drawable;

import java.awt.*;

import static common.Const.WINDOW_HEIGHT;
import static common.Const.WINDOW_WIDTH;

public class Score implements Drawable {

    private final int pointsToWin;

    private static final String PLAYER_WON_MESSAGE = "Player won!";
    private static final String COMPUTER_WON_MESSAGE = "Computer won!";

    private static final int SCORES_POSITION_Y = WINDOW_HEIGHT / 2;
    private static final int CENTER_LINE_POSITION_X = WINDOW_WIDTH / 2;
    private static final int SCORE_LEFT_BUFFER = 10;
    private static final int SCORE_RIGHT_BUFFER = 20;
    private static final int PLAYER_SCORE_POSITION_X = CENTER_LINE_POSITION_X - SCORE_RIGHT_BUFFER;
    private static final int COMPUTER_SCORE_POSITION_X = CENTER_LINE_POSITION_X + SCORE_LEFT_BUFFER;
    private static final int CENTER_LINE_POSITION_Y1 = 0;
    private static final int CENTER_LINE_POSITION_Y2 = WINDOW_HEIGHT;
    private static final int GAME_OVER_MESSAGE_POSITION_X = 50;
    private static final int GAME_OVER_MESSAGE_POSITION_Y = 100;

    private int playerScore;
    private int computerScore;

    public Score(int pointsToWin){
        this.pointsToWin = pointsToWin;
        playerScore = 0;
        computerScore = 0;
    }

    public void onPlayerScored(){
        playerScore++;
    }

    public void onComputerScored(){
        computerScore++;
    }

    @Override
    public void drawSelf(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(playerScore)
                , PLAYER_SCORE_POSITION_X, SCORES_POSITION_Y);
        g.drawString(Integer.toString(computerScore)
                , COMPUTER_SCORE_POSITION_X, SCORES_POSITION_Y);
        g.drawLine(CENTER_LINE_POSITION_X, CENTER_LINE_POSITION_Y1
                , CENTER_LINE_POSITION_X, CENTER_LINE_POSITION_Y2);
        if(playerOrComputerHasReachedScoreToWin()){
            drawGameOverMessage(g);
        }
    }

    private void drawGameOverMessage(Graphics g){
        String message = hasPlayerWon() ? PLAYER_WON_MESSAGE : COMPUTER_WON_MESSAGE;
        g.drawString(message
                , GAME_OVER_MESSAGE_POSITION_X, GAME_OVER_MESSAGE_POSITION_Y);
    }

    private boolean hasPlayerWon(){
        return playerScore >= pointsToWin;
    }

    private boolean hasComputerWon(){
        return computerScore >= pointsToWin;
    }

    public boolean playerOrComputerHasReachedScoreToWin() {
        return hasPlayerWon() || hasComputerWon();
    }

    public void reset() {
        playerScore = 0;
        computerScore = 0;
    }
}
