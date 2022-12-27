package game.player;

import game.cards.playerDeck.DeckOfProgressTokens;
import game.cards.playerDeck.PlayerDeck;
import game.tokens.conflict.DeckOfWarToken;
import game.wonders.Wonders;

public abstract class Player {
    Wonders wonders;
    PlayerDeck playerDeck;
    DeckOfProgressTokens deckOfProgressTokens;
    DeckOfWarToken deckOfWarToken;

    public Player(Wonders playerWonder) {
        this.wonders = playerWonder;
        this.playerDeck = new PlayerDeck();
        this.deckOfProgressTokens = new DeckOfProgressTokens();
        this.deckOfWarToken = new DeckOfWarToken();
    }

    public boolean HasTheCat(){
        return playerDeck.HasTheCat();
    }

    public void RemoveTheCat(){
        playerDeck.RemoveTheCat();
    }

    public void GotTheCat() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        playerDeck.GotTheCat();
    }

}