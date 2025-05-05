package com.example.javaproject2025.ui;

import com.example.javaproject2025.game.Bit;
import com.example.javaproject2025.game.GamePhysics;
import com.example.javaproject2025.game.Track;
import com.example.javaproject2025.utils.ScreenUtils;
import javafx.animation.AnimationTimer;
import static com.example.javaproject2025.ui.LoginScreen.createText;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.example.javaproject2025.ui.MainScreen.createMenuLabel;
import static com.example.javaproject2025.ui.MainScreen.createNeonGlow;

public class GameScreen  {
    // main root, holds all elements of the game.
    public Pane root = new Pane();
    public Bit bit1 = new Bit("Bit1", 600.0*4/9, 600-5, Color.RED);
    public Bit bit2 = new Bit("Bit2", 600.0*5/9, 600-5, Color.BLUE);
    public Scene scene;
    public String currentTurn = "";
    public int bitOneScore;
    public int bitTwoScore;
    public Label mainMenuButton = createMenuLabel("MAIN MENU", Color.WHITE);
    public Line finishLine = new Line();
    public String userOneUsername;
    public String userTwoUsername;
    public final double sceneWidth = 600;
    public final double sceneHeight = 600;

    public GameScreen(Stage primaryStage, String userOne, String userTwo, String trackName) {
        this.userOneUsername = userOne; this.userTwoUsername = userTwo;
        boolean bitOneTurnTriggered = false;
        System.out.println("HELLO TRACK WHAT ARE WE " + trackName);
        root.setStyle("-fx-background-color: black;");
        root.setPrefSize(sceneWidth, sceneHeight);
        ScreenUtils.drawStars(root);
        mainMenuButton.setFont(Font.font("Orbitron", 16));
        mainMenuButton.setTextFill(Color.LIGHTGRAY);
        mainMenuButton.setEffect(createNeonGlow(Color.LIGHTGRAY));
        mainMenuButton.setTranslateX(480);
        mainMenuButton.setTranslateY(10);
        randomizeTurn();

        Text bitOneScoreDisplay = createText(userOne + "\n    0", 18, Color.RED, 60, 530);
        Text bitTwoScoreDisplay = createText(userTwo + "\n    0", 18, Color.BLUE, 465, 530);


        // Render tracks dynamically
        Track track = new Track(trackName);
        switch (trackName.toLowerCase()) {
            case "track1" -> {
                track.setFinishLine(finishLine, "track1");
                track.buildLevel1Layout(sceneWidth, sceneHeight);
            }
            case "track2" -> {
                track.setFinishLine(finishLine, "track2");
                track.buildLevel2Layout(sceneWidth, sceneHeight);
            }
            case "track3" -> {
                track.setFinishLine(finishLine, "track3");
                track.buildLevel3Layout(sceneWidth, sceneHeight);
            }
            default -> track.buildLevel1Layout(sceneWidth, sceneHeight); // fallback
        }
        // render track
        track.render(root);

        // Create bits and add bits and directionals to the scene
        root.getChildren().addAll(bit1.getShape(), bit2.getShape(), bit1.getDirectionLine(), bit2.getDirectionLine(), mainMenuButton, finishLine, bitOneScoreDisplay, bitTwoScoreDisplay);

        // button which handles removal of all GameScreen Objects
        mainMenuButton.setOnMouseClicked(event -> {
            MainScreen newMenuAfterClicked = new MainScreen(primaryStage);
            primaryStage.setScene(newMenuAfterClicked.getScene());
        });

        // Add more elements later: bits, arrows, etc.
        scene = new Scene(root, sceneWidth, sceneHeight);
        scene.setOnKeyPressed(event -> {
            if(currentTurn.equals("bit1")) {
                if(!bitOneTurnTriggered){
                }
                switch (event.getCode()) {
                    case A -> bit1.rotate(-5);  // Bit1 Rotate left
                    case D -> bit1.rotate(5);   // Bit 1 Rotate right
                    case S -> {
                        bitOneScore += 1;
                        bit1.launch(10);
                        bit1.moved = true;
                        bitOneScoreDisplay.setText(userOne + "\n    " + bitOneScore);
                    }
                }
            }
            else{
                System.out.println("bit 2 turn");
                switch (event.getCode()) {
                    case LEFT -> bit2.rotate(-5); //Bit 2 Rotate left
                    case RIGHT -> bit2.rotate(5); // Bit 2 Rotate right
                    case UP -> {
                        bitTwoScore += 1;
                        bit1.moved = false;
                        bit2.launch(10);
                        bit2.moved = true;
                        bitTwoScoreDisplay.setText(userTwo + "\n    " + bitTwoScore);
                    }
                    case R -> {
                        bit2.getShape().setCenterX(sceneWidth * 5 / 9);
                        bit2.getShape().setCenterY(sceneHeight - 5);
                        bit2.launched = false;
                    }
                }
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(checkWinner().equals("bit1")){
                    System.out.println("bit 1 wins!");
                    root.getChildren().removeAll(bit1.getShape(), bit2.getShape(), bit1.getDirectionLine(), bit2.getDirectionLine(),
                            finishLine, mainMenuButton);
                    this.stop();
                    WinnerScreen winnerScreen;
                    try {
                        winnerScreen = new WinnerScreen(primaryStage, "bit1", userOneUsername, bitOneScore, trackName, userOneUsername, userTwoUsername);
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    primaryStage.setScene(winnerScreen.getScene());
                    winnerScreen.sendScore("BIT 1", bitOneScore);
                }

                else if(checkWinner().equals("bit2")){
                    System.out.println("bit 2 wins!");
                    root.getChildren().removeAll(bit1.getShape(), bit2.getShape(), bit1.getDirectionLine(), bit2.getDirectionLine(),
                            finishLine, mainMenuButton);
                    this.stop();
                    WinnerScreen winnerScreen;
                    try {
                        winnerScreen = new WinnerScreen(primaryStage, "bit2", userTwoUsername, bitTwoScore, trackName, userOneUsername, userTwoUsername);
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    primaryStage.setScene(winnerScreen.getScene());
                    winnerScreen.sendScore("BIT 2", bitTwoScore);

                }
                if(bit1.moved){
                    currentTurn = "bit2";
                }
                else if(bit2.moved){
                    currentTurn = "bit1";
                }

                GamePhysics gamePhysics = new GamePhysics();

//                if(bit2.getY() <= finishLine.getStartY()) {
//                    for (int i = 0; i < track.getBoundaries().size(); i++) {
//                        root.getChildren().remove(track.getBoundaries().get(i));
//                    }
//                    root.getChildren().remove(finishLine);
//                }

                bit1.moveIfLaunched();
                bit2.moveIfLaunched();

                // collision detection for both bits
                for (int i = 0; i < track.getBoundaries().size(); i++) {
                    Line boundary = track.getBoundaries().get(i);

                    if (gamePhysics.isCircleCollidingWithLine(bit2.getX(), bit2.getY(), bit2.getShape().getRadius(),
                            boundary.getStartX(), boundary.getStartY(),
                            boundary.getEndX(), boundary.getEndY())) {
                        bit2.launched = false;
                    }
                }
                for (int i = 0; i < track.getBoundaries().size(); i++) {
                    Line boundary = track.getBoundaries().get(i);
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

    public void currentTurn(){
        System.out.println("the current turn right now is" + currentTurn);
    }

}