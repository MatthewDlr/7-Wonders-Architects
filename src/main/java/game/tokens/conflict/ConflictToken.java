package game.tokens.conflict;

public class ConflictToken {
    
    private boolean faceWar;
    private final String WarFacePath;
    private final String PeaceFacePath;
    
    
    public ConflictToken() {
        faceWar = false;
        WarFacePath = "src/main/resources/game/tokens/ConflictTokenWarFace.png";
        PeaceFacePath = "src/main/resources/game/tokens/ConflictTokenPeaceFace.png";
    }
    
    public boolean isFaceToWar() {
        return faceWar;
    }
    
    public void setFaceToWar() {
        faceWar = true;
    }
    
    public void setFaceToPeace() {
        faceWar = false;
    }
    
    public String getUsedFacePath() { // Written by @Copilot
        return faceWar ? WarFacePath : PeaceFacePath;
    }
}
