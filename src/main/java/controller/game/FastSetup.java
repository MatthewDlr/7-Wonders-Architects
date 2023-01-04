package controller.game;

import errorsCenter.DataChecking;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class FastSetup {
    
    static ImageView setupProgressToken(int tokenNumber, String path, String id) {
        ImageView newToken = new ImageView();
        updateImage(newToken, path);
        newToken.setId(id);
        newToken.setFitHeight(60);
        newToken.setFitWidth(60);
        newToken.setLayoutX(775 - (tokenNumber * 75));
        newToken.setLayoutY(400);
        newToken.setOpacity(1);
        
        return newToken;
    }
    
    static ImageView setupConflictToken(String id, int tokenNumber) {
        ImageView newToken = new ImageView();
        updateImage(newToken, "src/main/resources/game/tokens/ConflictTokenPeaceFace.png");
        newToken.setId(id);
        newToken.setFitHeight(60);
        newToken.setFitWidth(60);
        newToken.setLayoutX(775 - (tokenNumber * 75));
        newToken.setLayoutY(490);
        newToken.setOpacity(1);
        
        return newToken;
    }
    
    static void updateImage(ImageView imageToUpdate, String cardPath) {
        System.out.println("Updating image of " + imageToUpdate.getId() + " : " + cardPath);
        DataChecking.checkIfFileIsCorrect(cardPath);
        
        File newFile = new File(cardPath);
        Image newImage = new Image(newFile.toURI().toString());
        imageToUpdate.setImage(newImage);
    }
    
    static void setupWonderPane(AnchorPane wonderGroup, int x, int y, int rotation) {
        wonderGroup.setOpacity(1);
        wonderGroup.setLayoutX(x);
        wonderGroup.setLayoutY(y);
        wonderGroup.setRotate(rotation);
    }
    
    public static MediaPlayer setupVideoPlayer(String videoPath) {
        File loadingAnimationFile = new File(videoPath);
        DataChecking.checkIfFileIsCorrect(String.valueOf(loadingAnimationFile));
        Media loadingAnimationMedia = new Media(loadingAnimationFile.toURI().toString());
        return new MediaPlayer(loadingAnimationMedia);
    }
    
}
