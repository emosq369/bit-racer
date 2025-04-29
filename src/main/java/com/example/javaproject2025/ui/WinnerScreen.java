package com.example.javaproject2025.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinnerScreen {

    private Scene scene;
    public Pane root;
    private Text winner;

    public WinnerScreen(Stage primaryStage){
        GameScreen gameScreen = new GameScreen(primaryStage);
        Label mainMenu = gameScreen.mainMenuButton;
        mainMenu.setTranslateX(250);
        mainMenu.setTranslateY(500);
        root = new Pane();
        root.setStyle("-fx-background-color: black;");
        Image gameAssets = new Image(getClass().getResource("/images/bit0WinScreen.png").toExternalForm());
        ImageView gameAssetsImageView = new ImageView(gameAssets);
        gameAssetsImageView.setFitHeight(600);
        gameAssetsImageView.setFitWidth(605);
        gameAssetsImageView.setPreserveRatio(true);
        Text winnerDisplay = new Text("Winner");
        winnerDisplay.setStyle("-fx-font-size: 20px;");
        winnerDisplay.setFill(Color.WHITE);
        winnerDisplay.setX(5);
        winnerDisplay.setY(5);
        root.getChildren().add(winnerDisplay);
        root.getChildren().add(gameAssetsImageView);
        root.getChildren().add(mainMenu);
        scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();
    }

    public void sendScore(String winnerString ,int score){
        winner = new Text(winnerString + " WINS");
        if(winnerString.equals("BIT 1")){
            winner.setFill(Color.RED);
        }
        else if(winnerString.equals("BIT 2")){
            winner.setFill(Color.BLUE);
        }

        Text displayScore = new Text(Integer.toString(score));
        displayScore.setFont(Font.font("Orbitron", FontWeight.BOLD, 32));
        displayScore.setX(335);
        displayScore.setY(275);
        displayScore.setFill(Color.WHITE);
        root.getChildren().add(displayScore);

    }

    public Scene getScene() {
        return scene;

    }
}
