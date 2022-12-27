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
    private int numberOfHumans , numberOfAI ;

    MediaPlayer LoadingAnimationMedia;
    public void Initialize() {
        this.numberOfHumans = this.numberOfAI = 0;
        File loadingAnimationFile = new File("src/main/resources/videos/LoadingAnimation.mp4");
        ErrorsHandler.CheckIfFileIsCorrect(String.valueOf(loadingAnimationFile));
        Media loadingAnimationMedia = new Media(loadingAnimationFile.toURI().toString());
        LoadingAnimationMedia = new MediaPlayer(loadingAnimationMedia);
        LoadingAnimationFrame.setMediaPlayer(LoadingAnimationMedia);

        ShowLoadingAnimation();
    }

    private void SetupGame() {
        System.out.println("Number of Humans : " + numberOfHumans);
        System.out.println("Number of AI : " + numberOfAI);
        Game game = new Game(numberOfHumans, numberOfAI);
        System.out.println("Game Started");
    }

    public void ShowLoadingAnimation() {
        LoadingAnimationMedia.setAutoPlay(true);
        LoadingAnimationMedia.setCycleCount(1);
        LoadingAnimationMedia.setOnEndOfMedia(() -> SetupGame());
        LoadingAnimationMedia.play();
        ErrorsHandler.CheckForMediaErrors(LoadingAnimationMedia);

    }

    public void SetNumberOfPlayers(int humans, int aI) {
        numberOfHumans = humans;
        numberOfAI = aI;
    }
}
