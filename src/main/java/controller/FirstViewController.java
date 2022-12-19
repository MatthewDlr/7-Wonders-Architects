package controller;

import game.Main;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FirstViewController extends Main implements Initializable {

    @FXML
    private MediaView fxmlIntroVideoFrame, fxmlVideoEffectFrame;

    @FXML
    private ImageView MainTitleFrame, singlePlayerButton, multiPlayerButton, singlePlayerSetupFrame;
    private MediaPlayer introVideo, backgroundVideoMedia, accessDeniedSoundEffect, mainThemeAudio;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File introVideoFile = new File("src/main/resources/videos/7WondersIntro.mp4");
        File titleVideoFile = new File("src/main/resources/videos/MainScreen.mp4");
        File gameTitleImageFile = new File("src/main/resources/images/Setup/MainTitle.jpg");
        File musicThemeFile = new File("src/main/resources/musics/7Wonders - Main Theme.mp3");
        File accessdeniedSoundFile = new File("src/main/resources/musics/AccessDenied.mp3");

        Media introVideoMedia = new Media(introVideoFile.toURI().toString());
        Media titleVideoMedia = new Media(titleVideoFile.toURI().toString());
        Image gameTitleImageMedia = new Image(gameTitleImageFile.toURI().toString());
        Media musicThemeMedia = new Media(musicThemeFile.toURI().toString());
        Media accessdeniedSoundMedia = new Media(accessdeniedSoundFile.toURI().toString());

        introVideo = new MediaPlayer(introVideoMedia);
        mainThemeAudio = new MediaPlayer(musicThemeMedia);
        accessDeniedSoundEffect = new MediaPlayer(accessdeniedSoundMedia);
        accessDeniedSoundEffect.setVolume(300);
        fxmlIntroVideoFrame.setMediaPlayer(introVideo);

        SetButtonScale(singlePlayerButton);
        SetButtonScale(multiPlayerButton);
        mainThemeAudio.setAutoPlay(true);

        introVideo.play();
        mainThemeAudio.play();

        introVideo.setOnEndOfMedia(() -> {
            introVideo.stop();
            MainTitleFrame.setImage(gameTitleImageMedia);

            backgroundVideoMedia = new MediaPlayer(titleVideoMedia);
            fxmlVideoEffectFrame.setMediaPlayer(backgroundVideoMedia);
            backgroundVideoMedia.setAutoPlay(true);
            backgroundVideoMedia.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundVideoMedia.play();
            introVideo.stop();
            fxmlIntroVideoFrame.setVisible(false);

            ShowComponentsWithTransition();

        });
    }

    private void SetButtonScale(ImageView button) {
        button.setScaleX(1.2);
        button.setScaleY(1.2);
    }

    private void ShowComponentsWithTransition() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(1000), MainTitleFrame);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);
        fadeTransition1.setInterpolator(Interpolator.EASE_OUT);
        fadeTransition1.play();
        fadeTransition1.setOnFinished((event -> {
            FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(400), singlePlayerButton);
            fadeTransition2.setFromValue(0);
            fadeTransition2.setToValue(1);
            fadeTransition2.setInterpolator(Interpolator.EASE_OUT);

            FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(400), multiPlayerButton);
            fadeTransition3.setFromValue(0);
            fadeTransition3.setToValue(1);
            fadeTransition3.setInterpolator(Interpolator.EASE_OUT);

            ParallelTransition parallelTransition = new ParallelTransition(fadeTransition2, fadeTransition3);
            parallelTransition.play();
        }));
    }

    @FXML
    public void SinglePlayerButtonClicked() {

        singlePlayerSetupFrame.setVisible(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1250), singlePlayerSetupFrame);
        translateTransition.setFromY(1000);
        translateTransition.setToY(-160);
        translateTransition.setInterpolator(Interpolator.EASE_OUT);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), singlePlayerSetupFrame);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setInterpolator(Interpolator.EASE_OUT);

        ParallelTransition parallelTransition = new ParallelTransition(translateTransition, fadeTransition);
        parallelTransition.setOnFinished(event -> {
            Parent singlePlayerSetup;
            try {
                singlePlayerSetup = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/controller/LocalGameSetup.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = fxmlVideoEffectFrame.getScene();
            scene.setRoot(singlePlayerSetup);
        });
        parallelTransition.play();
    }

    @FXML
    public void MultiPlayerButtonClicked() {
        accessDeniedSoundEffect.stop();
        accessDeniedSoundEffect.seek(Duration.ZERO);
        accessDeniedSoundEffect.play();

        multiPlayerButton.setRotate(0);
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), multiPlayerButton);
        rotateTransition.setByAngle(10);
        rotateTransition.setCycleCount(2);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setInterpolator(Interpolator.EASE_BOTH);
        rotateTransition.play();
    }

    @FXML
    public void MouseIsOverSinglePayerButton() {
        SetButtonScale(singlePlayerButton);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), singlePlayerButton);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.setByX(0.15);
        scaleTransition.setByY(0.15);
        scaleTransition.play();
    }

    @FXML
    public void MouseLeaveSinglePayerButton() {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), singlePlayerButton);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.setByX(-0.15);
        scaleTransition.setByY(-0.15);
        scaleTransition.play();

    }
}