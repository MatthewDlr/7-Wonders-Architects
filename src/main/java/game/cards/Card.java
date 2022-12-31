package game.cards;

import errorsCenter.DataChecking;

public abstract class Card {
    
    protected String name;
    protected String category;
    protected String cardPath;
    
    public String getCardName() {
        return name;
    }
    
    public String getCardCategory() {
        return category;
    }
    
    public String getCardPath() {
        return cardPath;
    }
    
    protected String findCardPath() {
        String path = "src/main/resources/game/cards/" + name + "Card.png";
        DataChecking.checkIfFileIsCorrect(path);
        return path;
    }
    
}
