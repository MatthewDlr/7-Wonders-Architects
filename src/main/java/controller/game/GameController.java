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
import javafx.scene.input.MouseEvent;
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
    AnchorPane alexandrie, babylone, ephese, halicarnasse, olympie, rhodes, gizeh;
    @FXML
    ImageView alexandrieCardsStack, babyloneCardsStack, epheseCardsStack, halicarnasseCardsStack, olympieCardsStack, rhodesCardsStack, gizehCardsStack, gameCardsStackReference, progressTokenDeck;
    @FXML
    ImageView referenceResourceCard, referenceShieldCard, referenceScienceCard, referenceProgressToken, referenceWarToken, referenceCat;
    @FXML
    AnchorPane pane;
    
    protected static final int[] referenceCardsPositionX = {275, 335, 395, 455, 290, 290, 0};
    protected static final int[] referenceCardsPositionY = {300, 300, 300, 300, 150, 100, 100};
    private ArrayList<ImageView> listOfCardsStacks = new ArrayList<>();
    
    MediaPlayer loadingAnimationMedia;
    ArrayList<AnchorPane> playedWonders = new ArrayList<>();
    private Game game;
    private Player currentPlayer;
    private ArrayList<Player> listOfPlayers;
    private TokensBoard tokensBoard;
    private PlayerQueue playerQueue;
    private GameCardsStack gameCardsStack;
    private ArrayList<ImageView> listOfProgressTokens = new ArrayList<>();
    ArrayList<ImageView> listOfConflictToken = new ArrayList<>();
    
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
        setupCardsStack();
    }
    
    private void setupCardsStack() {
        gameCardsStackReference.setDisable(false);
        AnimationsManager.enableDropShadow(gameCardsStackReference);
        Player player = getRightPlayer();
        ImageView cardsStackRight = (ImageView) player.getAnchorPane().getChildren().get(1);
        cardsStackRight.setDisable(false);
        AnimationsManager.enableDropShadow(cardsStackRight);
        
        Player player2 = getLeftPlayer();
        ImageView cardsStackLeft = (ImageView) player2.getAnchorPane().getChildren().get(1);
        cardsStackLeft.setDisable(false);
        AnimationsManager.enableDropShadow(cardsStackLeft);
    }
    
    
    public void setupProgressToken(ProgressToken progressToken, int tokenNumber) { // Helped by @Copilot
        ImageView token = FastSetup.setupProgressTokenUI(tokenNumber, "src/main/resources/game/progressTokens/" + progressToken.getName() + ".png","progressToken" + progressToken.getName());
        token.setDisable(true);
        pane.getChildren().add(pane.getChildren().indexOf(loadingGroup) - 1, token); // @Copilot
        listOfProgressTokens.add(token);
        token.setOnMouseClicked(event -> {
            System.out.println("\nClicked on " + token.getId());
            PlayerActions.getProgressToken(progressToken, token, pane);
        });
        PlayerActions.setListOfProgressTokens(listOfProgressTokens);
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
            AnchorPane anchorPane;
            ImageView cardsStack;
            switch (player.getWonderName()) {
                case "Alexandrie" -> {
                    anchorPane = alexandrie;
                    cardsStack = alexandrieCardsStack;
                }
                case "Babylone" -> {
                    anchorPane = babylone;
                    cardsStack = babyloneCardsStack;
                }
                case "Ephese" -> {
                    anchorPane = ephese;
                    cardsStack = epheseCardsStack;
                }
                case "Halicarnasse" -> {
                    anchorPane = halicarnasse;
                    cardsStack = halicarnasseCardsStack;
                }
                case "Olympie" -> {
                    anchorPane = olympie;
                    cardsStack = olympieCardsStack;
                }
                case "Rhodes" -> {
                    anchorPane = rhodes;
                    cardsStack = rhodesCardsStack;
                }
                case "Gizeh" -> {
                    anchorPane = gizeh;
                    cardsStack = gizehCardsStack;
                }
                default -> throw new IllegalStateException("Unexpected value: " + player.getWonderName());
            }
            listOfCardsStacks.add(cardsStack);
            player.setWonderGroup(anchorPane);
        }
        listOfCardsStacks.add(gameCardsStackReference);
    }
    
    public void setPlaceOfWonders(String wonderName, int x, int y, int rotation) {
        FastSetup.setupWonderPane(switch (wonderName) {
            case "Alexandrie" -> alexandrie;
            case "Babylone" -> babylone;
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
            case "Babylone" -> babyloneCardsStack;
            case "Ephese" -> epheseCardsStack;
            case "Halicarnasse" -> halicarnasseCardsStack;
            case "Olympie" -> olympieCardsStack;
            case "Rhodes" -> rhodesCardsStack;
            case "Gizeh" -> gizehCardsStack;
            default -> throw new IllegalStateException("Error in UI updateWonderTopCard : Unrecognized wonder (" + wonderName + ")");
        };
        FastSetup.updateImage(imageToUpdate, cardPath);
    }
    
    @FXML
    public void mainCardsStackAction() {
        for (ImageView cardsStack : listOfCardsStacks) {
            cardsStack.setDisable(true);
            AnimationsManager.disableDropShadow(cardsStack);
        }
        System.out.println("Clicked on GameCardsStack");
        PlayerActions.getNewCardFromGameCardsStack(gameCardsStack, gameCardsStackReference, pane);
        if (gameCardsStack.isEmpty()) {
            gameCardsStackReference.setVisible(false);
        }
    }
    
    @FXML
    public void cardsStackAction(MouseEvent event) {
        for (ImageView cardsStack : listOfCardsStacks) {
            cardsStack.setDisable(true);
            AnimationsManager.disableDropShadow(cardsStack);
        }
        String id = ((ImageView) event.getSource()).getId();
        id = id.replace("CardsStack", "");
        id = id.replaceFirst("^[a-z]", id.substring(0, 1).toUpperCase());
        Player clickedPlayer = null;
        for (Player player : listOfPlayers) {
            System.out.println(player.getWonderName());
            if (player.getWonderName().equalsIgnoreCase(id)) {
                clickedPlayer = player;
                break;
            }
        }
        if (clickedPlayer == null) {
            throw new IllegalStateException("Error in UI cardsStackAction : Unrecognized player id: " + id );
        }
        System.out.println("Clicked on " + id + "CardsStack");
        PlayerActions.getNewCardsFromWonderCardsStack(clickedPlayer, (ImageView) event.getSource(), pane);
    }
    
    public void newProgressToken() {
        ProgressToken progressToken = tokensBoard.popProgressToken();
        ImageView token = FastSetup.setupProgressTokenUI(tokensBoard.getNumberOfProgressToken(),"src/main/resources/game/progressTokens/" + progressToken.getName() + ".png","progressToken" + progressToken.getName());
        token.setDisable(true);
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
            case "Babylone" -> babylone;
            case "Ephese" -> ephese;
            case "Halicarnasse" -> halicarnasse;
            case "Olympie" -> olympie;
            case "Rhodes" -> rhodes;
            case "Gizeh" -> gizeh;
            default -> throw new IllegalStateException("Error in UI setup : Unrecognized wonder (" + wonderName + ")");
        };
        ImageView wonderFloor = (ImageView) ((Group) wonderPane.getChildren().get(0)).getChildren().get(floorNumber - 1);
        FastSetup.updateImage(wonderFloor, "src/main/resources/game/wondersFloors/" + wonderName + "/Floor" + floorNumber + "Built.png");
        nextPlayer();
    }
    
    public void allowUserToTakeAProgressToken() {
        gameCardsStackReference.setDisable(true);
        AnimationsManager.disableDropShadow(gameCardsStackReference);
        
        for (ImageView token : listOfProgressTokens) {
            token.setDisable(false);
            AnimationsManager.enableDropShadow(token);
        }
    }
    
    public void getCatUI() {
        PlayerActions.getCatUI(pane);
    }
    
    public void nextPlayer() {
        game.getGameBoard().nextPlayer();
    }
}
