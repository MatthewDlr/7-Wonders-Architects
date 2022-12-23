package game.tokens;

import game.tokens.conflict.ConflictTokenSet;
import game.tokens.progress.ProgressTokenStack;

public class TokensBoard {
    ConflictTokenSet conflictTokenSet;
    ProgressTokenStack progressTokenStack;

    public TokensBoard(int numberOfPlayers) {
        this.conflictTokenSet = new ConflictTokenSet(DetermineNumberOfWarTokens(numberOfPlayers));
        this.progressTokenStack = new ProgressTokenStack();
    }

    private int DetermineNumberOfWarTokens(int numberOfPlayers) {
        if (numberOfPlayers <= 3) {
            return 3;
        }
        if (numberOfPlayers == 4 || numberOfPlayers == 5) {
            return numberOfPlayers;
        }
        return 6;
    }

}
