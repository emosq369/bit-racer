package com.example.javaproject2025.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static com.example.javaproject2025.ui.MainScreen.createNeonGlow;

public class StageSelect {

    private Pane root = new Pane();
    private Scene scene;
    public Label track1Label = labelCreation("TRACK 1", Color.WHITE, 35, 190);
    public Label track2Label = labelCreation("TRACK 2", Color.WHITE, 235, 190);
    public Label track3Label = labelCreation("TRACK 3", Color.WHITE, 435, 190);
    public Label mainMenuLabel = labelCreation("MAIN MENU", Color.WHITE, 225, 530);
    public Label trackSelect = labelCreation("SELECT A TRACK", Color.WHITE, 180, 50);
    public String userOneUsername;
    public String userTwoUsername;

    public StageSelect(Stage primaryStage, String userOne, String userTwo) {
        this.userOneUsername = userOne;
        this.userTwoUsername = userTwo;
        Glow glow = new Glow();
        glow.setLevel(10);
        Image image = new Image(getClass().getResource("/images/stageSelect.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(600);

        root.setOnMouseMoved(event -> {
            System.out.println("x " + event.getX() + " y " + event.getY());
        });

        track1Label.setOnMouseClicked(event -> {
            GameScreen gameScreen = new GameScreen(primaryStage, userOneUsername, userTwoUsername, "trackOne");
            Scene scene = gameScreen.getScene();
            primaryStage.setScene(scene);
        });

        mainMenuLabel.setOnMouseClicked(event -> {
            MainScreen mainScreen = new MainScreen(primaryStage, userOneUsername, userTwoUsername);
            Scene scene = mainScreen.getScene();
            primaryStage.setScene(scene);
        });

        scene = new Scene(root, 600, 600);
        root.getChildren().addAll(imageView, track1Label, track2Label, track3Label, mainMenuLabel, trackSelect);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();
    }

    public Scene getScene() {
        return scene;
    }

    static public Label labelCreation(String text, Color neonColor, double x, double y) {
        Label label = new Label(text);
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setFont(Font.font("Orbitron", FontWeight.BOLD, 17));
        label.setTextFill(neonColor);
        DropShadow normalGlow = createNeonGlow(neonColor);
        DropShadow strongGlow = createNeonGlow(neonColor);
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

}
