package controller.game;

import errorsCenter.DataChecking;
import errorsCenter.ErrorsHandler;
import game.Game;
import game.board.GameConnect;
import java.io.File;
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

public class GameController extends GameConnect {

    @FXML
    Group loadingGroup;
    @FXML
    MediaView loadingAnimationFrame;
    @FXML
    Rectangle whiteForeground;
    @FXML
    Label startingTest;
    MediaPlayer loadingAnimationMedia;
    @FXML
    Group alexandrie, babylon, ephese, halicarnasse, olympie, rhodes, gizeh;
    ArrayList<Group> playedWonders = new ArrayList<>();


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
        Game game = new Game(numberOfHumans, numberOfAI);

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
        ErrorsHandler.handleErrorsInVideo(loadingAnimationMedia,
                "src/main/resources/videos/LoadingAnimation.mp4",
                loadingAnimationFrame);
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

    public void showWonderElements(String wonderName) {

        switch (wonderName) {
            case "Alexandrie" -> {
                alexandrie.setOpacity(1);
                playedWonders.add(alexandrie);
            }
            case "Babylone" -> {
                babylon.setOpacity(1);
                playedWonders.add(babylon);
            }
            case "Éphèse" -> {
                ephese.setOpacity(1);
                playedWonders.add(ephese);
            }
            case "Halicarnasse" -> {
                halicarnasse.setOpacity(1);
                playedWonders.add(halicarnasse);
            }
            case "Olympie" -> {
                olympie.setOpacity(1);
                playedWonders.add(olympie);
            }
            case "Rhodes" -> {
                rhodes.setOpacity(1);
                playedWonders.add(rhodes);
            }
            case "Gizeh" -> {
                gizeh.setOpacity(1);
                playedWonders.add(gizeh);
            }
            default -> throw new IllegalStateException("Error in UI setup : Unrecognized wonder (" + wonderName + ")");
        }
    }


    public Parent getGameView() {
        return loadingGroup;
    }
}
