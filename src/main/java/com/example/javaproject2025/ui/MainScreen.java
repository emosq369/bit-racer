package com.example.javaproject2025.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.example.javaproject2025.utils.ScreenUtils;
import com.example.javaproject2025.Session;

public class MainScreen {
    private Scene scene;

    public MainScreen(Stage primaryStage) {

        // Create a background with stars
        Pane backgroundPane = new Pane();
        backgroundPane.setPrefSize(600, 600);
        backgroundPane.setStyle("-fx-background-color: black;");
        ScreenUtils.drawStars(backgroundPane); // Draw 100 random stars

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: transparent;");

        // Logo
        Image logo = new Image("BitRacerLogo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(300);
        logoView.setPreserveRatio(true);

        // Menu options
        VBox menu = new VBox(30); // 20 px vertical spacing
        menu.setAlignment(Pos.CENTER);

        Label playLabel = createMenuLabel("PLAY", Color.WHITE);
        playLabel.setOnMouseClicked(event -> {
            StageSelect playScreen = new StageSelect(primaryStage, Session.getPlayer1(), Session.getPlayer2());
            Scene playScene = playScreen.getScene();
            primaryStage.setScene(playScene);
        });

        Label userProfileLabel = createMenuLabel("USER PROFILE", Color.WHITE);
        userProfileLabel.setOnMouseClicked(event -> {
            UserSelectScreen userSelectScreen = new UserSelectScreen(primaryStage);
            Scene userScene = userSelectScreen.getScene();
            primaryStage.setScene(userScene);
        });

        Label scoresLabel = createMenuLabel("HIGH SCORES", Color.WHITE);
        scoresLabel.setOnMouseClicked(event -> {
            primaryStage.setScene(new GeneralScoresScreen(primaryStage).getScene());
        });

        menu.getChildren().addAll(playLabel, userProfileLabel, scoresLabel);

        // Logout at top right
        Label logout = createMenuLabel("LOG OUT", Color.LIGHTGRAY);
        logout.setFont(Font.font("Orbitron", 16));
        logout.setTranslateX(-20);
        logout.setTranslateY(10);
        logout.setOnMouseClicked(event -> {
            try {
                LoginScreen loginScreen = new LoginScreen(primaryStage);
                Scene loginScene = loginScreen.getScene();
                primaryStage.setScene(loginScene);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // VBox for logout and logo
        VBox topVBox = new VBox();
        topVBox.setAlignment(Pos.TOP_CENTER);

        BorderPane logoutPane = new BorderPane();
        logoutPane.setRight(logout);
        logoutPane.setPrefHeight(40);
        logoutPane.setStyle("-fx-background-color: transparent;");
        topVBox.getChildren().addAll(logoutPane, logoView);

        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.getChildren().addAll(topVBox, menu);

        root.setCenter(mainLayout);

        StackPane layeredRoot = new StackPane(backgroundPane, root);
        scene = new Scene(layeredRoot, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();
    }

    public static Label createMenuLabel(String text, Color color) {
        Label label = new Label(text);
        label.setFont(Font.font("Orbitron", FontWeight.BOLD, 35));
        label.setTextFill(color);

        DropShadow normalGlow = ScreenUtils.createNeonGlow(Color.WHITE);
        DropShadow strongGlow = ScreenUtils.createNeonGlow(Color.WHITE);
        strongGlow.setRadius(40);

        label.setEffect(normalGlow);

        label.setOnMouseEntered(event -> {
            label.setEffect(strongGlow);
            label.setScaleX(1.1);
            label.setScaleY(1.1);
        });

        label.setOnMouseExited(event -> {
            label.setEffect(normalGlow);
            label.setScaleX(1.0);
            label.setScaleY(1.0);
        });

        return label;
    }

    public Scene getScene() {
        return scene;
    }
}
