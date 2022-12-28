package game;

import static org.testng.AssertJUnit.assertEquals;

import game.board.GameBoard;
import game.board.PlayerQueue;
import game.player.Player;
import game.tokens.TokensBoard;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;


public class GameTests {
    
    Game game = new Game(3, 4);
    ArrayList<Player> players = game.getListOfPlayers();
    GameBoard gameBoard = game.getGameBoard();
    PlayerQueue playerQueue = gameBoard.GetPlayerQueue();
    ArrayList<Player> AIPlayers = playerQueue.GetListOfAIPlayers();
    ArrayList<Player> humanPlayers = playerQueue.GetListOfHumanPlayers();
    
    TokensBoard tokensBoard = gameBoard.GetTokensBoard();
    
    @Test
    public void GameInitializationTest() {
        assertEquals("Invalid number of players", 7, players.size());
        assertEquals("Invalid number of AIPlayers", 4, AIPlayers.size());
        assertEquals("Invalid number of humanPlayers", 3, humanPlayers.size());
    }
}
