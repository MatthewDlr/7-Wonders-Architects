package game.tokens;


import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import org.junit.jupiter.api.Test;


public class TokensBoardTests {
    
    @Test
    public void TestTokensBoardInitialization() {
        TokensBoard tokensBoard = new TokensBoard(3);
        assertEquals(3, tokensBoard.conflictTokenSet.getNumberOfConflictTokens());
        assertFalse(tokensBoard.conflictTokenSet.isTimeForWar());
        assertEquals(0, tokensBoard.conflictTokenSet.getNumberOfConflictTokensSetToWarFace());
        assertEquals(15, tokensBoard.progressTokenStack.getNumberOfProgressTokensLeft());
    }
    
    @Test
    public void TestNumberOfConflictTokenFunction() {
        TokensBoard tokensBoard = new TokensBoard(2);
        assertEquals(3, tokensBoard.conflictTokenSet.getNumberOfConflictTokens());
        TokensBoard tokensBoard2 = new TokensBoard(5);
        assertEquals(5, tokensBoard2.conflictTokenSet.getNumberOfConflictTokens());
        TokensBoard tokensBoard3 = new TokensBoard(7);
        assertEquals(6, tokensBoard3.conflictTokenSet.getNumberOfConflictTokens());
    }
    
    @Test
    public void TestConflictTokenWar() {
        TokensBoard tokensBoard = new TokensBoard(4);
        
        tokensBoard.conflictTokenSet.addConflictTokenToWarFace();
        assertEquals(1, tokensBoard.conflictTokenSet.getNumberOfConflictTokensSetToWarFace());
        
        tokensBoard.conflictTokenSet.addConflictTokenToWarFace();
        tokensBoard.conflictTokenSet.addConflictTokenToWarFace();
        tokensBoard.conflictTokenSet.addConflictTokenToWarFace();
        tokensBoard.conflictTokenSet.addConflictTokenToWarFace();
        assertEquals(4, tokensBoard.conflictTokenSet.getNumberOfConflictTokensSetToWarFace());
        
        assertTrue(tokensBoard.conflictTokenSet.isTimeForWar());
        tokensBoard.conflictTokenSet.setConflictTokensToPeace();
        assertFalse(tokensBoard.conflictTokenSet.isTimeForWar());
        assertEquals(0, tokensBoard.conflictTokenSet.getNumberOfConflictTokensSetToWarFace());
    }
    
    @Test
    public void TestProgressToken() {
        TokensBoard tokensBoard = new TokensBoard(4);
        
        tokensBoard.progressTokenStack.getProgressToken();
        assertEquals(14, tokensBoard.progressTokenStack.getNumberOfProgressTokensLeft());
        
        RemoveAllProgressTokens(tokensBoard);
        assertTrue(tokensBoard.progressTokenStack.progressTokenDeckIsEmpty());
    }
    
    public void RemoveAllProgressTokens(TokensBoard tokensBoard) {
        for (int i = 0; i < 14; i++) {
            tokensBoard.progressTokenStack.getProgressToken();
        }
    }
    
}
