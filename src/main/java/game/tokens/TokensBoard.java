package game.tokens;

import controller.SoundEngine;
import controller.game.PlayerActions;
import game.board.GameBoard;
import game.tokens.conflict.ConflictTokenSet;
import game.tokens.progress.ProgressToken;
import game.tokens.progress.ProgressTokenStack;

public class TokensBoard {
    
    ConflictTokenSet conflictTokenSet; // TODO : figure out how to make these variables private (and can still be accessed by tests classes)
    ProgressTokenStack progressTokenStack;
    GameBoard gameBoard;
    
    public TokensBoard(int numberOfPlayers, GameBoard gameBoard) {
        conflictTokenSet = new ConflictTokenSet(determineNumberOfWarTokens(numberOfPlayers) );
        progressTokenStack = new ProgressTokenStack();
        this.gameBoard = gameBoard;
    }
    
    private int determineNumberOfWarTokens(int numberOfPlayers) { // TODO : figure out why this method may be static
        if (numberOfPlayers <= 3) {
            return 3;
        }
        if (numberOfPlayers == 4 || numberOfPlayers == 5) {
            return numberOfPlayers;
        }
        return 6;
    }
    
    public void addConflictTokenToWarFace() {
        conflictTokenSet.addConflictTokenToWarFace();
        if (conflictTokenSet.isTimeForWar()) {
            PlayerActions.IS_TOUR_FINISHED = false;
            SoundEngine.playWarCorSound();
            doWar();
            conflictTokenSet.setConflictTokensToPeace();
        }
    }
    
    public ProgressToken popProgressToken() {
        return progressTokenStack.getProgressToken();
    }
    
    public int getNumberOfConflictTokens() {
        return conflictTokenSet.getNumberOfConflictTokens();
    }
    
    public int getNumberOfProgressToken() {
        return progressTokenStack.getNumberOfProgressToken();
    }
    
    public String getPathOfConflictToken(int index) {
        return conflictTokenSet.getPathOfConflictToken(index);
    }
    
    public void doWar() {
        gameBoard.doWar();
    }
}
