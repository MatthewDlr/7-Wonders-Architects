package game.tokens.conflict;

import java.util.ArrayList;

public class ConflictTokenSet {
    private final ArrayList<ConflictToken> listOfConflictTokens;
    private final int numberOfConflictTokens;

    public ConflictTokenSet(int numberOfConflictTokens) {
        this.listOfConflictTokens = new ArrayList<>();
        this.numberOfConflictTokens = numberOfConflictTokens;

        for (int i = 0; i < numberOfConflictTokens; i++) {
            listOfConflictTokens.add(new ConflictToken());
        }
    }

    public void AddConflictTokenToWarFace() {
        for (ConflictToken conflictToken : listOfConflictTokens) {
            if (!conflictToken.IsFaceToWar()) {
                conflictToken.SetFaceToWar();
                return;
            }
        }
    }

    public boolean IsTimeForWar() {
        int numberOfWarTokensSetToWarFace = 0;

        for (ConflictToken conflictToken : listOfConflictTokens) {
            if (conflictToken.IsFaceToWar()) {
                numberOfWarTokensSetToWarFace++;
            }
        }
        return numberOfWarTokensSetToWarFace == numberOfConflictTokens;
        //TODO : do the war for the players
        //TODO : remove player's shields cards with trumpets
    }

    public void SetConflictTokensToPeace() {
        for (ConflictToken conflictToken : listOfConflictTokens) {
            conflictToken.SetFaceToPeace();
        }
    }

    public int GetNumberOfConflictTokens() {
        return numberOfConflictTokens;
    }

    public int GetNumberOfConflictTokensSetToWarFace() {
        int numberOfWarTokensSetToWarFace = 0;
        for (ConflictToken conflictToken : listOfConflictTokens) {
            if (conflictToken.IsFaceToWar()) {
                numberOfWarTokensSetToWarFace++;
            }
        }
        return numberOfWarTokensSetToWarFace;
    }
}
