package game.player;

import game.cards.playerDeck.DeckOfProgressTokens;
import game.cards.playerDeck.PlayerDeck;
import game.wonders.Wonders;

public abstract class Player {
    Wonders wonders;
    PlayerDeck playerDeck;
    DeckOfProgressTokens deckOfProgressTokens;
    private int numberOfWarTokens;
    private boolean hasTheCat;

    public Player(Wonders playerWonder) {
        this.wonders = playerWonder;
        this.playerDeck = new PlayerDeck();
        this.numberOfWarTokens = 0;
        this.hasTheCat = false;
        this.deckOfProgressTokens = new DeckOfProgressTokens();
    }

    public void AddWarWon() {
        numberOfWarTokens++;
    }

    public int GetWarTokensVictoryPoints() {
        return numberOfWarTokens * 3;
    }

    public void GetTheCat() {
        //TODO : set the cat to false for all other players
        hasTheCat = true;
    }

}