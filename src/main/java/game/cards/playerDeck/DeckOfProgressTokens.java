package game.cards.playerDeck;

import game.tokens.progress.ProgressToken;
import java.util.ArrayList;

public class DeckOfProgressTokens {
    
    private final ArrayList<ProgressToken> progressTokensSet;
    
    public DeckOfProgressTokens() {
        progressTokensSet = new ArrayList<>();
    }
    
    public void addProgressToken(ProgressToken progressToken) {
        progressTokensSet.add(progressToken);
    }
    
    public void removeProgressToken(ProgressToken progressToken) {
        progressTokensSet.remove(progressToken);
    }
    
    
}

