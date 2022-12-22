package game.tokens;

import game.tokens.conflict.ConflictTokenSet;
import game.tokens.progress.ProgressToken;
import java.util.ArrayList;
import java.util.Collections;

public class TokensBoard {
    ConflictTokenSet conflictTokenSet;
    private ArrayList<ProgressToken> deckOfProgressTokens;
    private int numberOfWarTokens;
    private boolean hasTheCat ;

    public TokensBoard(int numberOfPlayers) {
        this.numberOfWarTokens = 0;
        this.hasTheCat = false;
        this.conflictTokenSet = new ConflictTokenSet(DetermineNumberOfWarTokens(numberOfPlayers));
        this.deckOfProgressTokens = ProgressToken.CreateInstanceOfAllTokens();
        Collections.shuffle(deckOfProgressTokens);
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

    public void AddWarWon() {
        numberOfWarTokens++;
    }

    public int GetWarTokensVictoryPoints() {
        return numberOfWarTokens * 3;
    }

    public ProgressToken GetProgressToken() {
        if (deckOfProgressTokens.size() == 0) {
            throw new IllegalStateException("Error: GetProgressToken should not be called when the deck of progress tokens is empty");
        }
        return deckOfProgressTokens.remove(0);
    }

    public boolean IsProgressTokenDeckEmpty() {
        return deckOfProgressTokens.isEmpty();
    }

    public int GetNumberOfProgressTokensLeft() {
        return deckOfProgressTokens.size();
    }

    public void GetTheCat() {
        //TODO : set the cat to false for all other players
        hasTheCat = true;
    }

}
