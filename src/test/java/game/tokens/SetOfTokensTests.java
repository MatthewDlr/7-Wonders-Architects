package game.tokens;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.*;

public class SetOfTokensTests {

    @Test
    public void TestTokensBoardInitialization(){
        TokensBoard tokensBoard = new TokensBoard(3);
        assertEquals(3, tokensBoard.GetNumberOfConflictTokens());
        assertFalse(tokensBoard.IsTimeForWar());
        assertEquals(0, tokensBoard.GetNumberOfConflictTokensSetToWarFace());
        assertEquals(15, tokensBoard.GetNumberOfProgressTokensLeft());
    }

    @Test
    public void TestNumberOfConflictTokenFunction(){
        TokensBoard tokensBoard = new TokensBoard(2);
        assertEquals(3, tokensBoard.GetNumberOfConflictTokens());
        TokensBoard tokensBoard2 = new TokensBoard(5);
        assertEquals(5, tokensBoard2.GetNumberOfConflictTokens());
        TokensBoard tokensBoard3 = new TokensBoard(7);
        assertEquals(6, tokensBoard3.GetNumberOfConflictTokens());
    }

    @Test
    public void TestConflictTokenWar(){
        TokensBoard tokensBoard = new TokensBoard(4);

        tokensBoard.AddConflictTokenToWarFace();
        assertEquals(1, tokensBoard.GetNumberOfConflictTokensSetToWarFace());

        tokensBoard.AddConflictTokenToWarFace();
        tokensBoard.AddConflictTokenToWarFace();
        tokensBoard.AddConflictTokenToWarFace();
        assertEquals(4, tokensBoard.GetNumberOfConflictTokensSetToWarFace());

        assertTrue(tokensBoard.IsTimeForWar());
        tokensBoard.SetConflictTokensToPeace();
        assertFalse(tokensBoard.IsTimeForWar());
        assertEquals(0, tokensBoard.GetNumberOfConflictTokensSetToWarFace());
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
