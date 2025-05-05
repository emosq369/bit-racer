package com.example.javaproject2025.ui;

import com.example.javaproject2025.utils.ScreenUtils;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GeneralScoresScreen {
    private Scene scene;
    public final double sceneWidth = 600;
    public final double sceneHeight = 600;

    public GeneralScoresScreen(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");
        root.setPrefSize(sceneWidth, sceneHeight);
        ScreenUtils.drawStars(root);

        // Title
        Label title = new Label("TOP SCORES");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Orbitron", 36));
        title.setEffect(new DropShadow(10, Color.WHITE));
        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(30, 0, 10, 0));
        root.setTop(titleBox);

        // Scores Layout
        VBox scoresBox = new VBox(30);
        scoresBox.setAlignment(Pos.CENTER);

        // Track name mapping (for display only)
        Map<String, String> trackNames = new LinkedHashMap<>();
        trackNames.put("track1", "TRACK 1");
        trackNames.put("track2", "TRACK 2");
        trackNames.put("track3", "TRACK 3");

        // Initialize empty labels for each track
        Map<String, Label> trackLabels = new HashMap<>();
        for (String trackKey : trackNames.keySet()) {
            Label trackLabel = new Label(trackNames.get(trackKey));
            trackLabel.setFont(Font.font("Orbitron", 30));
            trackLabel.setTextFill(Color.WHITE);

            Label scoreLabel = new Label("...");
            scoreLabel.setFont(Font.font("Orbitron", 24));
            scoreLabel.setTextFill(Color.WHITE);

            VBox entryBox = new VBox(5, trackLabel, scoreLabel);
            entryBox.setAlignment(Pos.CENTER);
            scoresBox.getChildren().add(entryBox);

            trackLabels.put(trackKey, scoreLabel); // only store scoreLabel for updating later

        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/bitracer", "root", "bitracerDB"
            );
            Statement stmt = connection.createStatement();

                            String query =
                    "SELECT s.track, s.winner, s.score, s.loser " +
                            "FROM scores s " +
                            "JOIN ( " +
                            "    SELECT track, MIN(score) AS best_score " +
                            "    FROM scores " +
                            "    GROUP BY track " +
                            ") AS top_scores " +
                            "ON s.track = top_scores.track AND s.score = top_scores.best_score";



            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String track = rs.getString("track");
                String winner = rs.getString("winner");
                int score = rs.getInt("score");

                Label label = trackLabels.get(track);
                if (label != null) {
                    label.setText(winner + " - " + score);
                }
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // MAIN MENU
        Label mainMenu = ScreenUtils.createGlowingLabel("MAIN MENU", Color.WHITE, 20);
        mainMenu.setTextAlignment(TextAlignment.CENTER);
        mainMenu.setOnMouseClicked(e -> primaryStage.setScene(new MainScreen(primaryStage).getScene()));

        VBox bottomBox = new VBox(mainMenu);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.setPadding(new Insets(0, 0, 20, 0));

        root.setCenter(scoresBox);
        root.setBottom(bottomBox);

        scene = new Scene(root, (int) sceneWidth, (int) sceneHeight);
    }

    public Scene getScene() {
        return scene;
    }
}
