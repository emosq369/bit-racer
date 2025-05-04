package com.example.javaproject2025.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.sql.*;
import java.util.*;

import com.example.javaproject2025.utils.ScreenUtils;

public class IndividualScoresScreen {
    private Scene scene;
    public final double sceneWidth = 600;
    public final double sceneHeight = 600;

    public IndividualScoresScreen(Stage primaryStage, String username) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");
        root.setPrefSize(sceneWidth, sceneHeight);
        ScreenUtils.drawStars(root);

        Label title = new Label(username.toUpperCase() + "'S BEST SCORES");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Orbitron", 30));
        title.setEffect(new DropShadow(10, Color.WHITE));

        VBox topBox = new VBox(title);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(30, 0, 10, 0));
        root.setTop(topBox);

        VBox scoresBox = new VBox(30);
        scoresBox.setAlignment(Pos.CENTER);

        Map<String, String> trackNames = new LinkedHashMap<>();
        trackNames.put("track1", "TRACK 1");
        trackNames.put("track2", "TRACK 2");
        trackNames.put("track3", "TRACK 3");

        Map<String, Label> scoreLabels = new HashMap<>();

        for (String key : trackNames.keySet()) {
            Label trackLabel = new Label(trackNames.get(key));
            trackLabel.setTextFill(Color.WHITE);
            trackLabel.setFont(Font.font("Orbitron", 22));

            Label scoreLabel = new Label("...");
            scoreLabel.setTextFill(Color.WHITE);
            scoreLabel.setFont(Font.font("Orbitron", 24));

            VBox entryBox = new VBox(5, trackLabel, scoreLabel);
            entryBox.setAlignment(Pos.CENTER);
            scoresBox.getChildren().add(entryBox);
            scoreLabels.put(key, scoreLabel);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bitracer", "root", "bitracerDB");

            String query = "SELECT track, MIN(score) AS best_score FROM scores WHERE username = ? GROUP BY track";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String track = rs.getString("track");
                int score = rs.getInt("best_score");

                Label label = scoreLabels.get(track);
                if (label != null) {
                    label.setText(String.valueOf(score));
                }
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            Label errorLabel = new Label("Error loading scores.");
            errorLabel.setTextFill(Color.RED);
            errorLabel.setFont(Font.font("Orbitron", 20));
            scoresBox.getChildren().add(errorLabel);
        }

        root.setCenter(scoresBox);

        Label mainMenu = new Label("MAIN MENU");
        mainMenu.setTextFill(Color.WHITE);
        mainMenu.setFont(Font.font("Orbitron", 18));
        mainMenu.setEffect(new DropShadow(5, Color.WHITE));
        mainMenu.setTextAlignment(TextAlignment.CENTER);
        mainMenu.setOnMouseClicked(e -> primaryStage.setScene(new MainScreen(primaryStage).getScene()));

        VBox bottomBox = new VBox(mainMenu);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.setPadding(new Insets(0, 0, 20, 0));
        root.setBottom(bottomBox);

        scene = new Scene(root, sceneWidth, sceneHeight);
    }

    public Scene getScene() {
        return scene;
    }
}
