package game.player;

import game.board.GameBoard;
import game.cards.playerDeck.DeckOfProgressTokens;
import game.cards.playerDeck.PlayerDeck;
import game.tokens.conflict.DeckOfWarToken;
import game.wonders.Wonders;

public abstract class Player {
    
    Wonders wonders;
    PlayerDeck playerDeck;
    DeckOfProgressTokens deckOfProgressTokens;
    DeckOfWarToken deckOfWarToken;
    private GameBoard gameBoard;
    
    public Player(Wonders playerWonder) {
        wonders = playerWonder;
        playerDeck = new PlayerDeck();
        deckOfProgressTokens = new DeckOfProgressTokens();
        deckOfWarToken = new DeckOfWarToken();
    }
    
    public boolean hasTheCat() {
        return playerDeck.hasTheCat();
    }
    
    public void removeTheCat() {
        playerDeck.removeTheCat();
    }
    
    public void gotTheCat() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        playerDeck.gotTheCat();
    }
    
    public String getWonderName() {
        return wonders.getName();
    }
    
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        playerDeck.setGameBoard(gameBoard);
    }
    
    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }
}