package game.cards.playerDeck;

import game.cards.shields.ShieldCard1Trumpet;
import game.cards.shields.ShieldCard2Trumpets;
import game.cards.shields.ShieldsCard;
import java.util.ArrayList;

public class DeckOfShieldsCards {
    
    private final ArrayList<ShieldsCard> shieldsCardsSet;
    private PlayerCardsDeck playerCardsDeck;
    
    public DeckOfShieldsCards(PlayerCardsDeck playerCardsDeck) {
        shieldsCardsSet = new ArrayList<>();
        this.playerCardsDeck = playerCardsDeck;
    }
    
    void addCard(ShieldsCard cardToAdd) {
        shieldsCardsSet.add(cardToAdd);
        if (cardToAdd instanceof ShieldCard1Trumpet) {
            playerCardsDeck.getGameBoard().addConflictTokenToWarFace();
            return;
        }
        if (cardToAdd instanceof ShieldCard2Trumpets) {
            playerCardsDeck.getGameBoard().addConflictTokenToWarFace();
            playerCardsDeck.getGameBoard().addConflictTokenToWarFace();
        }
    }
    
    public int getNumberOfShields() {
        return shieldsCardsSet.size();
    }
    
    public int getNumberOfCards() {
        return shieldsCardsSet.size();
    }
}
