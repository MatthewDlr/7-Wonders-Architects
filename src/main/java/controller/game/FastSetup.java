package controller.game;

import errorsCenter.DataChecking;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class FastSetup {
    
    public static ImageView setupProgressToken(int tokenNumber, String path, String id) {
        ImageView newToken = new ImageView();
        updateImage(newToken, path);
        newToken.setId(id);
        newToken.setFitHeight(60);
        newToken.setFitWidth(60);
        newToken.setLayoutX(tokenNumber * -75);
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
    
}
