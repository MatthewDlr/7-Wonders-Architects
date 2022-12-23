package game.board;

import game.player.Player;
import game.tokens.TokensBoard;

import java.util.ArrayList;

public class GameBoard {
    private final TokensBoard tokensBoard;
    private final PlayerQueue playerQueue;

    public GameBoard(ArrayList<Player> listOfPlayers) {
        this.tokensBoard = new TokensBoard(listOfPlayers.size());
        this.playerQueue = new PlayerQueue(listOfPlayers);
    }

    public TokensBoard GetTokensBoard() {
        return tokensBoard;
    }

    public PlayerQueue GetPlayerQueue() {
        return playerQueue;
    }
}
