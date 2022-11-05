package game;

import common.Utils;
import game.ai.*;


import menus.Page;
import menus.Menus;

import java.awt.*;
import java.util.Map;

import static common.Const.*;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class PlayingField implements Page {
    private static final int VERTICAL_BOUNDARY_BUFFER = 30;
    private static final int HORIZONTAL_BOUNDARY_BUFFER = 30;
    private static final int LEFT_BOUNDARY = VERTICAL_BOUNDARY_BUFFER;
    private static final int RIGHT_BOUNDARY = WINDOW_WIDTH - HORIZONTAL_BOUNDARY_BUFFER;
    public static final int TOP_BOUNDARY = VERTICAL_BOUNDARY_BUFFER;
    public static final int BOTTOM_BOUNDARY = WINDOW_HEIGHT - VERTICAL_BOUNDARY_BUFFER;

    private boolean frozen;
    private boolean gameOver;

    private final Paddle playerPaddle;
    private final Paddle computerPaddle;
    private final Ball ball;
    private final AI ai;
    private final Score score;

    private final CollisionHandler collisionHandler;

    public PlayingField(int pointsToWin, int difficulty){
        frozen = false;
        playerPaddle = new Paddle(60, Color.BLUE);
        computerPaddle = new Paddle(500, Color.GREEN);
        ball = new Ball();
        ai = AIFactory.getAI(difficulty, computerPaddle, ball);
        score = new Score(pointsToWin);
        collisionHandler = new CollisionHandler(ball, playerPaddle, computerPaddle);
    }

    private void assessPositions(){
        collisionHandler.handleCollisions();
        handleScoredEvents();
    }

    private void handleScoredEvents(){
        if(computerJustScored()){
            onComputerScored();
        }
        if(playerJustScored()){
            onPlayerScored();
        }
    }

    private void onPlayerScored() {
        score.onPlayerScored();
        onPlayerOrComputerScored();
    }

    private void onComputerScored() {
        score.onComputerScored();
        onPlayerOrComputerScored();
    }

    private void onPlayerOrComputerScored(){
        checkGameOver();
        if(!gameOver){
            freeze();
        }
    }

    private boolean computerJustScored(){
        return ball.getPositionX() <= LEFT_BOUNDARY
                && ball.isMovingLeft()
                && !frozen
                && !gameOver;
    }

    private boolean playerJustScored() {
        return ball.getPositionX() >= RIGHT_BOUNDARY
                && ball.isMovingRight()
                && !frozen
                && !gameOver;
    }

    @Override
    public void drawSelf(Graphics g){
        assessPositions();
        ai.assess();
        ball.drawSelf(g);
        playerPaddle.drawSelf(g);
        computerPaddle.drawSelf(g);
        score.drawSelf(g);
        if(!frozen && !gameOver){
            ball.updatePosition();
            playerPaddle.updatePosition();
            computerPaddle.updatePosition();
        }
    }

    private void checkGameOver() {
        if(score.playerOrComputerHasReachedScoreToWin()){
            gameOver = true;
        }
    }

    private void movePlayerUp() {
        playerPaddle.moveUp(PLAYER_PADDLE_VELOCITY_MAGNITUDE);
    }

    private void movePlayerDown(){
        playerPaddle.moveDown(PLAYER_PADDLE_VELOCITY_MAGNITUDE);
    }

    public void stopPlayer(){
        playerPaddle.stop();
    }

    public void unfreeze(){
        if(!frozen){
            return;
        }
        frozen = false;
        reset();
    }

    public void freeze(){
        frozen = true;
    }

    public void reset(){
        playerPaddle.reset();
        computerPaddle.reset();
        ball.reset();
    }

    private void onFPressed() {
        unfreeze();
        if (gameOver) {
            Menus.getInstance().goToMainMenu();
        }
    }
    @Override
    public void onKeyPressed(char keyChar) {
        KEY_PRESSED_TO_ACTION.getOrDefault(keyChar, Utils::doNothing);
    }

    @Override
    public void onKeyReleased(char keyChar) {
        KEY_RELEASED_TO_ACTION.getOrDefault(keyChar, Utils::doNothing);
    }

    @Override
    public void onDrag(int x, int y) {

    }

    @Override
    public void onMousePressed(int x, int y) {

    }

    @Override
    public void onMouseReleased() {

    }
}
