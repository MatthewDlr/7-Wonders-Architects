package controller.game;

import controller.AnimationsManager;
import game.Game;
import game.board.PlayerQueue;
import game.board.gameUIBridge;
import game.cards.GameCardsStack;
import game.player.Player;
import game.tokens.TokensBoard;
import game.tokens.progress.ProgressToken;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    AnchorPane alexandrie, babylon, ephese, halicarnasse, olympie, rhodes, gizeh;
    @FXML
    ImageView alexandrieCardsStack, babylonCardsStack, epheseCardsStack, halicarnasseCardsStack, olympieCardsStack, rhodesCardsStack, gizehCardsStack, gameCardsStackReference, progressTokenDeck;
    @FXML
    ImageView referenceResourceCard, referenceShieldCard, referenceScienceCard, referenceProgressToken, referenceWarToken, referenceCat;
    @FXML
    AnchorPane pane;
    
    protected static final int[] referenceCardsPositionX = {275, 335, 395, 455, 290, 290, 0};
    protected static final int[] referenceCardsPositionY = {300, 300, 300, 300, 150, 100, 100};
    
    MediaPlayer loadingAnimationMedia;
    ArrayList<AnchorPane> playedWonders = new ArrayList<>();
    private Game game;
    private Player currentPlayer;
    private ArrayList<Player> listOfPlayers;
    private TokensBoard tokensBoard;
    private PlayerQueue playerQueue;
    private GameCardsStack gameCardsStack;
    private ArrayList<ImageView> listOfProgressTokens = new ArrayList<>();
    private ArrayList<ImageView> listOfConflictToken = new ArrayList<>();
    
    public void initialize(int numberOfHumans, int numberOfAI) {
        
        loadingAnimationMedia = FastSetup.setupVideoPlayer("src/main/resources/videos/LoadingAnimation.mp4");
        loadingAnimationFrame.setMediaPlayer(loadingAnimationMedia);
        AnimationsManager.showGameBoardLoadingAnimation(loadingGroup, loadingAnimationMedia, loadingAnimationFrame, whiteForeground, startingText);
        
        System.out.println("Number of Humans : " + numberOfHumans);
        System.out.println("Number of AI : " + numberOfAI);
        int time1 = (int) System.nanoTime(); //@Copilot
        
        game = new Game(numberOfHumans, numberOfAI);
        setGameController(this);
        tokensBoard = getTokensBoard();
        playerQueue = getPlayerQueue();
        listOfPlayers = getListOfPlayers();
        gameCardsStack = getGameCardsStack();
        game.launchGame();
        
        System.out.println("Game Started");
        int time2 = (int) System.nanoTime();
        System.out.println("Loading Time : " + (time2 - time1) / 1000000 + " ms \n"); // @Copliot
    }
    
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        PlayerActions.setCurrentPlayer(currentPlayer); // to be removed
    }
    
    
    public void setupProgressToken(ProgressToken progressToken, int tokenNumber) { // Helped by @Copilot
        ImageView token = FastSetup.setupProgressTokenUI(tokenNumber, "src/main/resources/game/progressTokens/" + progressToken.getName() + ".png","progressToken" + progressToken.getName());
        //insert the token after the loadingAnimationFrame
        pane.getChildren().add(pane.getChildren().indexOf(loadingGroup) - 1, token); // @Copilot
        listOfProgressTokens.add(token);
        token.setOnMouseClicked(event -> {
            System.out.println("\nClicked on " + token.getId());
            PlayerActions.getProgressToken(progressToken, token, pane);
            //addNewProgressToken();
        });
    }
    
    public void setupConflictToken(int tokenNumber) { // Helped by @Copilot
        ImageView token = FastSetup.setupConflictToken("conflictToken" + tokenNumber, tokenNumber);
        pane.getChildren().add(pane.getChildren().indexOf(loadingGroup) - 1, token); // @Copilot
        listOfConflictToken.add(token);
        token.setOnMouseClicked(event -> {
            System.out.println("This token has no action");
        });
    }
    
    public void linkPlayersWithUIComponents(){
        PlayerActions.setGameController(this);
        FastSetup.setupGameBoard(pane);
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
    
    public void setPlaceOfWonders(String wonderName, int x, int y, int rotation) {
        FastSetup.setupWonderPane(switch (wonderName) {
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
    
    public void setGameCardsStack() {
        gameCardsStackReference.setOnMouseClicked(event -> {
            System.out.println("Clicked on GameCardsStack");
            AnimationsManager.disableDropShadow(gameCardsStackReference);
            PlayerActions.getNewCard(gameCardsStack, gameCardsStackReference, pane);
            if (gameCardsStack.isEmpty()) {
                gameCardsStackReference.setVisible(false);
            }
        });
    }
    
    public void newProgressToken() {
        ProgressToken progressToken = tokensBoard.popProgressToken();
        ImageView token = FastSetup.setupProgressTokenUI(tokensBoard.getNumberOfProgressToken(),"src/main/resources/game/progressTokens/" + progressToken.getName() + ".png","progressToken" + progressToken.getName());
        listOfProgressTokens.add(token);
        pane.getChildren().add(token);
        FastSetup.updateProgressTokenPosition(listOfProgressTokens);
        token.setOnMouseClicked(event -> {
            System.out.println("\nClicked on " + token.getId());
            PlayerActions.getProgressToken(progressToken, token, pane);
        });
    }
    
    public void deleteProgressToken(ImageView token) {
        listOfProgressTokens.remove(token);
    }
    
    
    public void updateConflictTokenImage(int index, String path) {
        Animation rotateAnimation = AnimationsManager.createRotateTransition(listOfConflictToken.get(index), 300, 0, 360);
        Animation mockAnimation = AnimationsManager.createFadeTransition(listOfConflictToken.get(index), 150, 100, 100);
        mockAnimation.setOnFinished(event -> {
            FastSetup.updateImage(listOfConflictToken.get(index), path);
        });
        ParallelTransition parallelTransition = new ParallelTransition(rotateAnimation, mockAnimation);
        parallelTransition.play();
    }
    
    public void setFloorUIasBuilt(int floorNumber, String wonderName) {
        //get anchor pane that match the wonder name
        AnchorPane wonderPane = switch (wonderName) {
            case "Alexandrie" -> alexandrie;
            case "Babylone" -> babylon;
            case "Ephese" -> ephese;
            case "Halicarnasse" -> halicarnasse;
            case "Olympie" -> olympie;
            case "Rhodes" -> rhodes;
            case "Gizeh" -> gizeh;
            default -> throw new IllegalStateException("Error in UI setup : Unrecognized wonder (" + wonderName + ")");
        };
        ImageView wonderFloor = (ImageView) ((Group) wonderPane.getChildren().get(0)).getChildren().get(floorNumber - 1);
        FastSetup.updateImage(wonderFloor, "src/main/resources/game/wondersFloors/" + wonderName + "/Floor" + floorNumber + "Built.png");
    }

}
