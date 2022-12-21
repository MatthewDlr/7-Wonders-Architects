package game.cards.resources;

import game.cards.Card;

public abstract class ResourcesCard extends Card {

    public ResourcesCard(String name) {
        this.name = name;
        this.category = "ressources";
    }
}
