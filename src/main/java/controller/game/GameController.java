package controller.game;

import controller.ErrorsHandler;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class GameController {

    @FXML
    MediaView LoadingAnimationFrame;
    @FXML
    Rectangle WhiteForeground;
    @FXML
    Label StartingTest;
    MediaPlayer LoadingAnimationMedia;

    public void Initialize(int numberOfHumans, int numberOfAI) {
        File loadingAnimationFile = new File("src/main/resources/videos/LoadingAnimation.mp4");
        ErrorsHandler.CheckIfFileIsCorrect(String.valueOf(loadingAnimationFile));
        Media loadingAnimationMedia = new Media(loadingAnimationFile.toURI().toString());
        LoadingAnimationMedia = new MediaPlayer(loadingAnimationMedia);
        LoadingAnimationFrame.setMediaPlayer(LoadingAnimationMedia);

        ShowLoadingAnimation();
        System.out.println("Number of Humans : " + numberOfHumans);
        System.out.println("Number of AI : " + numberOfAI);

        int time1 = (int) System.nanoTime();
        Game game = new Game(numberOfHumans, numberOfAI);
        System.out.println("Game Started");
        int time2 = (int) System.nanoTime();
        System.out.println("Elapsed time : " + (time2 - time1)/1000000 + " ms");
    }


    public void ShowLoadingAnimation() {
        LoadingAnimationMedia.setAutoPlay(true);
        LoadingAnimationMedia.setCycleCount(1);
        LoadingAnimationMedia.play();
        ErrorsHandler.CheckForMediaErrors(LoadingAnimationMedia);

    }

}
