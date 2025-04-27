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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UserProfileScreen {
    private Scene scene;

    public UserProfileScreen() {
        // Background pane with stars
        Pane backgroundPane = new Pane();
        backgroundPane.setPrefSize(600, 600);
        backgroundPane.setStyle("-fx-background-color: black;");
        drawStars(backgroundPane, 100);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: transparent;");

        // Logo
        Image logo = new Image("BitRacerLogo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);

        // Avatar (optional)
        Circle avatarPlaceholder = new Circle(25, Color.TRANSPARENT);

        // Logout
        Label logout = createMenuLabel("LOG OUT");

        // Top Section
        HBox topSection = new HBox(150, logoView, avatarPlaceholder, logout);
//        topSection.setAlignment(Pos.CENTER);
        root.setTop(topSection);

        // Center Section (Profile Info)
        VBox centerSection = new VBox(20);
        centerSection.setAlignment(Pos.CENTER);

        //Default values for now
        Label username = createProfileLabel("Username: Player1");
        Label totalPlays = createProfileLabel("Total Plays: 42");
        Label wins = createProfileLabel("Wins: 10");
        Label fastestTime = createProfileLabel("Fastest Time: 1:32");

        Button scoresButton = new Button("SCORES");
        scoresButton.setFont(Font.font("Orbitron", FontWeight.BOLD, 20));
        scoresButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        scoresButton.setEffect(createNeonGlow(Color.WHITE));

        centerSection.getChildren().addAll(username, totalPlays, wins, fastestTime, scoresButton);
        root.setCenter(centerSection);

        // Bottom Section (Navigation)
        Button editProfile = new Button("EDIT PROFILE");
        Button mainMenu = new Button("MAIN MENU");

        styleNavigationButton(editProfile);
        styleNavigationButton(mainMenu);

        HBox bottomSection = new HBox(200, editProfile, mainMenu);
        bottomSection.setAlignment(Pos.CENTER);
        root.setBottom(bottomSection);

        StackPane layeredRoot = new StackPane(backgroundPane, root);
        scene = new Scene(layeredRoot, 600, 600);
    }

    private void drawStars(Pane pane, int numberOfStars) {
        for (int i = 0; i < numberOfStars; i++) {
            double x = Math.random() * pane.getPrefWidth();
            double y = Math.random() * pane.getPrefHeight();
            Circle star = new Circle(x, y, Math.random() * 1.5 + 0.5, Color.WHITE);
            pane.getChildren().add(star);
        }
    }

    private Label createMenuLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Orbitron", FontWeight.BOLD, 16));
        label.setTextFill(Color.LIGHTGRAY);
        label.setEffect(createNeonGlow(Color.LIGHTGRAY));
        return label;
    }

    private Label createProfileLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Orbitron", FontWeight.BOLD, 20));
        label.setTextFill(Color.WHITE);
        label.setEffect(createNeonGlow(Color.WHITE));
        return label;
    }

    private DropShadow createNeonGlow(Color color) {
        DropShadow glow = new DropShadow();
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setColor(color);
        glow.setRadius(20);
        return glow;
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
