package controller.game;

import controller.AnimationsManager;
import game.player.Player;
import game.tokens.progress.ProgressToken;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class PlayerActions {
    
    private static final int PLAYER_TOKENS_SET = 4;
    
    private static Player CURRENT_PLAYER = null;
    
    private static boolean isActionAllowed(){
        return CURRENT_PLAYER != null;
    }
    
    public static void setCurrentPlayer(Player currentPlayer) {
        CURRENT_PLAYER = currentPlayer;
    }
    
    public static void playerActionIsDone() {
        CURRENT_PLAYER = null;
    }
    
    public static void getProgressToken(ProgressToken progressToken, ImageView token, Group tokensBoard) {
        if (!isActionAllowed()) {
            System.out.println("Action not allowed");
            return;
        }
        
        double fromCoordinates[] = getGlobalCoordinates(token, 0);
        double[] toCoordinates = getGlobalCoordinates(((Group) CURRENT_PLAYER.getWonderGroup().getChildren().get(PLAYER_TOKENS_SET)).getChildren().get(0), CURRENT_PLAYER.getWonderGroupRotation());
        toCoordinates[0] += CURRENT_PLAYER.getCoordinatesForNextProgressToken()[0];
        toCoordinates[1] += CURRENT_PLAYER.getCoordinatesForNextProgressToken()[1];
        System.out.println("By coordinates: " + (fromCoordinates[0] - toCoordinates[0]) + " x " + (fromCoordinates[1] - toCoordinates[1]) + " y");
        
        Animation translate = AnimationsManager.createTranslateTransition(token, 1000, fromCoordinates[0], fromCoordinates[1], toCoordinates[0], toCoordinates[1]);
        Animation scale = AnimationsManager.createScaleTransition(token, 1000, 0.83, 0.83);
        ParallelTransition parallelTransition = new ParallelTransition(translate, scale);
        parallelTransition.play();
        parallelTransition.setOnFinished(e -> {
            tokensBoard.getChildren().remove(token);
            token.setTranslateX(0);
            token.setTranslateY(0);
            ((Group) CURRENT_PLAYER.getWonderGroup().getChildren().get(PLAYER_TOKENS_SET)).getChildren().add(token);
            System.out.println(CURRENT_PLAYER.getWonderGroup().getChildren().get(PLAYER_TOKENS_SET).getId());
            
            token.setLayoutX(CURRENT_PLAYER.getCoordinatesForNextProgressTokenNoRotation() - 60 );
            token.setLayoutY(0);
        });
        
        CURRENT_PLAYER.addProgressToken(progressToken);
        //playerActionIsDone();
        
    }
    
    private static double[] getGlobalCoordinates(Node node, double rotation) {
        Point2D globalPos = node.localToScene(node.getLayoutBounds().getMinX(), node.getLayoutBounds().getMinY());
        double[] coordinates = {globalPos.getX(), globalPos.getY()};
        
        switch ((int) rotation) { // @Copilot
            case 90 -> {
                coordinates[0] -= node.getLayoutBounds().getWidth();
                return coordinates;
            }
            case 180 -> {
                coordinates[0] -= node.getLayoutBounds().getWidth();
                coordinates[1] -= node.getLayoutBounds().getHeight();
                return coordinates;
            }
            case 270 -> {
                coordinates[1] -= node.getLayoutBounds().getHeight();
                return coordinates;
            }
            default -> {
                return coordinates;
            }
        }
    }
    
}
