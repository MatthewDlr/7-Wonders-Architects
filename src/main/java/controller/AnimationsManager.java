package controller;

import errorsCenter.ErrorsHandler;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

public abstract class AnimationsManager {
    
    public static TranslateTransition createTranslateTransition(Node movingNode, double duration, double fromX, double fromY, double toX, double toY) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(movingNode);
        translateTransition.setDuration(javafx.util.Duration.millis(duration));
        translateTransition.setByX(toX - fromX);
        translateTransition.setByY(toY - fromY);
        translateTransition.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);
        
        return translateTransition;
    }
    
    public static FadeTransition createFadeTransition(Node fadingNode, double duration, double fromValue, double toValue) {
        
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(fadingNode);
        fadeTransition.setDuration(javafx.util.Duration.millis(duration));
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);
        
        return fadeTransition;
    }
    
    public static ScaleTransition createScaleTransition(Node scalingNode, double duration, double toX, double toY) {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(scalingNode);
        scaleTransition.setDuration(javafx.util.Duration.millis(duration));
        scaleTransition.setFromX(scalingNode.getScaleX());
        scaleTransition.setFromY(scalingNode.getScaleY());
        scaleTransition.setToX(toX);
        scaleTransition.setToY(toY);
        scaleTransition.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);
        
        return scaleTransition;
    }
    
    public static void showGameBoardLoadingAnimation(Group loadingGroup, MediaPlayer loadingAnimationMedia, MediaView loadingAnimationFrame, Rectangle whiteForeground, Label startingText) {
        loadingGroup.setVisible(true);
        loadingAnimationMedia.setAutoPlay(true);
        loadingAnimationMedia.setRate(1.2);
        loadingAnimationMedia.setCycleCount(1);
        loadingAnimationMedia.setOnEndOfMedia(() -> {
            hideGameBoardLoadingAnimation(loadingGroup, whiteForeground, startingText,loadingAnimationFrame);
        });
        ErrorsHandler.handleErrorsInVideo(loadingAnimationMedia, "src/main/resources/videos/LoadingAnimation.mp4", loadingAnimationFrame);
        loadingAnimationMedia.play();
    }
    
    public static void hideGameBoardLoadingAnimation(Group loadingGroup,Rectangle whiteForeground, Label startingText, MediaView loadingAnimationFrame){
        FadeTransition whiteForegroundTransition = createFadeTransition(whiteForeground, 550, 1, 0);
        FadeTransition startingTestTransition = createFadeTransition(startingText, 500, 1, 0);
        FadeTransition loadingAnimationFrameTransition = createFadeTransition(loadingAnimationFrame, 300, 1, 0);
        
        ParallelTransition parallelTransition = new ParallelTransition(whiteForegroundTransition, startingTestTransition, loadingAnimationFrameTransition);
        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {
            loadingGroup.setVisible(false);
        });
    }
    
    public static void enableDropShadow(Node node){
        node.setEffect(new javafx.scene.effect.DropShadow());
        
        ((DropShadow) node.getEffect()).setColor(javafx.scene.paint.Color.web("#ffe503"));
        ((DropShadow) node.getEffect()).setBlurType(BlurType.GAUSSIAN);
        ((DropShadow) node.getEffect()).setRadius(50);
        ((DropShadow) node.getEffect()).setSpread(0.5);
        ((DropShadow) node.getEffect()).setWidth(150);
        ((DropShadow) node.getEffect()).setHeight(150);
    }
    
    public static void disableDropShadow(Node node){
        node.setEffect(null);
    }
    
    public static Animation createRotateTransition(ImageView newCard, int duration, int i1, double i2) {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(newCard);
        rotateTransition.setDuration(javafx.util.Duration.millis(duration));
        rotateTransition.setFromAngle(i1);
        rotateTransition.setToAngle(i2);
        rotateTransition.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);
        return rotateTransition;
    }
}