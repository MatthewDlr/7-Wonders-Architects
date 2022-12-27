package game.tokens.conflict;

public class ConflictToken {
    private boolean faceWar;
    private String WarFacePath, PeaceFacePath;


    public ConflictToken() {
        this.faceWar = false;
        this.WarFacePath = "src/main/resources/game/tokens/ConflictTokenWarFace.png";
        this.PeaceFacePath = "src/main/resources/game/tokens/ConflictTokenPeaceFace.png";
    }

    public boolean IsFaceToWar() {
        return faceWar;
    }

    public void SetFaceToWar() {
        this.faceWar = true;
    }

    public void SetFaceToPeace() {
        this.faceWar = false;
    }

    public String GetUsedFacePath() { // Written by @Copilot
        return faceWar ? WarFacePath : PeaceFacePath;
    }
}
