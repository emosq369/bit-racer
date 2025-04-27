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
import static com.example.javaproject2025.ui.ScreenUtils.*;


public class MainScreen {
    private Scene scene;

    public MainScreen(Stage primaryStage) {

        // Create a background with stars
        Pane backgroundPane = new Pane();
        backgroundPane.setPrefSize(600, 600);
        backgroundPane.setStyle("-fx-background-color: black;");
        drawStars(backgroundPane); // Draw 100 random stars

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

        Label playLabel = createMenuLabel("PLAY");
        //This links the "PLAY" label to GameScreen
        playLabel.setOnMouseClicked(event -> {
            GameScreen gameScreen = new GameScreen();
            Scene gameScene = gameScreen.getScene();
            primaryStage.setScene(gameScene);
        });

        Label userProfileLabel = createMenuLabel("USER PROFILE");
        //This links the "User Profile" label to UserProfileScreen
        userProfileLabel.setOnMouseClicked(event -> {
            UserProfileScreen userProfileScreen = new UserProfileScreen(primaryStage);
            Scene userScene = userProfileScreen.getScene();
            primaryStage.setScene(userScene);
        });

        Label scoresLabel = createMenuLabel("SCORES");

        menu.getChildren().addAll(playLabel, userProfileLabel, scoresLabel);

        // Logout at top right
        Label logout = createNavigationLabel("LOG OUT");
        logout.setTranslateX(-20);
        logout.setTranslateY(10);

        // VBox for logout and logo
        VBox topVBox = new VBox();
        topVBox.setAlignment(Pos.TOP_CENTER);

        // Logout in its own small container
        BorderPane logoutPane = new BorderPane();
        logoutPane.setRight(logout);
        logoutPane.setPrefHeight(40);
        logoutPane.setStyle("-fx-background-color: transparent;");

        topVBox.getChildren().addAll(logoutPane, logoView);


        // VBox for the whole screen layout
        VBox mainLayout = new VBox(); // 60px spacing between logo and menu
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.getChildren().addAll(topVBox, menu);

        // Set up root
        root.setCenter(mainLayout);

        StackPane layeredRoot = new StackPane(backgroundPane, root);

        scene = new Scene(layeredRoot, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();
    }

    private Label createMenuLabel(String text) {

        Label label = new Label(text);
        label.setFont(Font.font("Orbitron", FontWeight.BOLD, 35));
        label.setTextFill(Color.WHITE);

        DropShadow normalGlow = createNeonGlow(Color.WHITE);
        DropShadow strongGlow = createNeonGlow(Color.WHITE);
        strongGlow.setRadius(40); // Bigger glow when hovered

        label.setEffect(normalGlow);

        // Hover effects
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
