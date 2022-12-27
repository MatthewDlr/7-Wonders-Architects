package game.cards.playerDeck;

import game.tokens.progress.ProgressToken;

import java.util.ArrayList;

public class DeckOfProgressTokens {
    private final ArrayList<ProgressToken> progressTokensSet;

    public DeckOfProgressTokens() {
        this.progressTokensSet = new ArrayList<>();
    }

    public void AddProgressToken(ProgressToken progressToken) {
        progressTokensSet.add(progressToken);
    }

    public void RemoveProgressToken(ProgressToken progressToken) {
        progressTokensSet.remove(progressToken);
    }


}

