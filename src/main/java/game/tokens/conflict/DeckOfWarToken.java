package game.tokens.conflict;

import java.util.ArrayList;

public class DeckOfWarToken {
    
    private final ArrayList<DeckOfWarToken> warTokensSet;
    private final String WarTokenPath;
    
    public DeckOfWarToken() {
        warTokensSet = new ArrayList<>();
        WarTokenPath = "src/main/resources/game/tokens/WarToken.png";
    }
    
    public void AddWarToken(DeckOfWarToken warToken) {
        warTokensSet.add(warToken);
    }
    
    public String GetWarTokenPath() {
        return WarTokenPath;
    }
    
    public int GetWarTokensSetSize() {
        return warTokensSet.size();
    }
}
