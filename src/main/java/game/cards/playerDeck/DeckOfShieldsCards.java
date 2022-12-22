package game.cards.playerDeck;

import game.cards.shields.ShieldCard1Trumpet;
import game.cards.shields.ShieldCard2Trumpets;
import game.cards.shields.ShieldsCard;

import java.util.ArrayList;

public class DeckOfShieldsCards {
    private ArrayList<ShieldsCard> shieldsCardsSet;

    public DeckOfShieldsCards() {
        shieldsCardsSet = new ArrayList<>();
    }

    void AddCard(ShieldsCard cardToAdd) {
        shieldsCardsSet.add(cardToAdd);
        if (cardToAdd instanceof ShieldCard1Trumpet){
            //TODO TokensBoard.AddConflictTokenToWarFace();
            return;
        }
        if (cardToAdd instanceof ShieldCard2Trumpets){
            //TODO TokensBoard.AddConflictTokenToWarFace();
        }
    }

    public int GetNumberOfShields() {
        return shieldsCardsSet.size();
    }

}
