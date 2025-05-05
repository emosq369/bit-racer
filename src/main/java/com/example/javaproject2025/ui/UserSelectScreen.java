package com.example.javaproject2025.ui;

import com.example.javaproject2025.Session;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.example.javaproject2025.utils.ScreenUtils;

public class UserSelectScreen {
    private Scene scene;
    public final double sceneWidth = 600;
    public final double sceneHeight = 600;

    public UserSelectScreen(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");
        root.setPrefSize(sceneWidth, sceneHeight);
        ScreenUtils.drawStars(root);

        // Title at the top center
        Label title = labelCreation("SELECT USER", Color.WHITE, 36);
        VBox topBox = new VBox(title);
        topBox.setAlignment(Pos.CENTER);
        topBox.setMinHeight(100);
        root.setTop(topBox);

        // Player selection in the center
        String p1 = Session.getPlayer1();
        String p2 = Session.getPlayer2();

        Label player1Label = ScreenUtils.createGlowingLabel(p1, Color.RED, 35);
        player1Label.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        player1Label.setOnMouseClicked(e -> primaryStage.setScene(new UserProfileScreen(primaryStage, p1).getScene()));

        Label player2Label = ScreenUtils.createGlowingLabel(p2, Color.BLUE, 35);
        player2Label.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        player2Label.setOnMouseClicked(e -> primaryStage.setScene(new UserProfileScreen(primaryStage, p2).getScene()));

        HBox centerBox = new HBox(80, player1Label, player2Label);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);

        // Main menu at bottom center
        Label mainMenu = ScreenUtils.createGlowingLabel("MAIN MENU", Color.WHITE, 20);
        mainMenu.setOnMouseClicked(e -> primaryStage.setScene(new MainScreen(primaryStage).getScene()));
        VBox bottomBox = new VBox(mainMenu);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setMinHeight(80);
        root.setBottom(bottomBox);

        scene = new Scene(root, 600, 600);
    }

    private Label labelCreation(String text, Color color, int fontSize) {
        Label label = new Label(text);
        label.setTextFill(color);
        label.setFont(Font.font("Orbitron", FontWeight.BOLD, fontSize));
        label.setEffect(ScreenUtils.createNeonGlow(color));
        return label;
    }

    public Scene getScene() {
        return scene;
    }
}
