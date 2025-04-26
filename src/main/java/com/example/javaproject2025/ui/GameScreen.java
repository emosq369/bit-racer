package com.example.javaproject2025.ui;

import com.example.javaproject2025.game.Bit;
import com.example.javaproject2025.game.GamePhysics;
import com.example.javaproject2025.game.Track;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.management.monitor.MonitorSettingException;
import java.util.Date;
import java.util.List;
import java.util.Timer;


public class GameScreen  {
    private Scene scene;
    public String currentTurn = "";
    public boolean started = false;
    public int bit1Score;
    public int bit2Score;
    public Text scoreBit1 = new Text("Bit 1 Score : " + Integer.toString(bit1Score));
    public Text scoreBit2 = new Text("Bit 2 Score : " + Integer.toString(bit2Score));
    public Timer timer = new Timer();
    private final Glow glowEffect = new Glow(1.5);
    public Font gameFont = Font.loadFont(getClass().getResourceAsStream("/Minecraft.ttf"), 22);
    private Text playerOneScore;
    private Text playerTwoScore;
    private Button backButton;

    public GameScreen() {
        Image gifImage = new Image(getClass().getResource("/images/pulsating_star.gif").toExternalForm());
        Image gifImage2 = new Image(getClass().getResource("/images/pulsating_star.gif").toExternalForm());
        ImageView gifView2 = new ImageView(gifImage);
        gifView2.setX(490);
        gifView2.setY(290);
        gifView2.setFitHeight(80);
        gifView2.setFitWidth(40);
        gifView2.setPreserveRatio(true);
        Image finishLineImage = new Image(getClass().getResource("/images/finishLine.png").toExternalForm());
        Image gameAssets = new Image(getClass().getResource("/images/game_assets.png").toExternalForm());
        ImageView gameAssetsImageView = new ImageView(gameAssets);
        gameAssetsImageView.setFitHeight(600);
        gameAssetsImageView.setFitWidth(600);
        gameAssetsImageView.setPreserveRatio(true);
        ImageView finishLineImageView = new ImageView(finishLineImage);
        finishLineImageView.setScaleX(0.105);
        finishLineImageView.setScaleY(0.105);
        finishLineImageView.setX(-30);
        finishLineImageView.setY(-130);
        randomizeTurn();

        FadeTransition bit1ScoreFade = new FadeTransition(Duration.seconds(1), scoreBit1);
        bit1ScoreFade.setFromValue(1.0);
        bit1ScoreFade.setToValue(0.0);
        FadeTransition bit2ScoreFade = new FadeTransition(Duration.seconds(1), scoreBit2);
        bit2ScoreFade.setFromValue(1.0);
        bit2ScoreFade.setToValue(0.0);
        FadeTransition bit2ScoreAppear= new FadeTransition(Duration.seconds(2), scoreBit2);
        bit2ScoreAppear.setFromValue(0.0);
        bit2ScoreAppear.setToValue(1.0);
        FadeTransition bit1ScoreAppear = new FadeTransition(Duration.seconds(2), scoreBit1);
        bit1ScoreAppear.setFromValue(0.0);
        bit1ScoreAppear.setToValue(1.0);

        scoreBit1.setFont(gameFont);
        scoreBit1.setEffect(glowEffect);
        scoreBit1.setFill(Color.RED);
        scoreBit1.setX(20);
        scoreBit1.setY(520);
        scoreBit1.setStyle("-fx-font-size: 20px;");
        scoreBit2.setFont(gameFont);
        scoreBit2.setEffect(glowEffect);
        scoreBit2.setFill(Color.BLUE);
        scoreBit2.setX(20);
        scoreBit2.setY(520);
        scoreBit2.setStyle("-fx-font-size: 20px;");
        //scoreBit2.setOpacity(0);
        ImageView gifView = new ImageView(gifImage);
        gifView.setX(90);
        gifView.setY(190);
        gifView.setFitHeight(80);
        gifView.setFitWidth(40);
        gifView.setPreserveRatio(true);

        Pane root = new Pane();
        final double sceneWidth = 600;
        final double sceneHeight = 600;

        Line finishLine = new Line();
        finishLine.setStartX(330);
        finishLine.setStartY(100);
        finishLine.setEndX(400);
        finishLine.setEndY(100);
        finishLine.setFill(Color.RED);
        finishLine.setStroke(Color.RED);
        // Instantiate and render level 1's track
        Track track1 = new Track("Level 1");
        track1.buildLevel1Layout(sceneWidth, sceneHeight); // A method to define and add 4 boundary lines
        track1.render(root);

        // Create bits
        Bit bit1 = new Bit("Bit1", sceneWidth*4/9, sceneHeight-5, Color.RED);
        Bit bit2 = new Bit("Bit2", sceneWidth*5/9, sceneHeight-5, Color.BLUE);

        // Add bits and directionals to the scene
        root.getChildren().add(gameAssetsImageView);
        root.getChildren().addAll(bit1.getShape(), bit2.getShape(),
        bit1.getDirectionLine(), bit2.getDirectionLine());
        root.getChildren().add(finishLine);
        root.getChildren().addAll(gifView, gifView2);
        root.getChildren().add(finishLineImageView);

        if(currentTurn.equals("bit1")){
            root.getChildren().add(scoreBit1);
        }
        else{
            root.getChildren().add(scoreBit2);
        }

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
                            root.getChildren().add(scoreBit2);
                            started = false;
                        }
                        bit1Score += 1;
                        bit1.launch(10);
                        bit1.moved = true;
                        scoreBit1.setText("Bit 1 Score : " + Integer.toString(bit1Score));
                        bit1ScoreFade.play();
                        bit2ScoreAppear.play();
                    }

                }
            }
            else{
                    switch (event.getCode()) {
                        case LEFT -> bit2.rotate(-5); //Bit 2 Rotate left
                        case RIGHT -> bit2.rotate(5); // Bit 2 Rotate right
                        case UP -> {
                            if(started){
                                root.getChildren().add(scoreBit1);
                                started = false;
                            }
                            bit2Score += 1;
                            bit1.moved = false;
                            bit2.launch(10);
                            bit2.moved = true;
                            scoreBit2.setText("Bit 2 Score : " + Integer.toString(bit2Score));
                            bit2ScoreFade.play();
                            bit1ScoreAppear.play();
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

                    if (gamePhysics.isCircleCollidingWithLine( bit2.getX(), bit2.getY(), bit2.getShape().getRadius(),
                            boundary.getStartX(), boundary.getStartY(),
                            boundary.getEndX(), boundary.getEndY())) {
                        bit2.launched = false;
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
        System.out.println(randomNumber);
    }

}