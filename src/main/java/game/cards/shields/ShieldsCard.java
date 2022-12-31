package game.cards.shields;

import game.cards.Card;

public abstract class ShieldsCard extends Card {
    
    protected int numberOfTrumpet;
    
    public ShieldsCard(String name, int numberOfTrumpet) {
        category = "shield";
        this.name = name;
        this.numberOfTrumpet = numberOfTrumpet;
        cardPath = findCardPath();
    }
    
    public int getNumberOfTrumpet() {
        return numberOfTrumpet;
    }
    
}
