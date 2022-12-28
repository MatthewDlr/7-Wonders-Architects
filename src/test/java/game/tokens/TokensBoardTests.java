package game.tokens;


import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import org.junit.jupiter.api.Test;


public class TokensBoardTests {
    
    @Test
    public void TestTokensBoardInitialization() {
        TokensBoard tokensBoard = new TokensBoard(3);
        assertEquals(3, tokensBoard.conflictTokenSet.GetNumberOfConflictTokens());
        assertFalse(tokensBoard.conflictTokenSet.IsTimeForWar());
        assertEquals(0, tokensBoard.conflictTokenSet.GetNumberOfConflictTokensSetToWarFace());
        assertEquals(15, tokensBoard.progressTokenStack.GetNumberOfProgressTokensLeft());
    }
    
    @Test
    public void TestNumberOfConflictTokenFunction() {
        TokensBoard tokensBoard = new TokensBoard(2);
        assertEquals(3, tokensBoard.conflictTokenSet.GetNumberOfConflictTokens());
        TokensBoard tokensBoard2 = new TokensBoard(5);
        assertEquals(5, tokensBoard2.conflictTokenSet.GetNumberOfConflictTokens());
        TokensBoard tokensBoard3 = new TokensBoard(7);
        assertEquals(6, tokensBoard3.conflictTokenSet.GetNumberOfConflictTokens());
    }
    
    @Test
    public void TestConflictTokenWar() {
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
    public void TestProgressToken() {
        TokensBoard tokensBoard = new TokensBoard(4);
        
        tokensBoard.progressTokenStack.GetProgressToken();
        assertEquals(14, tokensBoard.progressTokenStack.GetNumberOfProgressTokensLeft());
        
        RemoveAllProgressTokens(tokensBoard);
        assertTrue(tokensBoard.progressTokenStack.ProgressTokenDeckIsEmpty());
    }
    
    public void RemoveAllProgressTokens(TokensBoard tokensBoard) {
        for (int i = 0; i < 14; i++) {
            tokensBoard.progressTokenStack.GetProgressToken();
        }
    }
    
}
