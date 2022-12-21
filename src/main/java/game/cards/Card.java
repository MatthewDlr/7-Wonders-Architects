package game.cards;

public abstract class Card {

    protected String name;
    protected String category;

    public String GetCardName() {
        return name;
    }

    public String GetCardCategory() {
        return category;
    }
}
