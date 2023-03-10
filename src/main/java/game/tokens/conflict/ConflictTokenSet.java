package game.tokens.conflict;

import java.util.ArrayList;

public class ConflictTokenSet {
    
    private final ArrayList<ConflictToken> listOfConflictTokens;
    private final int numberOfConflictTokens;
    
    public ConflictTokenSet(int numberOfConflictTokens) {
        listOfConflictTokens = new ArrayList<>();
        this.numberOfConflictTokens = numberOfConflictTokens;
        
        for (int i = 0; i < numberOfConflictTokens; i++) {
            listOfConflictTokens.add(new ConflictToken());
        }
    }
    
    public void addConflictTokenToWarFace() {
        for (ConflictToken conflictToken : listOfConflictTokens) {
            if (!conflictToken.isFaceToWar()) {
                conflictToken.setFaceToWar();
                return;
            }
        }
    }
    
    public boolean isTimeForWar() {
        int numberOfWarTokensSetToWarFace = 0;
        
        for (ConflictToken conflictToken : listOfConflictTokens) {
            if (conflictToken.isFaceToWar()) {
                numberOfWarTokensSetToWarFace++;
            }
        }
        return numberOfWarTokensSetToWarFace == numberOfConflictTokens;
    }
    
    public void setConflictTokensToPeace() {
        for (ConflictToken conflictToken : listOfConflictTokens) {
            conflictToken.setFaceToPeace();
        }
    }
    
    public int getNumberOfConflictTokens() {
        return numberOfConflictTokens;
    }
    
    public String getPathOfConflictToken(int index) {
        return listOfConflictTokens.get(index).getUsedFacePath();
    }
}
