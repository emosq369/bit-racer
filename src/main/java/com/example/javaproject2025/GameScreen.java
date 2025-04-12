package com.example.javaproject2025;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class GameScreen extends Application {

    @Override
    public void start(Stage primaryStage) {


        Pane root = new Pane();

        Scene scene = new Scene(root, 600, 600, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();

        Bit bit1 = new Bit(237, 568);

        System.out.println("info about boundary");
        System.out.println(bit1.bitBoundary.getCenterX());
        System.out.println(bit1.bitBoundary.getCenterY());
        System.out.println("radisu calc : "  +  bit1.bitBoundary.getRadius());

        scene.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println("moved");
            }
        });

        // 1. need to keep track of current position
        // 2. current position + amount pulled back in direction = distance moved
        // 3. figure out how to move such distance.
        bit1.bitRendered.setOnMouseDragged(jmouseEvent -> {
            // 30 is radius, will fix code later.
            if(
            (jmouseEvent.getX() < bit1.bitBoundary.getCenterX() + 30 && jmouseEvent.getY() < bit1.bitBoundary.getCenterY() + 30) &&
            (jmouseEvent.getX() > bit1.bitBoundary.getCenterX() - 30) && jmouseEvent.getY() > bit1.bitBoundary.getCenterY() - 30)
            {
                bit1.bitRendered.setCenterX(jmouseEvent.getX());
                bit1.bitRendered.setCenterY(jmouseEvent.getY());
                System.out.println("x value : " + bit1.bitRendered.getCenterX());
                System.out.println("y value : " + bit1.bitRendered.getCenterY());
            }
            else
                System.out.println("out of bounds");
            bit1.bitRendered.setFill(Color.RED);
//            bit1.bitBoundary.setCenterX(jmouseEvent.getX());
//            bit1.bitBoundary.setCenterY(jmouseEvent.getY());

        });

        bit1.bitRendered.setOnMouseReleased(jmouseEvent -> {
            bit1.bitRendered.setCenterX(bit1.bitRendered.getCenterX() + 15);
            bit1.bitBoundary.setCenterX(bit1.bitRendered.getCenterX());
            bit1.bitBoundary.setCenterY(bit1.bitRendered.getCenterY());
            bit1.bitDirection.setStartY(bit1.bitRendered.getCenterY());
            bit1.bitDirection.setStartX(bit1.bitRendered.getCenterX());
        });


        //Sample track
        Track beginnerTrack = new Track("Beginner Track");
        Line leftTop = new Line(300, primaryStage.getWidth()/2, 180, 0);
        Line rightTop = new Line(480, primaryStage.getWidth()/2, 360, 0);
        Line leftBottom = new Line(300, primaryStage.getWidth()/2, 180, primaryStage.getWidth());
        Line rightBottom = new Line(480, primaryStage.getWidth()/2, 360, primaryStage.getWidth());
        // Add to track
        beginnerTrack.addBoundary(leftTop);
        beginnerTrack.addBoundary(rightTop);
        beginnerTrack.addBoundary(leftBottom);
        beginnerTrack.addBoundary(rightBottom);
        beginnerTrack.render(root);
        bit1.render(root);


    }

    public static void main(String[] args) {
        launch(args);
    }
}
