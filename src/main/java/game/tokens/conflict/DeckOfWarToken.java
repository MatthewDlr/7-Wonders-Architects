package game.tokens.conflict;

import java.util.ArrayList;

public class DeckOfWarToken {
    private ArrayList<DeckOfWarToken> warTokensSet;
    private String WarTokenPath;

    public DeckOfWarToken() {
        this.warTokensSet = new ArrayList<>();
        this.WarTokenPath = "src/main/resources/game/tokens/WarToken.png";
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
