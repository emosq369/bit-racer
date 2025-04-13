package com.example.javaproject2025;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
    import javafx.scene.shape.Line;


    import java.util.ArrayList;
    import java.util.List;

    public class Bit extends ImageView {


        public double circleWeight = 10;
        public Circle bitRendered = new Circle();
        public Circle bitBoundary = new Circle();
        // List for 2 bits per level
        public List<Bit> levelBits = new ArrayList<>();

        // Based on where they should be according to the track
        // that is currently being played.

        public int startingXPos = 237;
        public int startingYPos = 568;

        // Bit Constructor
        public Bit(int xPos, int yPos) {
    //        this.startingXPos = xPos;
    //        this.startingYPos = yPos;
            bitBoundary();
            bitRendered.setCenterY(startingYPos);
        bitRendered.setCenterX(startingXPos);
        bitRendered.setRadius(10);
        bitRendered.setStroke(Color.WHITE);
        bitRendered.setFill(Color.WHITE);
        this.levelBits.add(this);
    }

    public void bitBoundary() {
        this.bitBoundary.setCenterX(startingXPos);
        this.bitBoundary.setCenterY(startingYPos);
        this.bitBoundary.setRadius(35);
        this.bitBoundary.setOpacity(0.5);
        this.bitBoundary.setFill(Color.WHITE);
        this.bitBoundary.setStroke(Color.WHITE);
    }

    public void restartPosition() {
        this.bitRendered.setCenterX(startingXPos);
        this.bitRendered.setCenterY(startingYPos);
        this.bitBoundary.setCenterX(startingXPos);
        this.bitBoundary.setCenterY(startingYPos);
    }

    public void moveBit(double mouseLocationX, double mouseLocationY) {

    }


    public void render(Pane root) {
        root.getChildren().addAll(bitBoundary, bitRendered);
    }

}


