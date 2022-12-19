package controller;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class SinglePlayerSetupController {

    @FXML
    private ImageView playerCard, iACard, playerCardOnHover, iACardOnHover, playerUpArrow, playerDownArrow, iAUpArrow, iADownArrow, StartButtonAvailable;

    @FXML
    private Label numberOfAiLabel, numberOfPlayerLabel, numberOfWondersLabel;

    private int numberOfPlayers, numberOfIA;
    private boolean isStartButtonAvailable = false;
    private MediaPlayer startButtonSoundEffect;

    public void initialize() {
        ShowCardsWithAnimation();
        File startButtonSoundEffectFile = new File("src/main/resources/musics/whoosh1.mp3");
        Media startButtonSoundEffectMedia = new Media(startButtonSoundEffectFile.toURI().toString());
        startButtonSoundEffect = new MediaPlayer(startButtonSoundEffectMedia);
    }

    @FXML
    protected void PlayerUpArrowClicked() {
        ArrowClickedAnimation(playerUpArrow, 1, 0.85);
        numberOfPlayers = min(numberOfPlayers + 1, 7);
        UpdateNumberOfWonders();
        UpdatePlayerCardBackground();
        ArrowClickedAnimation(playerUpArrow, 0.85, 1);
    }

    @FXML
    protected void PlayerDownArrowClicked() {
        ArrowClickedAnimation(playerDownArrow, 1, 0.85);
        numberOfPlayers = max(numberOfPlayers - 1, 0);
        UpdateNumberOfWonders();
        UpdatePlayerCardBackground();
        ArrowClickedAnimation(playerDownArrow, 0.85, 1);
    }

    private void UpdatePlayerCardBackground() {
        if (numberOfPlayers > 0) {
            ImageFadeTransition(playerCardOnHover, playerCardOnHover.getOpacity(), 1);
        } else {
            ImageFadeTransition(playerCardOnHover, playerCardOnHover.getOpacity(), 0);
        }
    }

    @FXML
    protected void IAUpArrowClicked() {
        ArrowClickedAnimation(iAUpArrow, 1, 0.85);
        numberOfIA = min(numberOfIA + 1, 7);
        UpdateNumberOfWonders();
        UpdateIACardBackground();
        ArrowClickedAnimation(iAUpArrow, 0.85, 1);
    }

    @FXML
    protected void IADownArrowClicked() {
        ArrowClickedAnimation(iADownArrow, 1, 0.85);
        numberOfIA = max(numberOfIA - 1, 0);
        UpdateNumberOfWonders();
        UpdateIACardBackground();
        ArrowClickedAnimation(iADownArrow, 0.85, 1);
    }

    private void UpdateIACardBackground() {
        if (numberOfIA > 0) {
            ImageFadeTransition(iACardOnHover, iACardOnHover.getOpacity(), 1);
        } else {
            ImageFadeTransition(iACardOnHover, iACardOnHover.getOpacity(), 0);
        }
    }

    private void UpdateNumberOfWonders() {
        int numberOfWonders = numberOfPlayers + numberOfIA;

        while (numberOfWonders > 7) {

            if (numberOfPlayers > numberOfIA) {
                numberOfPlayers--;
            } else {
                numberOfIA--;
            }
            numberOfWonders = numberOfPlayers + numberOfIA;
        }
        numberOfPlayerLabel.setText(Integer.toString(numberOfPlayers));
        numberOfAiLabel.setText(Integer.toString(numberOfIA));
        numberOfWondersLabel.setText(Integer.toString(numberOfWonders));
        UpdateStartButton(numberOfWonders);
    }

    private void UpdateStartButton(int numberOfWonders) {
        if (numberOfWonders >= 2) {
            if (!isStartButtonAvailable) {
                startButtonSoundEffect.stop();
                startButtonSoundEffect.seek(Duration.ZERO);
                startButtonSoundEffect.play();
                ImageFadeTransition(StartButtonAvailable, StartButtonAvailable.getOpacity(), 1);
                numberOfWondersLabel.setTextFill(javafx.scene.paint.Color.web("#ffe503"));
                ((DropShadow) numberOfWondersLabel.getEffect()).setColor(javafx.scene.paint.Color.web("#ffe503"));
            }
            isStartButtonAvailable = true;

        } else {
            numberOfWondersLabel.setTextFill(javafx.scene.paint.Color.web("#36362E"));
            ((DropShadow) numberOfWondersLabel.getEffect()).setColor(javafx.scene.paint.Color.web("#36362E"));
            ImageFadeTransition(StartButtonAvailable, StartButtonAvailable.getOpacity(), 0);
            isStartButtonAvailable = false;
        }
    }

    private void ImageFadeTransition(ImageView imageView, double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(250), imageView);
        imageView.setVisible(true);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setInterpolator(Interpolator.EASE_OUT);
        fadeTransition.play();
    }

    private void ArrowClickedAnimation(ImageView imageView, double fromValue, double toValue) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(250), imageView);
        scaleTransition.setFromX(fromValue);
        scaleTransition.setFromY(fromValue);
        scaleTransition.setToX(toValue);
        scaleTransition.setToY(toValue);
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
        scaleTransition.play();
    }

    private void ShowCardsWithAnimation() {

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
