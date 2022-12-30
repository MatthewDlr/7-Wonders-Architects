package game.board;

import game.player.Player;
import game.tokens.TokensBoard;
import java.util.ArrayList;

public class GameBoard {

    private TokensBoard tokensBoard;
    private PlayerQueue playerQueue;
    private GameConnect gameConnect;
    private ArrayList<Player> listOfPlayers;

    public GameBoard(ArrayList<Player> listOfPlayers) {
        tokensBoard = new TokensBoard(listOfPlayers.size());
        playerQueue = new PlayerQueue(listOfPlayers);
        gameConnect = new GameConnect();
        this.listOfPlayers = listOfPlayers;
        displayUIOfChosenPlayer();
    }

    public TokensBoard getTokensBoard() {
        return tokensBoard;
    }

    public PlayerQueue getPlayerQueue() {
        return playerQueue;
    }

    private void displayUIOfChosenPlayer() {
        for (Player player : listOfPlayers) {
            GameConnect.gameController.showWonderElements(player.getWonderName());
        }
    }

}
