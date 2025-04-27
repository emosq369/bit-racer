package com.example.javaproject2025.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
        //This links the "PLAY" button to GameScreen
        playLabel.setOnMouseClicked(event -> {
            GameScreen gameScreen = new GameScreen();
            Scene gameScene = gameScreen.getScene();
            primaryStage.setScene(gameScene);
        });

        Label userProfileLabel = createMenuLabel("USER PROFILE");
        //This links the "User Profile" button to UserProfileScreen
        userProfileLabel.setOnMouseClicked(event -> {
            UserProfileScreen userProfileScreen = new UserProfileScreen();
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

    public Scene getScene() {
        return scene;
    }
}
