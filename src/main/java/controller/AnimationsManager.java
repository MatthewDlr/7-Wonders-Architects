package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;

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
    
    
}
