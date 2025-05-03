package com.example.javaproject2025.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static com.example.javaproject2025.ui.ScreenUtils.*;

public class UserProfileScreen {
    private Scene scene;
    private String userOneUsername;
    private String userTwoUsername;
    private Stage primaryStage;

    public UserProfileScreen(Stage primaryStage, String userOne, String userTwo) {
        this.primaryStage = primaryStage;
        this.userOneUsername = userOne;
        this.userTwoUsername = userTwo;

        // Background pane with stars
        Pane backgroundPane = new Pane();
        backgroundPane.setPrefSize(600, 600);
        backgroundPane.setStyle("-fx-background-color: black;");
        drawStars(backgroundPane);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: transparent;");

        // VBox for log out
        VBox topVBox = new VBox();
        topVBox.setAlignment(Pos.TOP_CENTER);

        // Logout
        Label logout = createNavigationLabel("LOG OUT");
        logout.setTranslateX(-20);
        logout.setTranslateY(10);
        // Logout in its own small container
        BorderPane logoutPane = new BorderPane();
        logoutPane.setRight(logout);
        logoutPane.setPrefHeight(40);
        logoutPane.setStyle("-fx-background-color: transparent;");

        Label username = new Label("Player1");
        username.setFont(Font.font("Orbitron", FontWeight.BOLD, 50));
        username.setStyle("-fx-text-fill: white;");
        username.setEffect(createNeonGlow(Color.WHITE));
        topVBox.getChildren().addAll(logoutPane, username);

        root.setTop(topVBox);

        // Center Section (Profile Info)
        VBox centerSection = new VBox(20);
        centerSection.setAlignment(Pos.CENTER);

        //Default values for now
        Label totalPlays = createProfileLabel("Total Plays: ", Color.BLUE);
        totalPlays.setStyle("-fx-text-fill: blue;");
        Label tP = createProfileLabel("42", Color.WHITE);
        Label wins = createProfileLabel("Wins: ", Color.RED);
        Label w = createProfileLabel("10", Color.WHITE);
        wins.setStyle("-fx-text-fill: red;");
        HBox plays = new HBox();
        plays.setAlignment(Pos.CENTER);
        plays.getChildren().addAll(totalPlays, tP);
        HBox winsH = new HBox();
        winsH.setAlignment(Pos.CENTER);
        winsH.getChildren().addAll(wins, w);

        Button scoresButton = new Button("SCORES");
        scoresButton.setFont(Font.font("Orbitron", FontWeight.BOLD, 35));
        scoresButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; " +
                "-fx-border-color: white;");
        scoresButton.setEffect(createNeonGlow(Color.WHITE));

        centerSection.getChildren().addAll(plays, winsH, scoresButton);
        root.setCenter(centerSection);

        // Bottom Section (Main menu link)
        Button mainMenu = new Button("MAIN MENU");
        //This links the "Main menu" button to MainScreen
        mainMenu.setOnMouseClicked(event -> {
            MainScreen mainScreen = new MainScreen(primaryStage, userOneUsername, userTwoUsername);
            Scene mainScene = mainScreen.getScene();
            primaryStage.setScene(mainScene);
        });

        styleNavigationButton(mainMenu);

        HBox bottomSection = new HBox(300, mainMenu);
        bottomSection.setAlignment(Pos.BOTTOM_CENTER);
        root.setBottom(bottomSection);

        StackPane layeredRoot = new StackPane(backgroundPane, root);
        scene = new Scene(layeredRoot, 600, 600);
    }

    public static Label createProfileLabel(String text, Color color) {

        Label label = new Label(text);
        label.setFont(Font.font("Orbitron", FontWeight.BOLD, 35));
        label.setTextFill(Color.WHITE);

        label.setEffect(createNeonGlow(color));

        return label;
    }

    private void styleNavigationButton(Button button) {
        button.setFont(Font.font("Orbitron", FontWeight.BOLD, 16));
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        button.setEffect(createNeonGlow(Color.WHITE));
    }

    public Scene getScene() {
        return scene;
    }
}
