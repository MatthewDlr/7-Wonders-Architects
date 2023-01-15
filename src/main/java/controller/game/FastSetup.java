package controller.game;

import errorsCenter.DataChecking;
import game.cards.Card;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class FastSetup {
    
    private static AnchorPane pane;
    
    public static void setupGameBoard(AnchorPane pane) {
        FastSetup.pane = pane;
    }
    
    static ImageView setupProgressTokenUI(int tokenNumber, String path, String id) {
        ImageView newToken = new ImageView();
        updateImage(newToken, path);
        newToken.setId(id);
        newToken.setFitHeight(60);
        newToken.setFitWidth(60);
        newToken.setLayoutX(775 - tokenNumber * 75);
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
        newToken.setLayoutX(775 - tokenNumber * 75);
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
    
    public static ImageView createNewCardUI(Card card, int id) {
        ImageView newCard = new ImageView();
        updateImage(newCard, card.getCardPath());
        newCard.setId(card.getCardName() + id);
        newCard.setFitHeight(150);
        newCard.setFitWidth(100);
        newCard.setLayoutX(850);
        newCard.setLayoutY(400);
        return newCard;
    }
    
    public static void updateProgressTokenPosition(Iterable<ImageView> progressTokens) {
        int i = 1;
        for (ImageView token : progressTokens) {
            token.setLayoutX(775 - i * 75);
            i++;
        }
    }
    
    public static ImageView createNewWarToken() {
        ImageView newWarToken = new ImageView();
        updateImage(newWarToken, "src/main/resources/game/tokens/WarToken.png");
        newWarToken.setFitHeight(45);
        newWarToken.setFitWidth(45);
        newWarToken.setLayoutX(0);
        newWarToken.setLayoutY(0);
        pane.getChildren().add(3, newWarToken);
        return newWarToken;
    }
    
    public static ImageView createMockImage() {
        ImageView mockImage = new ImageView();
        mockImage.setFitHeight(1);
        mockImage.setFitWidth(1);
        mockImage.setLayoutX(0);
        mockImage.setLayoutY(0);
        return mockImage;
    }
    
}
