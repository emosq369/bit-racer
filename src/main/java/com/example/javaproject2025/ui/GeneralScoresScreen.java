package com.example.javaproject2025.ui;

import com.example.javaproject2025.utils.ScreenUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.TextAlignment;
import javafx.scene.effect.DropShadow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GeneralScoresScreen {
    private Scene scene;
    public final double sceneWidth = 600;
    public final double sceneHeight = 600;

    public GeneralScoresScreen(Stage primaryStage ) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");
        root.setPrefSize(sceneWidth, sceneHeight);
        ScreenUtils.drawStars(root);

        Label title = new Label("TOP SCORES");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Orbitron", 40));
        title.setEffect(new DropShadow(10, Color.WHITE));
        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);

        VBox scoresBox = new VBox(10);
        scoresBox.setAlignment(Pos.CENTER);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/bitracer", "root", "bitracerDB"
            );
            Statement stmt = connection.createStatement();
            String query =
                    "SELECT s.track, s.username, s.score " +
                            "FROM scores s " +
                            "JOIN ( " +
                            "    SELECT track, MAX(score) AS max_score " +
                            "    FROM scores " +
                            "    GROUP BY track " +
                            ") AS max_scores " +
                            "ON s.track = max_scores.track AND s.score = max_scores.max_score";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String track = rs.getString("track");
                String username = rs.getString("username");
                int score = rs.getInt("score");
                Label scoreLabel = new Label("Track " + track + ": " + username + " - " + score);
                scoreLabel.setTextFill(Color.WHITE);
                scoreLabel.setFont(Font.font("Orbitron", 24));
                scoresBox.getChildren().add(scoreLabel);
            }

            connection.close();
        } catch (Exception e) {
            Label error = new Label("Error loading scores");
            error.setTextFill(Color.RED);
            scoresBox.getChildren().add(error);
            e.printStackTrace();
        }

        Label mainMenu = new Label("MAIN MENU");
        mainMenu.setTextFill(Color.WHITE);
        mainMenu.setFont(Font.font("Orbitron", 20));
        mainMenu.setEffect(new DropShadow(5, Color.WHITE));
        mainMenu.setTextAlignment(TextAlignment.CENTER);
        mainMenu.setOnMouseClicked(e -> primaryStage.setScene(new MainScreen(primaryStage).getScene()));

        VBox bottomBox = new VBox(mainMenu);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.setMinHeight(80);

        root.setTop(titleBox);
        root.setCenter(scoresBox);
        root.setBottom(bottomBox);

        scene = new Scene(root, 600, 600);
    }

    public Scene getScene() {
        return scene;
    }
}
