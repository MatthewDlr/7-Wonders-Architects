package controller.game;

import static controller.game.GameController.referenceCardsPositionX;
import static controller.game.GameController.referenceCardsPositionY;

import controller.AnimationsManager;
import game.cards.Card;
import game.cards.GameCardsStack;
import game.player.Player;
import game.tokens.progress.ProgressToken;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class PlayerActions {
    
    private static GameController GAME_CONTROLLER;
    private static final int PLAYER_TOKENS_SET = 4;
    private static Player CURRENT_PLAYER = null;
    private static boolean IS_PROGRESS_TOKEN_ALLOWED = true;
    
    public static Player getCURRENT_PLAYER() {
        return CURRENT_PLAYER;
    }
    
    public static void setGameController(GameController controller) {
        GAME_CONTROLLER = controller;
    }
    
    private static boolean getProgressTokenAllowed(){
        return IS_PROGRESS_TOKEN_ALLOWED != false;
    }
    
    private static boolean doesAPlayerCanPlay(){
        return CURRENT_PLAYER != null;
    }
    
    public static void setCurrentPlayer(Player currentPlayer) {
        CURRENT_PLAYER = currentPlayer;
    }
    
    public static void playerActionIsDone() {
        CURRENT_PLAYER = null;
    }
    
    public static void getProgressToken(ProgressToken progressToken, ImageView token, AnchorPane pane) {
        if (!getProgressTokenAllowed()) {
            System.out.println("Action not allowed");
            return;
        }
        token.setOnMouseClicked(null);
        double fromCoordinates[] = {token.getLayoutX(), token.getLayoutY()};
        double toX = referenceCardsPositionX[4] + CURRENT_PLAYER.getAnchorPane().getLayoutX() + CURRENT_PLAYER.getCoordinatesForNextProgressToken()[0];
        double toY = referenceCardsPositionY[4] + CURRENT_PLAYER.getAnchorPane().getLayoutY() + CURRENT_PLAYER.getCoordinatesForNextProgressToken()[1];
        double[] toCoordinates = {toX, toY};
        System.out.println("Sending token to wonder " + CURRENT_PLAYER.getWonderName() + " by coordinates: " + (fromCoordinates[0] - toCoordinates[0]) + " x " + (fromCoordinates[1] - toCoordinates[1]) + " y");
        
        Animation translate = AnimationsManager.createTranslateTransition(token, 1000, fromCoordinates[0], fromCoordinates[1], toCoordinates[0], toCoordinates[1]);
        Animation scale = AnimationsManager.createScaleTransition(token, 1000, 0.83, 0.83);
        ParallelTransition parallelTransition = new ParallelTransition(translate, scale);
        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {
            GAME_CONTROLLER.deleteProgressToken(token);
            GAME_CONTROLLER.newProgressToken();
        });
        
        CURRENT_PLAYER.addProgressToken(progressToken);
        //IS_PROGRESS_TOKEN_ALLOWED = false;
        
    }
    
    public static void getNewCard(GameCardsStack gameCardsStack, ImageView gameCardsStackReference, AnchorPane pane) {
        if (!doesAPlayerCanPlay()) {
            System.out.println("Action Not allowed");
            return;
        }
        Card card = gameCardsStack.popCard();
        ImageView newCard = FastSetup.createNewCardUI(card, gameCardsStack.getSize());
        pane.getChildren().add(3, newCard);
        CURRENT_PLAYER.addUIcards(newCard);
        newCard.setLayoutX(gameCardsStackReference.getLayoutX());
        newCard.setLayoutY(gameCardsStackReference.getLayoutY());
        
        double toX = CURRENT_PLAYER.getAnchorPane().getLayoutX();
        double toY = CURRENT_PLAYER.getAnchorPane().getLayoutY();
        
        switch (card.getCardCategory()){
            case "science" -> {
                toX += referenceCardsPositionX[1];
                toY += referenceCardsPositionY[1] + CURRENT_PLAYER.getPlayerDeck().getNumberOfScienceCards() * -30;
            }
            case "resources" -> {
                toX += referenceCardsPositionX[0];
                toY += referenceCardsPositionY[0] + CURRENT_PLAYER.getPlayerDeck().getNumberOfResourcesCards() * -30;
            }
            case "shield" -> {
                toX += referenceCardsPositionX[2];
                toY += referenceCardsPositionY[2] + CURRENT_PLAYER.getPlayerDeck().getNumberOfShieldsCards() * -30;
            }
            case "victory" -> {
                toX += referenceCardsPositionX[3];
                toY += referenceCardsPositionY[3] + CURRENT_PLAYER.getPlayerDeck().getNumberOfVictoryPointsCards() * -30;
            }
            default -> throw new IllegalStateException("Unexpected value: " + card.getCardCategory());
        }
        Animation translate = AnimationsManager.createTranslateTransition(newCard, 1000, newCard.getLayoutX(), newCard.getLayoutY(), toX, toY);
        Animation scale = AnimationsManager.createScaleTransition(newCard, 1000, 0.5, 0.5);
        Animation rotation = AnimationsManager.createRotateTransition(newCard, 500, 0, CURRENT_PLAYER.getWonderGroupRotation());
        ParallelTransition parallelTransition = new ParallelTransition(translate, scale, rotation);
        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {
            CURRENT_PLAYER.getPlayerDeck().addCard(card);
        });
        //playerActionIsDone();
    }

}
