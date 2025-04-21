package com.example.javaproject2025.ui;

import com.example.javaproject2025.game.Bit;
import com.example.javaproject2025.game.Track;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class GameScreen  {
    private Scene scene;

    public GameScreen() {
        Pane root = new Pane();

        final double sceneWidth = 600;
        final double sceneHeight = 600;

        // Instantiate and render level 1's track
        Track track1 = new Track("Level 1");
        track1.buildLevel1Layout(sceneWidth, sceneHeight); // A method to define and add 4 boundary lines
        track1.render(root);

        // Create bits
        Bit bit1 = new Bit("Bit1", sceneWidth*4/9, sceneHeight-5, Color.RED);
        Bit bit2 = new Bit("Bit2", sceneWidth*5/9, sceneHeight-5, Color.BLUE);

// Add bits and directionals to the scene
        root.getChildren().addAll(bit1.getShape(), bit2.getShape(),
                bit1.getDirectionLine(), bit2.getDirectionLine());


        // Add more elements later: bits, arrows, etc.
        scene = new Scene(root, sceneWidth, sceneHeight, Color.BLACK);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case A -> bit1.rotate(-5);  // Bit1 Rotate left
                case D -> bit1.rotate(5);   // Bit 1 Rotate right
                case S -> bit1.launch(10); // launch bit1
                case LEFT -> bit2.rotate(-5); //Bit 2 Rotate left
                case RIGHT -> bit2.rotate(5); // Bit 2 Rotate right
                case UP -> bit2.launch(10); // launch bit2

            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                System.out.println(bit2.getX());
                System.out.println(bit2.getY());
                bit1.moveIfLaunched();
                bit2.moveIfLaunched();
            }
        };
        timer.start();
    }

    public Scene getScene() {
        return scene;
    }


}