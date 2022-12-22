package game.tokens;

import org.junit.jupiter.api.Test;
import static org.testng.AssertJUnit.*;

public class TokensBoardTests {

    @Test
    public void TestTokensBoardInitialization(){
        TokensBoard tokensBoard = new TokensBoard(3);
        assertEquals(3, tokensBoard.conflictTokenSet.GetNumberOfConflictTokens());
        assertFalse(tokensBoard.conflictTokenSet.IsTimeForWar());
        assertEquals(0, tokensBoard.conflictTokenSet.GetNumberOfConflictTokensSetToWarFace());
        assertEquals(15, tokensBoard.GetNumberOfProgressTokensLeft());
    }

    @Test
    public void TestNumberOfConflictTokenFunction(){
        TokensBoard tokensBoard = new TokensBoard(2);
        assertEquals(3, tokensBoard.conflictTokenSet.GetNumberOfConflictTokens());
        TokensBoard tokensBoard2 = new TokensBoard(5);
        assertEquals(5, tokensBoard2.conflictTokenSet.GetNumberOfConflictTokens());
        TokensBoard tokensBoard3 = new TokensBoard(7);
        assertEquals(6, tokensBoard3.conflictTokenSet.GetNumberOfConflictTokens());
    }

    @Test
    public void TestConflictTokenWar(){
        TokensBoard tokensBoard = new TokensBoard(4);

        tokensBoard.conflictTokenSet.AddConflictTokenToWarFace();
        assertEquals(1, tokensBoard.conflictTokenSet.GetNumberOfConflictTokensSetToWarFace());

        tokensBoard.conflictTokenSet.AddConflictTokenToWarFace();
        tokensBoard.conflictTokenSet.AddConflictTokenToWarFace();
        tokensBoard.conflictTokenSet.AddConflictTokenToWarFace();
        tokensBoard.conflictTokenSet.AddConflictTokenToWarFace();
        assertEquals(4, tokensBoard.conflictTokenSet.GetNumberOfConflictTokensSetToWarFace());

        assertTrue(tokensBoard.conflictTokenSet.IsTimeForWar());
        tokensBoard.conflictTokenSet.SetConflictTokensToPeace();
        assertFalse(tokensBoard.conflictTokenSet.IsTimeForWar());
        assertEquals(0, tokensBoard.conflictTokenSet.GetNumberOfConflictTokensSetToWarFace());
    }

    @Test
    public void TestProgressToken(){
        TokensBoard tokensBoard = new TokensBoard(4);

        tokensBoard.GetProgressToken();
        assertEquals(14, tokensBoard.GetNumberOfProgressTokensLeft());

        RemoveAllProgressTokens(tokensBoard);
        assertTrue(tokensBoard.IsProgressTokenDeckEmpty());
    }

    public void RemoveAllProgressTokens(TokensBoard tokensBoard){
        for (int i = 0; i < 14; i++) {
            tokensBoard.GetProgressToken();
        }
    }

}
