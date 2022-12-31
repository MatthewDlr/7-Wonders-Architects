package game.cards.playerDeck;

import game.cards.shields.ShieldCard1Trumpet;
import game.cards.shields.ShieldCard2Trumpets;
import game.cards.shields.ShieldsCard;
import java.util.ArrayList;

public class DeckOfShieldsCards {
    
    private final ArrayList<ShieldsCard> shieldsCardsSet;
    private PlayerDeck playerDeck;
    
    public DeckOfShieldsCards(PlayerDeck playerDeck) {
        shieldsCardsSet = new ArrayList<>();
        this.playerDeck = playerDeck;
    }
    
    void addCard(ShieldsCard cardToAdd) {
        shieldsCardsSet.add(cardToAdd);
        if (cardToAdd instanceof ShieldCard1Trumpet) {
            playerDeck.getGameBoard().addConflictTokenToWarFace();
            return;
        }
        if (cardToAdd instanceof ShieldCard2Trumpets) {
            playerDeck.getGameBoard().addConflictTokenToWarFace();
            playerDeck.getGameBoard().addConflictTokenToWarFace();
        }
    }
    
    public int getNumberOfShields() {
        return shieldsCardsSet.size();
    }

}
