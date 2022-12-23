package game;

import game.board.GameBoard;
import game.board.PlayerQueue;
import game.tokens.TokensBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import game.player.Player;

import static org.testng.AssertJUnit.assertEquals;


public class GameTests {

    Game game = new Game(3, 4);
    ArrayList<Player> players = game.GetListOfPlayers();
    GameBoard gameBoard = game.GetGameBoard();
    PlayerQueue playerQueue = gameBoard.GetPlayerQueue();
    ArrayList<Player> AIPlayers = playerQueue.GetListOfAIPlayers();
    ArrayList<Player> humanPlayers = playerQueue.GetListOfHumanPlayers();

    TokensBoard tokensBoard = gameBoard.GetTokensBoard();

    @Test
    public void GameInitializationTest(){
        assertEquals("Invalid number of players", 7, players.size());
        assertEquals("Invalid number of AIPlayers", 4, AIPlayers.size());
        assertEquals("Invalid number of humanPlayers", 3, humanPlayers.size());
    }
}
