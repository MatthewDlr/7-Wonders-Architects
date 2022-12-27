package game.cards;

import java.io.File;

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
        CheckIfFileExist(path);
        return path;
    }

    private void CheckIfFileExist(String path) {
        if (!new File(path).exists()) {
            throw new IllegalArgumentException("Error in cards Files Check : Failed to load " + path);
        }
    }
}
