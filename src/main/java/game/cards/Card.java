package game.cards;

import errorsCenter.DataChecking;

public abstract class Card {
    
    protected String name;
    protected String category;
    protected String cardPath;
    
    public String GetCardName() {
        return name;
    }
    
    public String GetCardCategory() {
        return category;
    }
    
    public String GetCardPath() {
        return cardPath;
    }
    
    protected String FindCardPath() {
        String path = "src/main/resources/game/cards/" + name + "Card.png";
        DataChecking.checkIfFileIsCorrect(path);
        return path;
    }
    
}
