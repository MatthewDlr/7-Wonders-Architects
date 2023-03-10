package game.board;

import controller.ScoreViewController;
import game.cards.GameCardsStack;
import game.player.AIPlayer;
import game.player.Player;
import game.tokens.TokensBoard;
import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class GameBoard {
    
    private final TokensBoard tokensBoard;
    private final PlayerQueue playerQueue;
    private final gameUIBridge gameUIBridge;
    private final ArrayList<Player> listOfPlayers;
    private final GameCardsStack gameCardsStack;
    
    public GameBoard(ArrayList<Player> listOfPlayers) {
        tokensBoard = new TokensBoard(listOfPlayers.size(), this);
        playerQueue = new PlayerQueue(listOfPlayers);
        gameUIBridge = new gameUIBridge();
        gameCardsStack = new GameCardsStack();
        this.listOfPlayers = listOfPlayers;
        
        for (Player player : listOfPlayers) {
            player.setGameBoard(this);
        }
        gameUIBridge.setup(listOfPlayers, tokensBoard, playerQueue, gameCardsStack);
    }
    
    public void initialize() {
        gameUIBridge.setupBoard();
        gameUIBridge.setCurrentPlayer(playerQueue.getActualPlayer());
        if (playerQueue.getActualPlayer() instanceof AIPlayer) {
            playerAutoPlay(playerQueue.getActualPlayer());
        }
    }
    
    public void nextPlayerTurn() {
        isGameFinished();
        playerQueue.nextPlayer();
        gameUIBridge.setCurrentPlayer(playerQueue.getActualPlayer());
        Player player = playerQueue.getActualPlayer();
        
        if (player instanceof AIPlayer) {
            playerAutoPlay(player);
        }
    }
    
    private void playerAutoPlay(Player player) {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            int action = (int) (Math.random() * 3) + 1;
            switch (action) {
                case 1 -> gameUIBridge.aIplayTakeMainStackCard();
                case 2 -> gameUIBridge.aIplayTakeRightStackCard(playerQueue.getRightPlayer());
                case 3 -> gameUIBridge.aIplayTakeLeftStackCard(playerQueue.getLeftPlayer());
                default -> throw new IllegalStateException("Unexpected value: " + action);
            }
        });
        pause.play();
    }
    
    public TokensBoard getTokensBoard() {
        if (tokensBoard == null) {
            throw new IllegalStateException("TokensBoard is not initialized");
        }
        return tokensBoard;
    }
    
    public PlayerQueue getPlayerQueue() {
        return playerQueue;
    }
    
    public void addConflictTokenToWarFace() {
        tokensBoard.addConflictTokenToWarFace();
        for (int i = 0; i < tokensBoard.getNumberOfConflictTokens(); i++) {
            String path = tokensBoard.getPathOfConflictToken(i);
            gameUIBridge.updateConflictTokenPath(i, path);
        }
    }
    
    public void setFloorUIasBuilt(int floor) {
        gameUIBridge.setFloorAsBuilt(floor, playerQueue.getActualPlayer().getWonderName());
    }
    
    
    public void getProgressToken() {
        gameUIBridge.UIBridgeAllowUserToTakeAProgressToken();
    }
    
    public void getCatUI() {
        gameUIBridge.UIBridgeTakeTheCat();
    }
    
    public void removeCatForAllPlayers() {
        playerQueue.removeTheCatFromAllPlayers();
    }
    
    
    private void isGameFinished() {
        for (Player player : listOfPlayers) {
            if (player.getWonderLevel() >= 5) {
                ScoreViewController.setupScoreView(getPlayerScore(), listOfPlayers, gameUIBridge.getPaneUI());
            }
            System.out.println("Player wonder level: " + player.getWonderLevel());
        }
    }
    
    private ArrayList<Integer> getPlayerScore() {
        ArrayList<Integer> scores = new ArrayList<>();
        for (Player player : listOfPlayers) {
            scores.add(player.getNumberOfVictoryPoints());
        }
        scores.sort((o1, o2) -> o2 - o1);
        return scores;
    }
    
    public void doWar() {
        if (listOfPlayers.size() == 2) {
            doWarForTwoPlayers();
        } else {
            for (int i = 0; i < listOfPlayers.size(); i++) {
                Player player = playerQueue.getActualPlayer();
                if (player.getNumberOfShields() > playerQueue.getRightPlayer().getNumberOfShields()) {
                    player.addWarToken();
                    gameUIBridge.addWarTokenToPlayer(player);
                }
                if (player.getNumberOfShields() > playerQueue.getLeftPlayer().getNumberOfShields()) {
                    player.addWarToken();
                    gameUIBridge.addWarTokenToPlayer(player);
                }
                playerQueue.nextPlayer();
            }
        }
        removeTrumpetCardsForPlayers();
        tokensBoard.setConflictTokenNumberToZero();
        nextPlayerTurn();
    }
    
    private void doWarForTwoPlayers() {
        if (listOfPlayers.get(0).getNumberOfShields() > listOfPlayers.get(1).getNumberOfShields()) {
            listOfPlayers.get(0).addWarToken();
            gameUIBridge.addWarTokenToPlayer(listOfPlayers.get(0));
        } else if (listOfPlayers.get(0).getNumberOfShields() < listOfPlayers.get(1).getNumberOfShields()) {
            listOfPlayers.get(1).addWarToken();
            gameUIBridge.addWarTokenToPlayer(listOfPlayers.get(1));
        }
        System.out.println("War is a tie");
    }
    
    private void removeTrumpetCardsForPlayers() {
        for (Player player : listOfPlayers) {
            player.removeTrumpetCards();
        }
    }
}
