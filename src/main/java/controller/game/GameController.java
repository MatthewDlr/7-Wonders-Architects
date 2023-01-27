package controller.game;

import controller.AnimationsEngine;
import game.Game;
import game.board.PlayerQueue;
import game.board.gameUIBridge;
import game.cards.GameCardsStack;
import game.player.AIPlayer;
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
import javafx.scene.layout.Pane;
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
    ImageView referenceResourceCard, referenceShieldCard, referenceScienceCard, referenceProgressToken, referenceWarToken, referenceCat, catCard;
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
    private ArrayList<ImageView> listOfProgressTokensUI = new ArrayList<>();
    ArrayList<ImageView> listOfConflictToken = new ArrayList<>();
    ArrayList<ProgressToken> listOfProgressTokensInGame = new ArrayList<>();
    
    public void initialize(int numberOfHumans, int numberOfAI) {
        
        loadingAnimationMedia = FastSetup.setupVideoPlayer("src/main/resources/videos/LoadingAnimation.mp4");
        loadingAnimationFrame.setMediaPlayer(loadingAnimationMedia);
        AnimationsEngine.showGameBoardLoadingAnimation(loadingGroup, loadingAnimationMedia, loadingAnimationFrame, whiteForeground, startingText);
        
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
        if (currentPlayer instanceof AIPlayer) {
            return;
        }
        gameCardsStackReference.setDisable(false);
        AnimationsEngine.enableDropShadow(gameCardsStackReference);
        Player player = getRightPlayer();
        ImageView cardsStackRight = (ImageView) player.getAnchorPane().getChildren().get(1);
        cardsStackRight.setDisable(false);
        AnimationsEngine.enableDropShadow(cardsStackRight);
        
        Player player2 = getLeftPlayer();
        ImageView cardsStackLeft = (ImageView) player2.getAnchorPane().getChildren().get(1);
        cardsStackLeft.setDisable(false);
        AnimationsEngine.enableDropShadow(cardsStackLeft);
    }
    
    
    public void setupProgressToken(ProgressToken progressToken, int tokenNumber) { // Helped by @Copilot
        ImageView token = FastSetup.setupProgressTokenUI(tokenNumber, "src/main/resources/game/progressTokens/" + progressToken.getName() + ".png","progressToken" + progressToken.getName());
        token.setDisable(true);
        pane.getChildren().add(pane.getChildren().indexOf(loadingGroup) - 1, token); // @Copilot
        listOfProgressTokensUI.add(token);
        listOfProgressTokensInGame.add(progressToken);
        token.setOnMouseClicked(event -> {
            System.out.println("\nClicked on " + token.getId());
            PlayerActions.getProgressToken(progressToken, token, pane);
        });
        PlayerActions.setListOfProgressTokens(listOfProgressTokensUI);
    }
    
    public void setupConflictToken(int tokenNumber) { // Helped by @Copilot
        ImageView token = FastSetup.setupConflictToken("conflictToken" + tokenNumber, tokenNumber);
        pane.getChildren().add(pane.getChildren().indexOf(loadingGroup) - 1, token); // @Copilot
        listOfConflictToken.add(token);
        token.setOnMouseClicked(event -> {
            System.out.println("This token has no action");
        });
    }
    
    public void linkPlayersWithUIComponents() {
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
            AnimationsEngine.disableDropShadow(cardsStack);
        }
        System.out.println("Clicked on GameCardsStack");
        PlayerActions.getNewCardFromGameCardsStack(gameCardsStack, gameCardsStackReference, pane);
        if (gameCardsStack.isEmpty()) {
            gameCardsStackReference.setVisible(false);
        }
    }
    
    @FXML
    public void mainCardsStackActionOnHover(){
        if (!currentPlayer.hasTheCat()){
            return;
        }
        String cardPath = gameCardsStack.getTopCardPath();
        FastSetup.updateImage(catCard, cardPath);
        
        catCard.setVisible(true);
        Animation animation1 = AnimationsEngine.createRotateTransition(catCard, 500, 0, 15);
        Animation animation2 = AnimationsEngine.createTranslateTransitionTo(catCard, 500, 35, -35);
        ParallelTransition parallelTransition = new ParallelTransition(animation1, animation2);
        parallelTransition.play();
    }
    
    @FXML
    public void mainCardsStackActionOnExit(){
        Animation animation1 = AnimationsEngine.createRotateTransition(catCard, 500, 15, 0);
        Animation animation2 = AnimationsEngine.createTranslateTransitionTo(catCard, 500, -35, 35);
        ParallelTransition parallelTransition = new ParallelTransition(animation1, animation2);
        parallelTransition.setOnFinished(event -> catCard.setVisible(false));
        parallelTransition.play();
    }
    
    @FXML
    public void alexandrieCardsStackAction() {
        cardsStackAction(alexandrie);
    }
    
    @FXML
    public void babyloneCardsStackAction() {
        cardsStackAction(babylone);
    }
    
    @FXML
    public void epheseCardsStackAction() {
        cardsStackAction(ephese);
    }
    
    @FXML
    public void halicarnasseCardsStackAction() {
        cardsStackAction(halicarnasse);
    }
    
    @FXML
    public void olympieCardsStackAction() {
        cardsStackAction(olympie);
    }
    
    @FXML
    public void rhodesCardsStackAction() {
        cardsStackAction(rhodes);
    }
    
    @FXML
    public void gizehCardsStackAction() {
        cardsStackAction(gizeh);
    }
    
    @FXML
    public void cardsStackAction(AnchorPane wonder) {
        for (ImageView cardsStack : listOfCardsStacks) {
            cardsStack.setDisable(true);
            AnimationsEngine.disableDropShadow(cardsStack);
        }
        
        String id = wonder.getId();
        Player clickedPlayer = null;
        for (Player player : listOfPlayers) {
            System.out.println(player.getWonderName());
            if (player.getWonderName().equalsIgnoreCase(id)) {
                clickedPlayer = player;
                break;
            }
        }
        if (clickedPlayer == null) {
            System.out.println("Error in UI cardsStackAction : Unrecognized player id: " + id);
        }
        
        System.out.println("Clicked on " + id + "CardsStack");
        PlayerActions.getNewCardsFromWonderCardsStack(clickedPlayer, (ImageView) wonder.getChildren().get(1), pane);
    }
    
    public void newProgressToken() {
        ProgressToken progressToken = tokensBoard.popProgressToken();
        ImageView token = FastSetup.setupProgressTokenUI(tokensBoard.getNumberOfProgressToken(),"src/main/resources/game/progressTokens/" + progressToken.getName() + ".png","progressToken" + progressToken.getName());
        token.setDisable(true);
        listOfProgressTokensUI.add(token);
        listOfProgressTokensInGame.add(progressToken);
        pane.getChildren().add(token);
        FastSetup.updateProgressTokenPosition(listOfProgressTokensUI);
        token.setOnMouseClicked(event -> {
            System.out.println("\nClicked on " + token.getId());
            PlayerActions.getProgressToken(progressToken, token, pane);
        });
    }
    
    
    public void deleteProgressToken(ImageView token, ProgressToken progressToken) {
        listOfProgressTokensUI.remove(token);
        listOfProgressTokensInGame.remove(progressToken);
    }
    
    
    public void updateConflictTokenImage(int index, String path) {
        Animation rotateAnimation = AnimationsEngine.createRotateTransition(listOfConflictToken.get(index), 300, 0, 360);
        Animation mockAnimation = AnimationsEngine.createFadeTransition(listOfConflictToken.get(index), 150, 100, 100);
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
        if (currentPlayer instanceof AIPlayer) {
            int randomIndex = (int) (Math.random() * 3);
            ImageView token = listOfProgressTokensUI.get(randomIndex);
            ProgressToken progressToken = listOfProgressTokensInGame.get(randomIndex);
            PlayerActions.getProgressToken(progressToken, token, pane);
        } else {
            gameCardsStackReference.setDisable(true);
            AnimationsEngine.disableDropShadow(gameCardsStackReference);
            
            for (ImageView token : listOfProgressTokensUI) {
                token.setDisable(false);
                AnimationsEngine.enableDropShadow(token);
            }
        }
    }
    
    public void getCatUI() {
        PlayerActions.getCatUI(pane);
    }
    
    public void nextPlayer() {
        game.getGameBoard().nextPlayerTurn();
    }
    
    public void addWarTokenUIToPlayer(Player player) {
        ImageView warToken = FastSetup.createNewWarToken();
        double toX = player.getAnchorPane().getLayoutX() + 300 + player.getCoordinatesForNextWarToken() ;
        double toY = player.getAnchorPane().getLayoutY() + 100;
        AnimationsEngine.createTranslateTransitionTo(warToken, 1500, toX, toY).play();
    }
    
    public void takeMainStackCard() {
        mainCardsStackAction();
    }
    
    public void takeRightStackCard(Player rightPlayer) {
        cardsStackAction(rightPlayer.getAnchorPane());
    }
    
    public void takeLeftStackCard(Player leftPlayer) {
        cardsStackAction(leftPlayer.getAnchorPane());
    }
    
    public Pane getPane() {
        return pane;
    }
}
