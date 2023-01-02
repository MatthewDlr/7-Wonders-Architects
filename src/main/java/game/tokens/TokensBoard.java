package game.tokens;

import game.tokens.conflict.ConflictTokenSet;
import game.tokens.progress.ProgressToken;
import game.tokens.progress.ProgressTokenStack;

public class TokensBoard {
    
    ConflictTokenSet conflictTokenSet; // TODO : figure out how to make these variables private (and can still be accessed by tests classes)
    ProgressTokenStack progressTokenStack;
    
    public TokensBoard(int numberOfPlayers) {
        conflictTokenSet = new ConflictTokenSet(determineNumberOfWarTokens(numberOfPlayers));
        progressTokenStack = new ProgressTokenStack();
    }
    
    private int determineNumberOfWarTokens(int numberOfPlayers) { // TODO : figure out why this method may be static
        if (numberOfPlayers <= 3) {
            return 3;
        }
        if ((numberOfPlayers == 4) || (numberOfPlayers == 5)) {
            return numberOfPlayers;
        }
        return 6;
    }
    
    public void addConflictTokenToWarFace() {
        conflictTokenSet.addConflictTokenToWarFace();
    }
    
    public ProgressToken getProgressToken() {
        return progressTokenStack.getProgressToken();
    }
    
    public int getNumberOfConflictTokens() {
        return conflictTokenSet.getNumberOfConflictTokens();
    }
}
