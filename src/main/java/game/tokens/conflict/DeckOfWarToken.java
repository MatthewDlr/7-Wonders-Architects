package game.tokens.conflict;

public class DeckOfWarToken {
    
    private int numberOfWarTokens;
    private final String warTokenPath;
    
    public DeckOfWarToken() {
        warTokenPath = "src/main/resources/game/tokens/WarToken.png";
        numberOfWarTokens = 0;
    }
    
    public void addWarToken() {
        numberOfWarTokens++;
    }
    
    public String getWarTokenPath() {
        return warTokenPath;
    }
    
    public int getNumberOfVictoryPoints() {
        return numberOfWarTokens * 3;
    }
    
    public double getNumberOfWarTokens() {
        return numberOfWarTokens;
    }
}
