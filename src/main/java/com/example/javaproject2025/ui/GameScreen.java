package com.example.javaproject2025.ui;

import com.example.javaproject2025.game.Bit;
import com.example.javaproject2025.game.GamePhysics;
import com.example.javaproject2025.game.Track;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;

import static com.example.javaproject2025.ui.MainScreen.createMenuLabel;
import static com.example.javaproject2025.ui.MainScreen.createNeonGlow;

public class GameScreen  {
    // main root, holds all elements of the game.
    public Pane root = new Pane();
    public Bit bit1 = new Bit("Bit1", 600*4/9, 600-5, Color.RED);
    public Bit bit2 = new Bit("Bit2", 600*5/9, 600-5, Color.BLUE);
    public Scene scene;
    public String currentTurn = "";
    public boolean started = false;
    public int bit1Score;
    public int bit2Score;
    public Text scoreBit1 = new Text("BIT 1 SCORE : " + Integer.toString(bit1Score));
    public Text scoreBit2 = new Text("BIT 2 SCORE : " + Integer.toString(bit2Score));
    private final Glow glowEffect = new Glow(1.5);
    public Font gameFont = Font.loadFont(getClass().getResourceAsStream("/Minecraft.ttf"), 22);
    private Text playerOneScore;
    private Text playerTwoScore;
    public Label mainMenuButton = createMenuLabel("MAIN MENU", Color.WHITE);
    public Line finishLine = new Line();
    public String userOneUsername;
    public String userTwoUsername;
    public String trackName;

    public GameScreen(Stage primaryStage, String userOne, String userTwo, String trackName) {
        this.userOneUsername = userOne;
        this.userTwoUsername = userTwo;
        this.trackName = trackName;
        System.out.println("HELLO TRACK WHAT ARE WE " + trackName);
        root.setStyle("-fx-background-color: black;");
        mainMenuButton.setFont(Font.font("Orbitron", 16));
        mainMenuButton.setTextFill(Color.LIGHTGRAY);
        mainMenuButton.setEffect(createNeonGlow(Color.LIGHTGRAY));
        mainMenuButton.setTranslateX(480);
        mainMenuButton.setTranslateY(10);
        Image gifImage = new Image(getClass().getResource("/images/pulsating_star.gif").toExternalForm());
        Image gifImage2 = new Image(getClass().getResource("/images/pulsating_star.gif").toExternalForm());
        ImageView gifView2 = new ImageView(gifImage);
        gifView2.setX(490);
        gifView2.setY(290);
        gifView2.setFitHeight(80);
        gifView2.setFitWidth(40);
        gifView2.setPreserveRatio(true);
        Image gameAssets = new Image(getClass().getResource("/images/game_assets.png").toExternalForm());
        ImageView gameAssetsImageView = new ImageView(gameAssets);
        gameAssetsImageView.setFitHeight(600);
        gameAssetsImageView.setFitWidth(600);
        gameAssetsImageView.setPreserveRatio(true);
        randomizeTurn();
        scoreBit1.setFont(Font.font("Orbitron", 18));
        scoreBit1.setEffect(glowEffect);
        scoreBit1.setFill(Color.RED);
        scoreBit1.setX(20);
        scoreBit1.setY(530);
        scoreBit2.setFont(Font.font("Orbitron", 18));
        scoreBit2.setEffect(glowEffect);
        scoreBit2.setFill(Color.BLUE);
        scoreBit2.setX(410);
        scoreBit2.setY(530);
        //scoreBit2.setOpacity(0);
        ImageView gifView = new ImageView(gifImage);
        gifView.setX(90);
        gifView.setY(190);
        gifView.setFitHeight(80);
        gifView.setFitWidth(40);
        gifView.setPreserveRatio(true);

        // main pane for the game (root pane)
        final double sceneWidth = 600;
        final double sceneHeight = 600;
        finishLine.setStartX(330);
        finishLine.setStartY(90);
        finishLine.setEndX(400);
        finishLine.setEndY(90);
        finishLine.setFill(Color.RED);
        finishLine.setStroke(Color.RED);
        // Instantiate and render level 1's track
        Track track1 = new Track("Level 1");
        track1.buildLevel1Layout(sceneWidth, sceneHeight); // A method to define and add 4 boundary lines
        track1.render(root);

        // Create bits

        // Add bits and directionals to the scene
        root.getChildren().add(gameAssetsImageView);
        root.getChildren().addAll(bit1.getShape(), bit2.getShape(),
                bit1.getDirectionLine(), bit2.getDirectionLine());

        //root.getChildren().add(finishLine);
        root.getChildren().addAll(gifView, gifView2);
        root.getChildren().add(mainMenuButton);

        // button which handles removal of all GameScreen Objects
        mainMenuButton.setOnMouseClicked(event -> {
            root.getChildren().removeAll(bit1.getShape(), bit2.getShape(), bit1.getDirectionLine(), bit2.getDirectionLine(),
                    finishLine, mainMenuButton, gameAssetsImageView, scoreBit1, scoreBit2);
            MainScreen newMenuAfterClicked = new MainScreen(primaryStage, userOneUsername, userTwoUsername);
            primaryStage.setScene(newMenuAfterClicked.getScene());
        });

        root.getChildren().add(scoreBit1);
        root.getChildren().add(scoreBit2);
//        if(currentTurn.equals("bit1")){
//            root.getChildren().add(scoreBit1);
//        }
//        else{
//            root.getChildren().add(scoreBit2);
//        }

        started = true;

        // Add more elements later: bits, arrows, etc.
        scene = new Scene(root, sceneWidth, sceneHeight, Color.BLACK);
        scene.setOnKeyPressed(event -> {
            if(currentTurn.equals("bit1")) {
                switch (event.getCode()) {
                    case A -> bit1.rotate(-5);  // Bit1 Rotate left
                    case D -> bit1.rotate(5);   // Bit 1 Rotate right
                    case S -> {
                        if(started){
                            started = false;
                        }
                        bit1Score += 1;
                        bit1.launch(10);
                        bit1.moved = true;
                        scoreBit1.setText("BIT 1 SCORE : " + Integer.toString(bit1Score));
                    }
                }
            }
            else{
                switch (event.getCode()) {
                    case LEFT -> bit2.rotate(-5); //Bit 2 Rotate left
                    case RIGHT -> bit2.rotate(5); // Bit 2 Rotate right
                    case UP -> {
                        if(started){
                            started = false;
                        }
                        bit2Score += 1;
                        bit1.moved = false;
                        bit2.launch(10);
                        bit2.moved = true;
                        scoreBit2.setText("BIT 2 SCORE: " + Integer.toString(bit2Score));
                    }
                    case R -> {
                        bit2.getShape().setCenterX(sceneWidth * 5 / 9);
                        bit2.getShape().setCenterY(sceneHeight - 5);
                        bit2.launched = false;
                    }
                    case B -> {
                        track1.render(root);
                        root.getChildren().add(finishLine);
                    }

                }
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(checkWinner() == "bit1"){
                    System.out.println("bit 1 wins!");
                    root.getChildren().removeAll(bit1.getShape(), bit2.getShape(), bit1.getDirectionLine(), bit2.getDirectionLine(),
                            finishLine, mainMenuButton, gameAssetsImageView, scoreBit1, scoreBit2);
                    this.stop();
                    WinnerScreen winnerScreen = null;
                    try {
                        winnerScreen = new WinnerScreen(primaryStage, "bit1", userOneUsername, bit1Score, trackName, userOneUsername, userTwoUsername);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    primaryStage.setScene(winnerScreen.getScene());
                    winnerScreen.sendScore("BIT 1", bit1Score);
                }

                else if(checkWinner() == "bit2" ){
                    System.out.println("bit 2 wins!");
                    root.getChildren().removeAll(bit1.getShape(), bit2.getShape(), bit1.getDirectionLine(), bit2.getDirectionLine(),
                            finishLine, mainMenuButton, gameAssetsImageView, scoreBit1, scoreBit2);
                    this.stop();
                    WinnerScreen winnerScreen = null;
                    try {
                        winnerScreen = new WinnerScreen(primaryStage, "bit2", userTwoUsername, bit2Score, trackName, userOneUsername, userTwoUsername);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    primaryStage.setScene(winnerScreen.getScene());
                    winnerScreen.sendScore("BIT 2", bit2Score);

                }
                if(bit1.moved){
                    currentTurn = "bit2";
                }
                else if(bit2.moved){
                    currentTurn = "bit1";
                }

                GamePhysics gamePhysics = new GamePhysics();
                if(bit2.getY() <= finishLine.getStartY()) {
                    for (int i = 0; i < track1.getBoundaries().size(); i++) {
                        root.getChildren().remove(track1.getBoundaries().get(i));
                    }
                    root.getChildren().remove(finishLine);
                }

                bit1.moveIfLaunched();
                bit2.moveIfLaunched();
                for (int i = 0; i < track1.getBoundaries().size(); i++) {
                    Line boundary = track1.getBoundaries().get(i);

                    if (gamePhysics.isCircleCollidingWithLine(bit2.getX(), bit2.getY(), bit2.getShape().getRadius(),
                            boundary.getStartX(), boundary.getStartY(),
                            boundary.getEndX(), boundary.getEndY())) {
                        bit2.launched = false;
                    }
                }
                for (int i = 0; i < track1.getBoundaries().size(); i++) {
                    Line boundary = track1.getBoundaries().get(i);
                    if (gamePhysics.isCircleCollidingWithLine(bit1.getX(), bit1.getY(), bit1.getShape().getRadius(),
                            boundary.getStartX(), boundary.getStartY(),
                            boundary.getEndX(), boundary.getEndY())) {
                        bit1.launched = false;
                    }
                }
            }
        };
        timer.start();
    }

    public Scene getScene() {
        return scene;
    }


    public void randomizeTurn(){
        int randomNumber = (int) (Math.random() * 100);

        if(randomNumber > 50)
            currentTurn = "bit1";
        else
            currentTurn = "bit2";
    }

    public String checkWinner(){

        String winner = "";

        if(bit1.getY() <= finishLine.getStartY()){
            winner = "bit1";
        }
        else if(bit2.getY() <= finishLine.getStartY()){
            winner = "bit2";
        }

        return winner;
    }


}