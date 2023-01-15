package controller.game;

import static controller.game.GameController.referenceCardsPositionX;
import static controller.game.GameController.referenceCardsPositionY;

import controller.AnimationsEngine;
import controller.SoundEngine;
import game.cards.Card;
import game.cards.GameCardsStack;
import game.player.Player;
import game.tokens.progress.ProgressToken;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class PlayerActions {
    
    private static GameController GAME_CONTROLLER;
    private static final int PLAYER_TOKENS_SET = 4;
    private static Player CURRENT_PLAYER = null;
    private static ArrayList<ImageView> LIST_OF_PROGRESS_TOKENS = new ArrayList<>();
    private static ImageView CAT = null;
    public static boolean IS_TOUR_FINISHED;
    
    public static void setListOfProgressTokens(ArrayList<ImageView> listOfProgressTokens) {
        LIST_OF_PROGRESS_TOKENS = listOfProgressTokens;
    }
    
    public static Player getCURRENT_PLAYER() {
        return CURRENT_PLAYER;
    }
    
    public static void setGameController(GameController controller) {
        GAME_CONTROLLER = controller;
    }
    
    public static void setCurrentPlayer(Player currentPlayer) {
        CURRENT_PLAYER = currentPlayer;
    }
    
    public static void playerActionIsDone() {
        CURRENT_PLAYER = null;
    }
    
    public static void getProgressToken(ProgressToken progressToken, ImageView token, AnchorPane pane) {
        for (ImageView tokenInBoard : LIST_OF_PROGRESS_TOKENS) {
            tokenInBoard.setDisable(true);
            AnimationsEngine.disableDropShadow(tokenInBoard);
        }
        double fromCoordinates[] = {token.getLayoutX(), token.getLayoutY()};
        double toX = referenceCardsPositionX[4] + CURRENT_PLAYER.getAnchorPane().getLayoutX() + CURRENT_PLAYER.getCoordinatesForNextProgressToken()[0];
        double toY = referenceCardsPositionY[4] + CURRENT_PLAYER.getAnchorPane().getLayoutY() + CURRENT_PLAYER.getCoordinatesForNextProgressToken()[1];
        double[] toCoordinates = {toX, toY};
        System.out.println("Sending token to wonder " + CURRENT_PLAYER.getWonderName() + " by coordinates: " + (fromCoordinates[0] - toCoordinates[0]) + " x " + (fromCoordinates[1] - toCoordinates[1]) + " y");
        
        Animation translate = AnimationsEngine.createTranslateTransition(token, 1000, fromCoordinates[0], fromCoordinates[1], toCoordinates[0], toCoordinates[1]);
        Animation scale = AnimationsEngine.createScaleTransition(token, 1000, 0.83, 0.83);
        ParallelTransition parallelTransition = new ParallelTransition(translate, scale);
        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {
            GAME_CONTROLLER.deleteProgressToken(token, progressToken);
            GAME_CONTROLLER.newProgressToken();
            GAME_CONTROLLER.nextPlayer();
        });
        CURRENT_PLAYER.addProgressToken(progressToken);
        
    }
    
    public static void getNewCardFromGameCardsStack(GameCardsStack gameCardsStack, ImageView gameCardsStackReference, AnchorPane pane) {
        Card card = gameCardsStack.popCard();
        ImageView newCard = FastSetup.createNewCardUI(card, gameCardsStack.getSize());
        pane.getChildren().add(3, newCard);
        CURRENT_PLAYER.addUIcard(newCard);
        newCard.setLayoutX(gameCardsStackReference.getLayoutX());
        newCard.setLayoutY(gameCardsStackReference.getLayoutY());
        addCardUIToPlayer(card, newCard, true);
    }
    
    public static void getNewCardsFromWonderCardsStack(Player player, ImageView wonderCardsStack, AnchorPane pane) {
        Card card = player.popWonderCard();
        FastSetup.updateImage(wonderCardsStack, player.getWonderTopCardPath());
        ImageView newCard = FastSetup.createNewCardUI(card, player.getWonderCardsStackSize());
        pane.getChildren().add(3, newCard);
        CURRENT_PLAYER.addUIcard(newCard);
        newCard.setLayoutX(wonderCardsStack.getLayoutX() + player.getAnchorPane().getLayoutX());
        newCard.setLayoutY(wonderCardsStack.getLayoutY() + player.getAnchorPane().getLayoutY());
        addCardUIToPlayer(card, newCard, false);
    }
    
    private static void addCardUIToPlayer(Card card, ImageView newCard, boolean fromMainCardStack) {
        
        double toX = CURRENT_PLAYER.getAnchorPane().getLayoutX();
        double toY = CURRENT_PLAYER.getAnchorPane().getLayoutY();
        switch (card.getCardCategory()) {
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
        Animation translate = AnimationsEngine.createTranslateTransition(newCard, 1250, newCard.getLayoutX(), newCard.getLayoutY(), toX, toY);
        double scaleX, scaleY;
        Animation scale = AnimationsEngine.createScaleTransition(newCard, 1000, 0.5, 0.5);
        Animation rotation = AnimationsEngine.createRotateTransition(newCard, 500, 0, CURRENT_PLAYER.getWonderGroupRotation());
        ParallelTransition parallelTransition = new ParallelTransition(translate, scale, rotation);
        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {
            IS_TOUR_FINISHED = true;
            CURRENT_PLAYER.getPlayerDeck().addCard(card);
            if (IS_TOUR_FINISHED) {
                GAME_CONTROLLER.nextPlayer();
            }
        });
    }
    
    public static void getCatUI(AnchorPane pane) {
        SoundEngine.playCatSound();
        if (CAT == null) {
            CAT = new ImageView();
            FastSetup.updateImage(CAT, "src/main/resources/game/tokens/Cat.png");
            CAT.setFitHeight(85);
            CAT.setFitWidth(40);
            pane.getChildren().add(CAT);
        }
        
        double toX = referenceCardsPositionX[6] + CURRENT_PLAYER.getAnchorPane().getLayoutX();
        double toY = referenceCardsPositionY[6] + CURRENT_PLAYER.getAnchorPane().getLayoutY();
        Animation translate = AnimationsEngine.createTranslateTransitionTo(CAT, 1000, toX, toY);
        translate.play();
        translate.setOnFinished(event -> {
            GAME_CONTROLLER.nextPlayer();
        });
    }
}
