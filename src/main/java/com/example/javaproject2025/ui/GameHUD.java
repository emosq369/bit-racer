package com.example.javaproject2025.ui;

import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameHUD {

    // Apr 13
    // We can tweak this however we need to because we'll determine a way to keep track of score, but
    // for now this is what I got.
    private Text playerOneScore;
    private Text playerTwoScore;
    private Button backButton;
    public Font gameFont = Font.loadFont(getClass().getResourceAsStream("/Minecraft.ttf"), 22);
    private final Glow glowEffect = new Glow(0.9);
    public javafx.scene.shape.Rectangle powerMeter;

    public GameHUD() {
        setupScore();
        setupBackButton();
        setupPowerMeter();
    }

    public void setupBackButton(){
        this.backButton = new Button("main menu");
        //this.backButton.setEffect(glowEffect);
        this.backButton.setOnMouseEntered(e -> {
            this.backButton.setTextFill(Color.RED);
        });

        this.backButton.setOnMouseExited(e -> {
            this.backButton.setTextFill(Color.WHITE);
        });

        this.backButton.setFont(gameFont);
        this.backButton.setTextFill(Color.ALICEBLUE);
        this.backButton.setStyle("-fx-background-color: transparent;");
        this.backButton.setTranslateX(460);
    }

    public void setupScore(){
        this.playerOneScore = new Text("P1 Score : 0 ");
        this.playerTwoScore = new Text("P2 Score : 0 ");
        this.playerOneScore.setEffect(glowEffect);
        this.playerTwoScore.setEffect(glowEffect);
        this.playerOneScore.setFont(gameFont);
        this.playerTwoScore.setFont(gameFont);
        this.playerTwoScore.setFill(Color.WHITE);
        this.playerOneScore.setFill(Color.WHITE);
        this.playerOneScore.setX(4.5);
        this.playerOneScore.setY(585);
        this.playerTwoScore.setX(4.5);
        this.playerTwoScore.setY(30);
    }

    public void setupPowerMeter(){
        this.powerMeter = new javafx.scene.shape.Rectangle();
        this.powerMeter.setWidth(15);
        this.powerMeter.setHeight(70);
        this.powerMeter.setEffect(glowEffect);
//        this.powerMeter.setFill(Color.WHITE);
        this.powerMeter.setStroke(Color.WHITE);
        this.powerMeter.setY(520);
        this.powerMeter.setX(570);
    }


}
