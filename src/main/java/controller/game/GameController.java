package controller.game;

import errorsCenter.DataChecking;
import errorsCenter.ErrorsHandler;
import game.Game;
import game.board.gameUIBridge;
import java.io.File;
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

public class GameController extends gameUIBridge {
    
    @FXML
    Group loadingGroup;
    @FXML
    MediaView loadingAnimationFrame;
    @FXML
    Rectangle whiteForeground;
    @FXML
    Label startingTest;
    @FXML
    Group alexandrie, babylon, ephese, halicarnasse, olympie, rhodes, gizeh;
    @FXML
    ImageView alexandrieCardsStack, babylonCardsStack, epheseCardsStack, halicarnasseCardsStack, olympieCardsStack, rhodesCardsStack, gizehCardsStack, gameCardsStack;
    
    MediaPlayer loadingAnimationMedia;
    ArrayList<Group> playedWonders = new ArrayList<>();
    private Game game;
    
    
    public void initialize(int numberOfHumans, int numberOfAI) {
        
        File loadingAnimationFile = new File("src/main/resources/videos/LoadingAnimation.mp4");
        DataChecking.checkIfFileIsCorrect(String.valueOf(loadingAnimationFile));
        Media loadingAnimationMedia = new Media(loadingAnimationFile.toURI().toString());
        this.loadingAnimationMedia = new MediaPlayer(loadingAnimationMedia);
        loadingAnimationFrame.setMediaPlayer(this.loadingAnimationMedia);
        showLoadingAnimation();
        
        System.out.println("Number of Humans : " + numberOfHumans);
        System.out.println("Number of AI : " + numberOfAI);
        
        int time1 = (int) System.nanoTime();
        setGameController(this);
        game = new Game(numberOfHumans, numberOfAI);
        game.launchGame();
        
        System.out.println("Game Started");
        int time2 = (int) System.nanoTime();
        System.out.println("Loading Time : " + (time2 - time1) / 1000000 + " ms");
    }
    
    private void showLoadingAnimation() {
        loadingGroup.setVisible(true);
        loadingAnimationMedia.setAutoPlay(true);
        loadingAnimationMedia.setCycleCount(1);
        loadingAnimationMedia.play();
        loadingAnimationMedia.setOnEndOfMedia(() -> {
            hideLoadingAnimation();
        });
        ErrorsHandler.handleErrorsInVideo(loadingAnimationMedia, "src/main/resources/videos/LoadingAnimation.mp4", loadingAnimationFrame);
    }
    
    private void hideLoadingAnimation() {
        FadeTransition fadeTransition = new FadeTransition(javafx.util.Duration.millis(550), whiteForeground);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.interpolatorProperty().set(Interpolator.EASE_BOTH);
        
        FadeTransition fadeTransition2 = new FadeTransition(javafx.util.Duration.millis(500), startingTest);
        fadeTransition2.setFromValue(1);
        fadeTransition2.interpolatorProperty().set(Interpolator.EASE_BOTH);
        
        FadeTransition fadeTransition3 = new FadeTransition(javafx.util.Duration.millis(300), loadingAnimationFrame);
        fadeTransition3.setFromValue(1);
        fadeTransition3.setToValue(0);
        fadeTransition3.interpolatorProperty().set(Interpolator.EASE_BOTH);
        
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, fadeTransition2, fadeTransition3);
        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {
            loadingGroup.setVisible(false);
        });
    }
    
    public void setPlaceOfWonders(String wonderName, int x, int y, int rotation) {
        
        switch (wonderName) {
            case "Alexandrie" -> {
                alexandrie.setOpacity(1);
                playedWonders.add(alexandrie);
                alexandrie.setLayoutX(x);
                alexandrie.setLayoutY(y);
                alexandrie.setRotate(rotation);
            }
            case "Babylone" -> {
                babylon.setOpacity(1);
                playedWonders.add(babylon);
                babylon.setLayoutX(x);
                babylon.setLayoutY(y);
                babylon.setRotate(rotation);
            }
            case "Ephese" -> {
                ephese.setOpacity(1);
                playedWonders.add(ephese);
                ephese.setLayoutX(x);
                ephese.setLayoutY(y);
                ephese.setRotate(rotation);
            }
            case "Halicarnasse" -> {
                halicarnasse.setOpacity(1);
                playedWonders.add(halicarnasse);
                halicarnasse.setLayoutX(x);
                halicarnasse.setLayoutY(y);
                halicarnasse.setRotate(rotation);
            }
            case "Olympie" -> {
                olympie.setOpacity(1);
                playedWonders.add(olympie);
                olympie.setLayoutX(x);
                olympie.setLayoutY(y);
                olympie.setRotate(rotation);
            }
            case "Rhodes" -> {
                rhodes.setOpacity(1);
                playedWonders.add(rhodes);
                rhodes.setLayoutX(x);
                rhodes.setLayoutY(y);
                rhodes.setRotate(rotation);
            }
            case "Gizeh" -> {
                gizeh.setOpacity(1);
                playedWonders.add(gizeh);
                gizeh.setLayoutX(x);
                gizeh.setLayoutY(y);
                gizeh.setRotate(rotation);
            }
            default -> throw new IllegalStateException("Error in UI setup : Unrecognized wonder (" + wonderName + ")");
        }
    }
    
    public void updateWonderTopCard(String cardPath, String wonderName) {
        
        System.out.println("Updating wonder top card : " + cardPath);
        File file = new File(cardPath);
        Image newImage = new Image(file.toURI().toString());
    
        (switch (wonderName) {
            case "Alexandrie" -> alexandrieCardsStack;
            case "Babylone" -> babylonCardsStack;
            case "Ephese" -> epheseCardsStack;
            case "Halicarnasse" -> halicarnasseCardsStack;
            case "Olympie" -> olympieCardsStack;
            case "Rhodes" -> rhodesCardsStack;
            case "Gizeh" -> gizehCardsStack;
            default -> throw new IllegalStateException("Error in UI updateWonderTopCard : Unrecognized wonder (" + wonderName + ")");
        }).setImage(newImage);
    }
    
    public Parent getGameView() {
        return loadingGroup;
    }
}
