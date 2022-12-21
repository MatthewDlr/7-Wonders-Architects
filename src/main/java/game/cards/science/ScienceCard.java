package game.cards.science;

import game.cards.Card;

public abstract class ScienceCard extends Card {

    public ScienceCard(String name) {
        this.name = name;
        this.category = "science";
    }
}

