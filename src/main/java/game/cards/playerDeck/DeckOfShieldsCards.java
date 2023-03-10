package game.cards.playerDeck;

import game.cards.shields.ShieldCard1Trumpet;
import game.cards.shields.ShieldCard2Trumpets;
import game.cards.shields.ShieldsCard;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class DeckOfShieldsCards {
    
    private ArrayList<ShieldsCard> shieldsCardsSet;
    private PlayerCardsDeck playerCardsDeck;
    private ArrayList<ImageView> shieldsCardsSetUI;
    
    public DeckOfShieldsCards(PlayerCardsDeck playerCardsDeck) {
        shieldsCardsSet = new ArrayList<>();
        this.playerCardsDeck = playerCardsDeck;
        shieldsCardsSetUI = new ArrayList<>();
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
    
    public int getNumberOfCards() {
        return shieldsCardsSet.size();
    }
    
    public void removeTrumpetCards() {
        Iterable<ShieldsCard> cardsToRemove = (ArrayList<ShieldsCard>) shieldsCardsSet.clone();
        
        for (ShieldsCard card : cardsToRemove) {
            if (card instanceof ShieldCard1Trumpet || card instanceof ShieldCard2Trumpets) {
                shieldsCardsSet.remove(card);
            }
        }
        
        Iterable<ImageView> shieldsCardsSetUIToRemove = (ArrayList<ImageView>) shieldsCardsSetUI.clone();
        for (ImageView shieldCard : shieldsCardsSetUIToRemove) {
            if (shieldCard.getId().contains("1") || shieldCard.getId().contains("2")) {
                shieldCard.setVisible(false);
                shieldsCardsSetUI.remove(shieldCard);
            }
        }
    }
    
    public void addCardUI(ImageView cardToAdd) {
        shieldsCardsSetUI.add(cardToAdd);
    }
}
