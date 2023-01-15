package controller;

import errorsCenter.DataChecking;
import errorsCenter.ErrorsHandler;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class FirstViewController implements Initializable {
    
    @FXML
    private MediaView fxmlIntroVideoFrame, fxmlVideoEffectFrame;
    
    @FXML
    private ImageView mainTitleFrame, singlePlayerButton, multiPlayerButton, singlePlayerSetupFrame;
    private static MediaPlayer INTRO_VIDEO;
    private static MediaPlayer BACKGROUND_VIDEO_MEDIA;
    private static MediaPlayer ACCESS_DENIED_SOUND_EFFECT;
    private static MediaPlayer MAIN_THEME_AUDIO;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        setupAllComponents();
        
        INTRO_VIDEO.play();
        MAIN_THEME_AUDIO.setVolume(0.5);
        
        INTRO_VIDEO.setOnEndOfMedia(() -> {
            fxmlVideoEffectFrame.setMediaPlayer(BACKGROUND_VIDEO_MEDIA);
            BACKGROUND_VIDEO_MEDIA.play();
            ErrorsHandler.handleErrorsInVideo(BACKGROUND_VIDEO_MEDIA, "src/main/resources/videos/MainScreen.mp4", fxmlVideoEffectFrame);
            INTRO_VIDEO.stop();
            fxmlIntroVideoFrame.setVisible(false);
            showComponentsWithTransition();
            
        });
        ErrorsHandler.handleErrorsInVideo(INTRO_VIDEO, "src/main/resources/videos/7WondersIntro.mp4", fxmlIntroVideoFrame);
    }
    
    private void setupAllComponents() {
        File introVideoFile = new File("src/main/resources/videos/7WondersIntro.mp4");
        File titleVideoFile = new File("src/main/resources/videos/MainScreen.mp4");
        File accessdeniedSoundFile = new File("src/main/resources/musics/AccessDenied.mp3");
        
        DataChecking.checkIfFileIsCorrect(String.valueOf(introVideoFile));
        DataChecking.checkIfFileIsCorrect(String.valueOf(titleVideoFile));
        DataChecking.checkIfFileIsCorrect(String.valueOf(accessdeniedSoundFile));
        
        Media introVideoMedia = new Media(introVideoFile.toURI().toString());
        Media titleVideoMedia = new Media(titleVideoFile.toURI().toString());
        Media accessdeniedSoundMedia = new Media(accessdeniedSoundFile.toURI().toString());
        
        INTRO_VIDEO = new MediaPlayer(introVideoMedia);
        BACKGROUND_VIDEO_MEDIA = new MediaPlayer(titleVideoMedia);
        ACCESS_DENIED_SOUND_EFFECT = new MediaPlayer(accessdeniedSoundMedia);
        
        fxmlIntroVideoFrame.setMediaPlayer(INTRO_VIDEO);
        BACKGROUND_VIDEO_MEDIA.setAutoPlay(true);
        BACKGROUND_VIDEO_MEDIA.setCycleCount(MediaPlayer.INDEFINITE);
        
        MAIN_THEME_AUDIO = SoundEngine.playMainTheme();
        MAIN_THEME_AUDIO.setAutoPlay(true);
        
        setButtonScale(singlePlayerButton);
        setButtonScale(multiPlayerButton);
    }
    
    private void setButtonScale(ImageView button) {
        button.setScaleX(1.3);
        button.setScaleY(1.3);
    }
    
    
    private void showComponentsWithTransition() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(750), mainTitleFrame);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);
        fadeTransition1.setInterpolator(Interpolator.EASE_OUT);
        fadeTransition1.play();
        
        fadeTransition1.setOnFinished(event -> {
            FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(250), singlePlayerButton);
            fadeTransition2.setFromValue(0);
            fadeTransition2.setToValue(1);
            fadeTransition2.setInterpolator(Interpolator.EASE_OUT);
            
            FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(250), multiPlayerButton);
            fadeTransition3.setFromValue(0);
            fadeTransition3.setToValue(1);
            fadeTransition3.setInterpolator(Interpolator.EASE_OUT);
            
            ParallelTransition parallelTransition = new ParallelTransition(fadeTransition2, fadeTransition3);
            parallelTransition.play();
        });
    }
    
    @FXML
    protected void multiPlayerButtonClicked() {
        playAccessDeniedSoundEffect();
        rotateTransition(multiPlayerButton);
    }
    
    private void playAccessDeniedSoundEffect() {
        ACCESS_DENIED_SOUND_EFFECT.play();
        ACCESS_DENIED_SOUND_EFFECT.setOnEndOfMedia(() -> {
            ACCESS_DENIED_SOUND_EFFECT.stop();
            ACCESS_DENIED_SOUND_EFFECT.seek(Duration.ZERO);
        });
    }
    
    private void rotateTransition(ImageView imageView) {
        imageView.setRotate(0);
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), imageView);
        rotateTransition.setByAngle(10);
        rotateTransition.setCycleCount(2);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setInterpolator(Interpolator.EASE_BOTH);
        rotateTransition.play();
    }
    
    @FXML
    protected void mouseIsOverSinglePayerButton() {
        setButtonScale(singlePlayerButton);
        
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), singlePlayerButton);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.setByX(0.15);
        scaleTransition.setByY(0.15);
        scaleTransition.play();
    }
    
    @FXML
    protected void mouseLeaveSinglePayerButton() {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), singlePlayerButton);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.setByX(-0.15);
        scaleTransition.setByY(-0.15);
        scaleTransition.play();
    }
    
    @FXML
    public void singlePlayerButtonClicked() {
        
        singlePlayerButton.setDisable(true);
        multiPlayerButton.setDisable(true);
        singlePlayerSetupFrame.setVisible(true);
        
        parallelTransitionWithTranslateAndFade(singlePlayerSetupFrame);
    }
    
    private void parallelTransitionWithTranslateAndFade(ImageView imageView) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), imageView);
        translateTransition.setFromY(1000);
        translateTransition.setToY(-160);
        translateTransition.setInterpolator(Interpolator.EASE_OUT);
        
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), imageView);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setInterpolator(Interpolator.EASE_OUT);
        
        ParallelTransition parallelTransition = new ParallelTransition(translateTransition, fadeTransition);
        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {
            try {
                switchToGameConfigScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    
    private void switchToGameConfigScene() throws IOException {
        Parent singlePlayerSetup = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/controller/LocalGameSetup.fxml")));
        Scene scene = fxmlVideoEffectFrame.getScene();
        scene.setRoot(singlePlayerSetup);
    }
    
    public static void stopAllMedia() {
        INTRO_VIDEO.stop();
        BACKGROUND_VIDEO_MEDIA.stop();
        MAIN_THEME_AUDIO.stop();
        ACCESS_DENIED_SOUND_EFFECT.stop();
    }
    
}