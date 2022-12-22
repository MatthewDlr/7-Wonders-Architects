package game.tokens;

import game.tokens.conflict.ConflictToken;
import game.tokens.progress.ProgressToken;
import java.util.ArrayList;
import java.util.Collections;

public class TokensBoard {
    private ArrayList<ConflictToken> deckOfConflictTokens = new ArrayList<>();
    private ArrayList<ProgressToken> deckOfProgressTokens;

    public TokensBoard(int numberOfPlayers) {
        for (int i = 0; i < DetermineNumberOfWarTokens(numberOfPlayers) ; i++) {
            deckOfConflictTokens.add(new ConflictToken());

        }
        deckOfProgressTokens = ProgressToken.CreateInstanceOfAllTokens();
        Collections.shuffle(deckOfProgressTokens);
    }

    public ProgressToken GetProgressToken() {
        if (deckOfProgressTokens.size() == 0) {
            //TODO: Figure out how to handle this
        }
        return deckOfProgressTokens.remove(0);
    }

    public boolean IsProgressTokenDeckEmpty() {
        return deckOfProgressTokens.isEmpty();
    }

    public int GetNumberOfProgressTokensLeft() {
        return deckOfProgressTokens.size();
    }

    //TODO: Figure out why this function is needed
    protected TokensBoard() {
    }

    private int DetermineNumberOfWarTokens(int numberOfPlayers) {
        if (numberOfPlayers <= 3) {
            return 3;
        }
        if (numberOfPlayers == 4 || numberOfPlayers == 5) {
            return numberOfPlayers;
        }
        return 6;
    }

    public void AddConflictTokenToWarFace() {
        for (ConflictToken conflictToken : deckOfConflictTokens) {
            if (!conflictToken.IsFaceWar()) {
                conflictToken.SetFaceToWar();
                break;
            }
        }
    }

    protected boolean IsTimeForWar() {
        int numberOfWarTokensSetToWarFace = 0;
        for (ConflictToken conflictToken : deckOfConflictTokens) {
            if (conflictToken.IsFaceWar()) {
                numberOfWarTokensSetToWarFace++;
            }
        }
        return numberOfWarTokensSetToWarFace == deckOfConflictTokens.size();
    }

    protected void SetConflictTokensToPeace() {
        for (ConflictToken conflictToken : deckOfConflictTokens) {
            conflictToken.SetFaceToPeace();
        }
    }

    protected int GetNumberOfConflictTokens() {
        return deckOfConflictTokens.size();
    }

    protected int GetNumberOfConflictTokensSetToWarFace() {
        int numberOfWarTokensSetToWarFace = 0;
        for (ConflictToken conflictToken : deckOfConflictTokens) {
            if (conflictToken.IsFaceWar()) {
                numberOfWarTokensSetToWarFace++;
            }
        }
        return numberOfWarTokensSetToWarFace;
    }


}
