package controller.game;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    MediaView LoadingAnimationFrame;
    @FXML
    Rectangle WhiteForeground;

    MediaPlayer LoadingAnimationMedia;
    public void initialize() {

        File loadingAnimationFile = new File("src/main/resources/videos/LoadingAnimation.mp4");
        Media loadingAnimationMedia = new Media(loadingAnimationFile.toURI().toString());
        LoadingAnimationMedia = new MediaPlayer(loadingAnimationMedia);
        LoadingAnimationFrame.setMediaPlayer(LoadingAnimationMedia);


        ShowLoadingAnimation();
    }

    public void ShowLoadingAnimation() {
        LoadingAnimationMedia.play();
        LoadingAnimationMedia.setAutoPlay(true);
        LoadingAnimationMedia.setCycleCount(MediaPlayer.INDEFINITE);

    }
}
