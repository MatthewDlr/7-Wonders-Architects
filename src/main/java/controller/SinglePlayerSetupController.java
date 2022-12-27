package controller;

import controller.game.GameController;
import javafx.animation.*;
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
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import static java.lang.Math.max;
import static java.lang.Math.min;
import controller.game.GameController;


public class SinglePlayerSetupController {

    @FXML
    private ImageView playerCard, iACard, playerCardOnHover, iACardOnHover, playerUpArrow, playerDownArrow, iAUpArrow, iADownArrow, StartButtonAvailable, StartButtonUnavailable;

    @FXML
    private Label numberOfAiLabel, numberOfPlayerLabel, numberOfWondersLabel;
    @FXML
    Rectangle WhiteForeground;

    private int numberOfHumansPlayer, numberOfIAPlayer;
    private boolean isStartButtonAvailable = false;
    private MediaPlayer startButtonSoundEffect, gameStartSoundEffect;

    public void initialize() {
        ShowCardsWithAnimation();
        File startButtonSoundEffectFile = new File("src/main/resources/musics/whoosh1.mp3");
        ErrorsHandler.CheckIfFileIsCorrect(String.valueOf(startButtonSoundEffectFile));
        Media startButtonSoundEffectMedia = new Media(startButtonSoundEffectFile.toURI().toString());
        startButtonSoundEffect = new MediaPlayer(startButtonSoundEffectMedia);

        File gameStartSoundEffectFile = new File("src/main/resources/musics/StartSound.mp3");
        ErrorsHandler.CheckIfFileIsCorrect(String.valueOf(gameStartSoundEffectFile));
        Media gameStartSoundEffectMedia = new Media(gameStartSoundEffectFile.toURI().toString());
        gameStartSoundEffect = new MediaPlayer(gameStartSoundEffectMedia);
    }

    @FXML
    protected void StartButtonClicked() {
        if (isStartButtonAvailable) {

            FirstViewController.StopAllMedia();
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1500), WhiteForeground);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setInterpolator(Interpolator.EASE_BOTH);

            gameStartSoundEffect.stop();
            gameStartSoundEffect.seek(Duration.ZERO);
            gameStartSoundEffect.play();

            fadeTransition.play();
            fadeTransition.setOnFinished(event -> {
                Stage stage = (Stage) StartButtonAvailable.getScene().getWindow();
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();
                stage.setWidth(screenBounds.getWidth());
                stage.setHeight(screenBounds.getHeight());
                System.out.println("Width: " + screenBounds.getWidth() + " Height: " + screenBounds.getHeight());
                stage.setMaximized(true);
                stage.setResizable(true);

                try {
                    SwitchController();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void SwitchController() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/controller/GameView.fxml"))); // @Copilot
        Parent GameView = loader.load();
        Scene scene = StartButtonAvailable.getScene();
        scene.setRoot(GameView);
        GameController gameController = loader.getController(); // @OpenAI
        gameController.Initialize();
        gameController.SetNumberOfPlayers(numberOfHumansPlayer, numberOfIAPlayer);
    }

    @FXML
    protected void MouseIsOverStartButton() {
        if (!isStartButtonAvailable){
            return;
        }
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), StartButtonAvailable);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.setByX(0.10);
        scaleTransition.setByY(0.10);
        scaleTransition.play();
    }

    @FXML
    protected void MouseLeaveStartButton() {
        if (!isStartButtonAvailable){
            return;
        }
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), StartButtonAvailable);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.setByX(-0.10);
        scaleTransition.setByY(-0.10);
        scaleTransition.play();
        scaleTransition.setOnFinished(event -> {
            StartButtonAvailable.setScaleX(1);
            StartButtonAvailable.setScaleY(1);
        });
    }

    @FXML
    protected void PlayerUpArrowClicked() {
        ArrowClickedAnimation(playerUpArrow, 1, 0.85);
        numberOfHumansPlayer = min(numberOfHumansPlayer + 1, 7);
        UpdateNumberOfWonders();
        UpdatePlayerCardBackground();
        ArrowClickedAnimation(playerUpArrow, 0.85, 1);
    }

    @FXML
    protected void PlayerDownArrowClicked() {
        ArrowClickedAnimation(playerDownArrow, 1, 0.85);
        numberOfHumansPlayer = max(numberOfHumansPlayer - 1, 0);
        UpdateNumberOfWonders();
        UpdatePlayerCardBackground();
        ArrowClickedAnimation(playerDownArrow, 0.85, 1);
    }

    private void UpdatePlayerCardBackground() {
        if (numberOfHumansPlayer > 0) {
            ImageFadeTransition(playerCardOnHover, playerCardOnHover.getOpacity(), 1);
        } else {
            ImageFadeTransition(playerCardOnHover, playerCardOnHover.getOpacity(), 0);
        }
    }

    @FXML
    protected void IAUpArrowClicked() {
        ArrowClickedAnimation(iAUpArrow, 1, 0.85);
        numberOfIAPlayer = min(numberOfIAPlayer + 1, 7);
        UpdateNumberOfWonders();
        UpdateIACardBackground();
        ArrowClickedAnimation(iAUpArrow, 0.85, 1);
    }

    @FXML
    protected void IADownArrowClicked() {
        ArrowClickedAnimation(iADownArrow, 1, 0.85);
        numberOfIAPlayer = max(numberOfIAPlayer - 1, 0);
        UpdateNumberOfWonders();
        UpdateIACardBackground();
        ArrowClickedAnimation(iADownArrow, 0.85, 1);
    }

    private void UpdateIACardBackground() {
        if (numberOfIAPlayer > 0) {
            ImageFadeTransition(iACardOnHover, iACardOnHover.getOpacity(), 1);
        } else {
            ImageFadeTransition(iACardOnHover, iACardOnHover.getOpacity(), 0);
        }
    }

    public void UpdateNumberOfWonders() {
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
        UpdateStartButton(numberOfWonders);
    }

    private void UpdateStartButton(int numberOfWonders) {
        if (numberOfWonders >= 2) {
            if (!isStartButtonAvailable) {
                startButtonSoundEffect.stop();
                startButtonSoundEffect.seek(Duration.ZERO);
                startButtonSoundEffect.play();

                ImageFadeTransition(StartButtonUnavailable, StartButtonUnavailable.getOpacity(), 0);
                ImageFadeTransition(StartButtonAvailable, StartButtonAvailable.getOpacity(), 1);
                numberOfWondersLabel.setTextFill(javafx.scene.paint.Color.web("#ffe503"));
                ((DropShadow) numberOfWondersLabel.getEffect()).setColor(javafx.scene.paint.Color.web("#ffe503"));
            }
            isStartButtonAvailable = true;


        } else {
            StartButtonUnavailable.setOpacity(1);
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
        fadeTransition.setInterpolator(Interpolator.EASE_BOTH);
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
