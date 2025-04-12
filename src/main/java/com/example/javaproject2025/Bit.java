package com.example.javaproject2025;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


import java.util.ArrayList;
import java.util.List;

public class Bit {


    public Line bitDirection = new Line();
    public Circle bitRendered = new Circle();
    public Circle bitBoundary = new Circle();

    // List for 2 bits per level
    public List<Bit> levelBits = new ArrayList<>();

    // Based on where they should be according to the track
    // that is currently being played.

    public int xPos;
    private int yPos;

    // Bit Constructor
    public Bit( int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        bitBoundary();
        bitLine();
        bitRendered.setCenterY(yPos);
        bitRendered.setCenterX(xPos);
        bitRendered.setRadius(10);
        bitRendered.setStroke(Color.WHITE);
        bitRendered.setFill(Color.WHITE);
        this.levelBits.add(this);
    }

    public void bitBoundary(){
        this.bitBoundary.setCenterX(xPos);
        this.bitBoundary.setCenterY(yPos);
        this.bitBoundary.setRadius(35);
        this.bitBoundary.setOpacity(0.5);
        this.bitBoundary.setFill(Color.WHITE);
        this.bitBoundary.setStroke(Color.WHITE);
    }

    public void bitLine(){
        this.bitDirection.setStroke(Color.WHITE);
        this.bitDirection.setFill(Color.WHITE);
        this.bitDirection.setStartY(yPos);
        this.bitDirection.setStartX(xPos);
        this.bitDirection.setStrokeWidth(5);
        this.bitDirection.setOpacity(0.5);
        this.bitDirection.setEndX(xPos + 5);
        this.bitDirection.setEndY(yPos + 5);
//        this.bitDirection.setEndY(335);
//        this.bitDirection.setEndX(335);
    }

    public void moveBit() {
//        bitRendered.setOnMouseClicked(event -> {
//            MouseButton mb = event.getButton();
//            System.out.println("hello chicken");
//        });
        this.bitRendered.setCenterY(yPos + 10);
    }


    public void render(Pane root) {
        root.getChildren().addAll(bitBoundary,bitDirection, bitRendered);
    }

}

