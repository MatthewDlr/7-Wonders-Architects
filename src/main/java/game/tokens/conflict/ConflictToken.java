package game.tokens.conflict;

public class ConflictToken  {
    boolean faceWar ;

    public ConflictToken() {
        this.faceWar = false;
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
}
