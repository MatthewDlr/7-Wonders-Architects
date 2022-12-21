package game.tokens.conflict;
import game.tokens.TokensBoard;

public class ConflictToken extends TokensBoard {

    boolean faceWar = false;

    public ConflictToken() {
    }

    public boolean IsFaceWar() {
        return faceWar;
    }

    public void SetFaceToWar() {
        this.faceWar = true;
    }

    public void SetFaceToPeace() {
        this.faceWar = false;
    }
}
