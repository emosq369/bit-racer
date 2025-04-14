package com.example.javaproject2025;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameHUD {

    private Text playerTwoScore;
    private Text playerOneScore;
    private Button backButton;
    public Font gameFont = Font.loadFont(getClass().getResourceAsStream("/Minecraft.ttf"), 27);

    public GameHUD() {
        setupScore();
        setupBackButton();
    }

    public void setupBackButton(){
        this.backButton = new Button("return");
        this.backButton.setFont(gameFont);
//        Text backButtonText = new Text("Back");
//        backButtonText.setFill(Color.WHITE);
//        backButtonText.setFont(gameFont);
//        this.backButton.setStyle("-fx-background-color: #f5f5f5; -fx-font-size: 14; -fx-font: \"/Minecraft.ttf;");
        this.backButton.setTranslateX(40);
    }

    public void setupScore(){
        this.playerTwoScore = new Text("P1 Score : 0 ");
        this.playerOneScore = new Text("P2 Score : 0 ");
        this.playerOneScore.setFont(gameFont);
        this.playerTwoScore.setFont(gameFont);
        this.playerTwoScore.setFill(Color.WHITE);
        this.playerOneScore.setFill(Color.WHITE);
        this.playerTwoScore.setX(4.5);
        this.playerTwoScore.setY(30);
        this.playerOneScore.setX(4.5);
        this.playerOneScore.setY(70);
    }


    public void render(Pane root) {
        root.getChildren().add(this.playerOneScore);
        root.getChildren().add(this.playerTwoScore);
        root.getChildren().add(this.backButton);
    }
}
