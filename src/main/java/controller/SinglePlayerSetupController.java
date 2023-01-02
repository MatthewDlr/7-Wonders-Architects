package controller;

import static java.lang.Math.max;
import static java.lang.Math.min;

import controller.game.GameController;
import errorsCenter.DataChecking;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SinglePlayerSetupController {
    
    @FXML
    Rectangle whiteForeground;
    @FXML
    private ImageView playerCard, iACard, playerCardOnHover, iACardOnHover, playerUpArrow, playerDownArrow, iAUpArrow, iADownArrow, startButtonAvailable, startButtonUnavailable;
    @FXML
    private Label numberOfAiLabel, numberOfPlayerLabel, numberOfWondersLabel;
    private int numberOfHumansPlayer, numberOfIAPlayer;
    private boolean isStartButtonAvailable;
    private MediaPlayer startButtonSoundEffect, gameStartSoundEffect;
    
    public void initialize() {
        showCardsWithAnimation();
        File startButtonSoundEffectFile = new File("src/main/resources/musics/whoosh1.mp3");
        DataChecking.checkIfFileIsCorrect(String.valueOf(startButtonSoundEffectFile));
        Media startButtonSoundEffectMedia = new Media(startButtonSoundEffectFile.toURI().toString());
        startButtonSoundEffect = new MediaPlayer(startButtonSoundEffectMedia);
        
        File gameStartSoundEffectFile = new File("src/main/resources/musics/StartSound.mp3");
        DataChecking.checkIfFileIsCorrect(String.valueOf(gameStartSoundEffectFile));
        Media gameStartSoundEffectMedia = new Media(gameStartSoundEffectFile.toURI().toString());
        gameStartSoundEffect = new MediaPlayer(gameStartSoundEffectMedia);
    }
    
    @FXML
    protected void startButtonClicked() {
        if (isStartButtonAvailable) {
            
            Animation fadeTransition = AnimationsManager.createFadeTransition(whiteForeground, 1000, 0, 1);
            fadeTransition.play();
            gameStartSoundEffect.play();
            fadeTransition.setOnFinished(event -> {
                FirstViewController.stopAllMedia();
                try {
                    switchController();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    
    private void switchController() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/controller/GameView.fxml"))); // @Copilot
        Parent gameView = loader.load();
        GameController gameController = loader.getController(); // @OpenAI
        gameController.initialize(numberOfHumansPlayer, numberOfIAPlayer);
        Scene scene = startButtonAvailable.getScene();
        Stage stage = (Stage) scene.getWindow();
        
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setResizable(true);
        stage.setMaximized(true);
        System.out.println("Screen Width: " + screenBounds.getWidth() + " Screen Height: " + screenBounds.getHeight());
        
        scene.setRoot(gameView);
    }
    
    @FXML
    protected void mouseIsOverStartButton() {
        if (!isStartButtonAvailable) {
            return;
        }
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), startButtonAvailable);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.setByX(0.10);
        scaleTransition.setByY(0.10);
        scaleTransition.play();
    }
    
    @FXML
    protected void mouseLeaveStartButton() {
        if (!isStartButtonAvailable) {
            return;
        }
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), startButtonAvailable);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.setByX(-0.10);
        scaleTransition.setByY(-0.10);
        scaleTransition.play();
        scaleTransition.setOnFinished(event -> {
            startButtonAvailable.setScaleX(1);
            startButtonAvailable.setScaleY(1);
        });
    }
    
    @FXML
    protected void playerUpArrowClicked() {
        arrowClickedAnimation(playerUpArrow, 1, 0.85);
        numberOfHumansPlayer = min(numberOfHumansPlayer + 1, 7);
        updateNumberOfWonders();
        updatePlayerCardBackground();
        arrowClickedAnimation(playerUpArrow, 0.85, 1);
    }
    
    @FXML
    protected void playerDownArrowClicked() {
        arrowClickedAnimation(playerDownArrow, 1, 0.85);
        numberOfHumansPlayer = max(numberOfHumansPlayer - 1, 0);
        updateNumberOfWonders();
        updatePlayerCardBackground();
        arrowClickedAnimation(playerDownArrow, 0.85, 1);
    }
    
    private void updatePlayerCardBackground() {
        if (numberOfHumansPlayer > 0) {
            imageFadeTransition(playerCardOnHover, playerCardOnHover.getOpacity(), 1);
        } else {
            imageFadeTransition(playerCardOnHover, playerCardOnHover.getOpacity(), 0);
        }
    }
    
    @FXML
    protected void iaUpArrowClicked() {
        arrowClickedAnimation(iAUpArrow, 1, 0.85);
        numberOfIAPlayer = min(numberOfIAPlayer + 1, 7);
        updateNumberOfWonders();
        updateIACardBackground();
        arrowClickedAnimation(iAUpArrow, 0.85, 1);
    }
    
    @FXML
    protected void iaDownArrowClicked() {
        arrowClickedAnimation(iADownArrow, 1, 0.85);
        numberOfIAPlayer = max(numberOfIAPlayer - 1, 0);
        updateNumberOfWonders();
        updateIACardBackground();
        arrowClickedAnimation(iADownArrow, 0.85, 1);
    }
    
    private void updateIACardBackground() {
        if (numberOfIAPlayer > 0) {
            imageFadeTransition(iACardOnHover, iACardOnHover.getOpacity(), 1);
        } else {
            imageFadeTransition(iACardOnHover, iACardOnHover.getOpacity(), 0);
        }
    }
    
    public void updateNumberOfWonders() {
        int numberOfWonders = numberOfHumansPlayer + numberOfIAPlayer;
        
        while (numberOfWonders > 7) {
            
            if (numberOfHumansPlayer > numberOfIAPlayer) {
                numberOfHumansPlayer--;
            } else {
                numberOfIAPlayer--;
            }
            numberOfWonders = numberOfHumansPlayer + numberOfIAPlayer;
        }
        numberOfPlayerLabel.setText(Integer.toString(numberOfHumansPlayer));
        numberOfAiLabel.setText(Integer.toString(numberOfIAPlayer));
        numberOfWondersLabel.setText(Integer.toString(numberOfWonders));
        updateStartButton(numberOfWonders);
    }
    
    private void updateStartButton(int numberOfWonders) {
        if (numberOfWonders >= 2) {
            if (!isStartButtonAvailable) {
                startButtonSoundEffect.stop();
                startButtonSoundEffect.seek(Duration.ZERO);
                startButtonSoundEffect.play();
                
                imageFadeTransition(startButtonUnavailable, startButtonUnavailable.getOpacity(), 0);
                imageFadeTransition(startButtonAvailable, startButtonAvailable.getOpacity(), 1);
                numberOfWondersLabel.setTextFill(javafx.scene.paint.Color.web("#ffe503"));
                ((DropShadow) numberOfWondersLabel.getEffect()).setColor(javafx.scene.paint.Color.web("#ffe503"));
            }
            isStartButtonAvailable = true;
            
            
        } else {
            startButtonUnavailable.setOpacity(1);
            numberOfWondersLabel.setTextFill(javafx.scene.paint.Color.web("#36362E"));
            ((DropShadow) numberOfWondersLabel.getEffect()).setColor(javafx.scene.paint.Color.web("#36362E"));
            imageFadeTransition(startButtonAvailable, startButtonAvailable.getOpacity(), 0);
            isStartButtonAvailable = false;
        }
    }
    
    private void imageFadeTransition(ImageView imageView, double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(250), imageView);
        imageView.setVisible(true);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setInterpolator(Interpolator.EASE_BOTH);
        fadeTransition.play();
    }
    
    private void arrowClickedAnimation(ImageView imageView, double fromValue, double toValue) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(250), imageView);
        scaleTransition.setFromX(fromValue);
        scaleTransition.setFromY(fromValue);
        scaleTransition.setToX(toValue);
        scaleTransition.setToY(toValue);
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
        scaleTransition.play();
    }
    
    private void showCardsWithAnimation() {
        
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(250), playerCard);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setInterpolator(Interpolator.EASE_OUT);
        
        playerCard.setRotate(30);
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(300), playerCard);
        rotateTransition.setByAngle(-30);
        rotateTransition.setInterpolator(Interpolator.EASE_BOTH);
        rotateTransition.setAutoReverse(false);
        rotateTransition.setCycleCount(1);
        
        FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(250), iACard);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.setInterpolator(Interpolator.EASE_OUT);
        
        iACard.setRotate(30);
        RotateTransition rotateTransition2 = new RotateTransition(Duration.millis(300), iACard);
        rotateTransition2.setByAngle(-30);
        rotateTransition2.setInterpolator(Interpolator.EASE_BOTH);
        rotateTransition2.setAutoReverse(false);
        rotateTransition2.setCycleCount(1);
        
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition2, rotateTransition2, fadeTransition, rotateTransition);
        parallelTransition.setOnFinished(event -> {
            playerCard.setRotate(0);
            iACard.setRotate(0);
        });
        parallelTransition.play();
    }
    
    
}
