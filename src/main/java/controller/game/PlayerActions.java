package controller.game;

import controller.AnimationsManager;
import game.player.Player;
import game.tokens.progress.ProgressToken;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class PlayerActions {
    
    private static final int PLAYER_TOKENS_SET = 4;
    private static Player CURRENT_PLAYER = null;
    private static boolean IS_PROGRESS_TOKEN_ALLOWED = false;
    
    private static boolean getProgressTokenAllowed(){
        return IS_PROGRESS_TOKEN_ALLOWED != false;
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
        double toX =((Pane) pane.getChildren().get(3)).getChildren().get(6).getLayoutX() + pane.getChildren().get(3).getLayoutX() + CURRENT_PLAYER.getCoordinatesForNextProgressToken()[0];
        double toY =((Pane) pane.getChildren().get(3)).getChildren().get(6).getLayoutY() + pane.getChildren().get(3).getLayoutY() + CURRENT_PLAYER.getCoordinatesForNextProgressToken()[1];
        double[] toCoordinates = {toX, toY};
        System.out.println("Sending token to wonder " + CURRENT_PLAYER.getWonderName() + " by coordinates: " + (fromCoordinates[0] - toCoordinates[0]) + " x " + (fromCoordinates[1] - toCoordinates[1]) + " y");
        
        Animation translate = AnimationsManager.createTranslateTransition(token, 1000, fromCoordinates[0], fromCoordinates[1], toCoordinates[0], toCoordinates[1]);
        Animation scale = AnimationsManager.createScaleTransition(token, 1000, 0.83, 0.83);
        ParallelTransition parallelTransition = new ParallelTransition(translate, scale);
        parallelTransition.play();
        
        CURRENT_PLAYER.addProgressToken(progressToken);
        IS_PROGRESS_TOKEN_ALLOWED = false;
        playerActionIsDone();
        
    }
    
}
