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
import javafx.scene.shape.Circle;

import java.sql.SQLException;

import static com.example.javaproject2025.ui.ScreenUtils.*;

public class MainScreen {
    private Scene scene;
    public String userOneUsername;
    public String userTwoUsername;

    public MainScreen(Stage primaryStage, String userOne, String userTwo) {
        System.out.println("bit one is : " + userOne);
        System.out.println("bit two is : " + userTwo);
        this.userOneUsername = userOne;
        this.userTwoUsername = userTwo;

        // Create a background with stars
        Pane backgroundPane = new Pane();
        backgroundPane.setPrefSize(600, 600);
        backgroundPane.setStyle("-fx-background-color: black;");
        drawStars(backgroundPane, 100); // Draw 100 random stars

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
        playLabel.setOnMouseClicked(event -> {
            StageSelect playScreen = new StageSelect(primaryStage, userOneUsername, userTwoUsername);
            Scene playScene = playScreen.getScene();
            primaryStage.setScene(playScene);
        });

        Label userProfileLabel = createMenuLabel("USER PROFILE");
        userProfileLabel.setOnMouseClicked(event -> {
            UserProfileScreen userProfileScreen = new UserProfileScreen(primaryStage);
            Scene userScene = userProfileScreen.getScene();
            primaryStage.setScene(userScene);
        });

        Label scoresLabel = createMenuLabel("SCORES");

        menu.getChildren().addAll(playLabel, userProfileLabel, scoresLabel);

        // Logout at top right
        Label logout = createMenuLabel("LOG OUT", Color.LIGHTGRAY);
        logout.setFont(Font.font("Orbitron", 16));
        logout.setTextFill(Color.LIGHTGRAY);
        logout.setEffect(createNeonGlow(Color.LIGHTGRAY));
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

    public static Label createMenuLabel(String text, Color neonColor) {
        Label label = new Label(text);
        label.setFont(Font.font("Orbitron", FontWeight.BOLD, 35));
        label.setTextFill(Color.WHITE);

        DropShadow normalGlow = createNeonGlow(Color.WHITE);
        DropShadow strongGlow = createNeonGlow(Color.WHITE);
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

    public static DropShadow createNeonGlow(Color color) {
        DropShadow glow = new DropShadow();
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setColor(color);
        glow.setRadius(20);
        return glow;
    }

    private void drawStars(Pane pane, int numberOfStars) {
        DropShadow glow = new DropShadow();
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setColor(Color.WHITE);
        glow.setRadius(10);

        for (int i = 0; i < numberOfStars; i++) {
            double x = Math.random() * pane.getPrefWidth();
            double y = Math.random() * pane.getPrefHeight();
            Circle star = new Circle(x, y, Math.random() * 0.5, Color.WHITE);
            pane.getChildren().add(star);
        }
    }

    public Scene getScene() {
        return scene;
    }
}
