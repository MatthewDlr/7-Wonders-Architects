package game.cards.playerDeck;

import game.tokens.progress.ProgressToken;

import java.util.ArrayList;

public class DeckOfProgressTokens {
    private final ArrayList<ProgressToken> progressTokens;

    public DeckOfProgressTokens() {
        this.progressTokens = new ArrayList<>();
    }

    public void AddProgressToken(ProgressToken progressToken) {
        progressTokens.add(progressToken);
    }


}

