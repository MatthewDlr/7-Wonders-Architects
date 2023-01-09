package game.tokens.progress;

import java.util.ArrayList;
import java.util.Collections;

public class ProgressTokenStack {
    
    private final ArrayList<ProgressToken> deckOfProgressTokens;
    
    public ProgressTokenStack() {
        deckOfProgressTokens = new ArrayList<>();
        
        createInstanceOfAllTokens();
        Collections.shuffle(deckOfProgressTokens);
    }
    
    private void createInstanceOfAllTokens() {
        deckOfProgressTokens.add(new Architecture());
        deckOfProgressTokens.add(new Artisanat());
        deckOfProgressTokens.add(new Culture());
        deckOfProgressTokens.add(new Culture());
        deckOfProgressTokens.add(new Décoration());
        deckOfProgressTokens.add(new Economie());
        deckOfProgressTokens.add(new Education());
        deckOfProgressTokens.add(new Ingénierie());
        deckOfProgressTokens.add(new Joaillerie());
        deckOfProgressTokens.add(new Politique());
        deckOfProgressTokens.add(new Propagande());
        deckOfProgressTokens.add(new Science());
        deckOfProgressTokens.add(new Stratégie());
        deckOfProgressTokens.add(new Tactique());
        deckOfProgressTokens.add(new Urbanisme());
    }
    
    public ProgressToken getProgressToken() {
        if (progressTokenDeckIsEmpty()) {
            throw new IllegalStateException("Error in TokensBoard.GetProgressToken : this function should never be called when the deck of progress tokens is empty");
        }
        return deckOfProgressTokens.remove(0);
    }
    
    public boolean progressTokenDeckIsEmpty() {
        return deckOfProgressTokens.isEmpty();
    }
    
    public int getNumberOfProgressTokensLeft() {
        return deckOfProgressTokens.size();
    }
    
    public ArrayList<ProgressToken> getDeckOfProgressTokens() {
        return deckOfProgressTokens;
    }
    
    public int getNumberOfProgressToken() {
        return deckOfProgressTokens.size();
    }
    
}
