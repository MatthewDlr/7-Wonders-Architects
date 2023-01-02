package controller.game;

import controller.AnimationsManager;
import errorsCenter.DataChecking;
import errorsCenter.ErrorsHandler;
import game.Game;
import game.board.gameUIBridge;
import game.player.Player;
import game.tokens.progress.ProgressToken;
import java.io.File;
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

public class GameController extends gameUIBridge {
    
    @FXML
    Group loadingGroup;
    @FXML
    MediaView loadingAnimationFrame;
    @FXML
    Rectangle whiteForeground;
    @FXML
    Label startingText;
    @FXML
    Group alexandrie, babylon, ephese, halicarnasse, olympie, rhodes, gizeh, progressTokensBoard, warTokensBoard;
    @FXML
    ImageView alexandrieCardsStack, babylonCardsStack, epheseCardsStack, halicarnasseCardsStack, olympieCardsStack, rhodesCardsStack, gizehCardsStack, gameCardsStack, progressTokenDeck;
    @FXML
    ImageView alexandrieProgressTokens1, babylonProgressTokens1, epheseProgressTokens1, halicarnasseProgressTokens1, olympieProgressTokens1, rhodesProgressTokens1, gizehProgressTokens1, background;
    @FXML
    AnchorPane pane;
    
    MediaPlayer loadingAnimationMedia;
    ArrayList<Group> playedWonders = new ArrayList<>();
    private Game game;
    private Player currentPlayer;
    
    public void initialize(int numberOfHumans, int numberOfAI) {
        
        File loadingAnimationFile = new File("src/main/resources/videos/LoadingAnimation.mp4");
        DataChecking.checkIfFileIsCorrect(String.valueOf(loadingAnimationFile));
        Media loadingAnimationMedia = new Media(loadingAnimationFile.toURI().toString());
        this.loadingAnimationMedia = new MediaPlayer(loadingAnimationMedia);
        loadingAnimationFrame.setMediaPlayer(this.loadingAnimationMedia);
        showLoadingAnimation();
        
        System.out.println("Number of Humans : " + numberOfHumans);
        System.out.println("Number of AI : " + numberOfAI);
        
        int time1 = (int) System.nanoTime(); //@Copilot
        setGameController(this);
        game = new Game(numberOfHumans, numberOfAI);
        game.launchGame();
        
        System.out.println("Game Started");
        int time2 = (int) System.nanoTime();
        System.out.println("Loading Time : " + (time2 - time1) / 1000000 + " ms \n"); // @Copliot
    }
    
    private void showLoadingAnimation() {
        loadingGroup.setVisible(true);
        loadingAnimationMedia.setAutoPlay(true);
        loadingAnimationMedia.setRate(1.2);
        loadingAnimationMedia.setCycleCount(1);
        loadingAnimationMedia.setOnEndOfMedia(() -> {
            hideLoadingAnimation();
        });
        ErrorsHandler.handleErrorsInVideo(loadingAnimationMedia, "src/main/resources/videos/LoadingAnimation.mp4", loadingAnimationFrame);
        loadingAnimationMedia.play();
    }
    
    private void hideLoadingAnimation() {
        FadeTransition whiteForegroundTransition = AnimationsManager.createFadeTransition(whiteForeground, 550, 1, 0);
        FadeTransition startingTestTransition = AnimationsManager.createFadeTransition(startingText, 500, 1, 0);
        FadeTransition loadingAnimationFrameTransition = AnimationsManager.createFadeTransition(loadingAnimationFrame, 300, 1, 0);
        
        ParallelTransition parallelTransition = new ParallelTransition(whiteForegroundTransition, startingTestTransition, loadingAnimationFrameTransition);
        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {
            loadingGroup.setVisible(false);
        });
    }
    
    public void associatePlayersWithWonders(Iterable<Player> listOfPlayers) {
        for (Player player : listOfPlayers) {
            player.setWonderGroup(switch (player.getWonderName()) {
                case "Alexandrie" -> alexandrie;
                case "Babylone" -> babylon;
                case "Ephese" -> ephese;
                case "Halicarnasse" -> halicarnasse;
                case "Olympie" -> olympie;
                case "Rhodes" -> rhodes;
                case "Gizeh" -> gizeh;
                default -> throw new IllegalStateException(" Unknown wonder name: " + player.getWonderName());
            });
        }
    }
    
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        PlayerActions.setCurrentPlayer(currentPlayer); // to be removed
    }
    
    public void setPlaceOfWonders(String wonderName, int x, int y, int rotation) {
        
        setupWonderGroup(switch (wonderName) {
            case "Alexandrie" -> alexandrie;
            case "Babylone" -> babylon;
            case "Ephese" -> ephese;
            case "Halicarnasse" -> halicarnasse;
            case "Olympie" -> olympie;
            case "Rhodes" -> rhodes;
            case "Gizeh" -> gizeh;
            default -> throw new IllegalStateException("Error in UI setup : Unrecognized wonder (" + wonderName + ")");
        }, x, y, rotation); // Switch simplified by @IntelliJ
    }
    
    private void setupWonderGroup(Group wonderGroup, int x, int y, int rotation) {
        wonderGroup.setOpacity(1);
        playedWonders.add(wonderGroup);
        wonderGroup.setLayoutX(x);
        wonderGroup.setLayoutY(y);
        wonderGroup.setRotate(rotation);
    }
    
    public void updateWonderTopCard(String cardPath, String wonderName) {
        
        ImageView imageToUpdate = switch (wonderName) {
            case "Alexandrie" -> alexandrieCardsStack;
            case "Babylone" -> babylonCardsStack;
            case "Ephese" -> epheseCardsStack;
            case "Halicarnasse" -> halicarnasseCardsStack;
            case "Olympie" -> olympieCardsStack;
            case "Rhodes" -> rhodesCardsStack;
            case "Gizeh" -> gizehCardsStack;
            default -> throw new IllegalStateException("Error in UI updateWonderTopCard : Unrecognized wonder (" + wonderName + ")");
        };
        FastSetup.updateImage(imageToUpdate, cardPath);
    }
    
    
    public void setupProgressToken(ProgressToken progressToken, int tokenNumber) { // Helped by @Copilot
        ImageView token = FastSetup.setupProgressToken(tokenNumber, "src/main/resources/game/progressTokens/" + progressToken.getName() + ".png", "progressToken" + progressToken.getName());
        token.setOnMouseClicked(event -> {
            System.out.println("\nClicked on " + token.getId());
            PlayerActions.getProgressToken(progressToken, token, progressTokensBoard);
            addNewProgressToken();
        });
        progressTokensBoard.getChildren().add(token);
    }
    
    public void setupConflictToken(int tokenNumber) { // Helped by @Copilot
        
        ImageView token = FastSetup.setupProgressToken(tokenNumber, "src/main/resources/game/tokens/ConflictTokenPeaceFace.png", "conflictToken" + tokenNumber);
        token.setOnMouseClicked(event -> {
            System.out.println("This token has no action");
        });
        warTokensBoard.getChildren().add(token);
    }
    
    public void addNewProgressToken(){
        int i = 0;
        for (Node node : progressTokensBoard.getChildren()) {
            node.setLayoutX(i * -75);
            i++;
        }
        ProgressToken progressToken = getProgressToken();
        setupProgressToken(progressToken, i);
    }
    
    
}
