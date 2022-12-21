package game.cards.shields;

import game.cards.Card;

public abstract class ShieldsCard extends Card {

    protected int numberOfTrumpet ;

    public ShieldsCard(String name, int numberOfTrumpet) {
        this.category = "bouclier";
        this.name = name;
        this.numberOfTrumpet = numberOfTrumpet ;
    }

    public int getNumberOfTrumpet() {
        return numberOfTrumpet;
    }

}
