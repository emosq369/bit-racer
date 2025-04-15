package com.example.javaproject2025;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class testEdwin extends Application {
    @Override
    public void start(Stage primaryStage) {
        // i have to read more about this, but I believe this is how
        // we can handle physics and collision checks.
        Image gifImage = new Image(getClass().getResource("/images/starGif.gif").toExternalForm());
        ImageView gifView = new ImageView(gifImage);
        gifView.setFitHeight(600);
        gifView.setFitWidth(600);
        gifView.setPreserveRatio(true);

        Pane root = new Pane();
        Scene scene = new Scene(root, 600,600, Color.BLACK);
        Text myText = new Text();
        GameHUD gameHUD = new GameHUD();
        Text previousBitCoordinates = new Text();
        Bit bit1 = new Bit(237, 568);

        root.getChildren().add(gifView);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();

        // this makes the window show in focus rather than having to be opened
        // by selecting it.
        primaryStage.requestFocus();

        Vector position = new Vector(200, 200);
        Vector velocity = new Vector(2.5, 2);
        Circle vectorCircle = new Circle(position.x, position.y, 20);
        vectorCircle.setFill(Color.WHITE);
        vectorCircle.setStroke(Color.BLACK);
        // I believe game physics will be handled in here. This function constantly
        // checks for updates within our app.
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (position.x > 600 || position.x < 0) {
                    velocity.x = velocity.x * -1;
                }
                if (position.y > 600 || position.y < 0) {
                    velocity.y = velocity.y * -1;
                }
                bit1.bitRendered.setOnMouseDragged(jmouseEvent -> {
                    position.add(velocity);
                    vectorCircle.setCenterX(position.x);
                    vectorCircle.setCenterY(position.y);
//                    bit1.bitRendered.setCenterX(bit1.bitRendered.getCenterX() + 10);
                });
//                bit1.bitRendered.setCenterX(bit1.bitRendered.getCenterX() + .10);
            }
        };

        // functionality for restarting, just for testing reasons.
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.R) {
                previousBitCoordinates.setText("previous bit coordinate " + " x : " + bit1.bitRendered.getCenterX() + " " + "y : " + bit1.bitRendered.getCenterY() + "\n");
                bit1.restartPosition();
                myText.setText("bit coordinates " + " x : " + bit1.bitRendered.getCenterX() + " " + "y : " + bit1.bitRendered.getCenterY() + "\n");
            }
        });

        System.out.println("info about boundary");
        System.out.println(bit1.bitBoundary.getCenterX());
        System.out.println(bit1.bitBoundary.getCenterY());
        System.out.println("radius calc : " + bit1.bitBoundary.getRadius());

        scene.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println("moved");
            }
        });

        // 1. need to keep track of current position
        // 2. current position + amount pulled back in direction = distance moved
        // 3. figure out how to move such distance.
        previousBitCoordinates.setFill(Color.RED);
        previousBitCoordinates.setStyle("-fx-font-size: 20");
        previousBitCoordinates.setText("previous bit coordinate " + " x : " + bit1.bitRendered.getCenterX() + " " + "y : " + bit1.bitRendered.getCenterY()+ "\n");

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
//                myText.setText(myText.getText() + "out of bounds");"
            bit1.bitRendered.setFill(Color.RED);
//            bit1.bitBoundary.setCenterX(jmouseEvent.getX());
//            bit1.bitBoundary.setCenterY(jmouseEvent.getY());
            myText.setText("mouse coordinates " + " x : " + jmouseEvent.getX() + " " + "y : " + jmouseEvent.getY()+ "\n");


        });

        bit1.bitRendered.setOnMouseReleased(jmouseEvent -> {
            bit1.bitRendered.setCenterX(bit1.bitRendered.getCenterX() + 15);
            bit1.bitBoundary.setCenterX(bit1.bitRendered.getCenterX());
            bit1.bitBoundary.setCenterY(bit1.bitRendered.getCenterY());
            myText.setText(myText.getText() + '\n' + "bit coordinates " + " x : " + bit1.bitRendered.getCenterX() + " " + "y : " + bit1.bitRendered.getCenterY() + "\n");
            previousBitCoordinates.setText("previous bit coordinate " + " x : " + bit1.bitRendered.getCenterX() + " " + "y : " + bit1.bitRendered.getCenterY()+ "\n");
        });

        //Sample track
        Track beginnerTrack = new Track("Beginner Track");
        Line leftTop = new Line(300, primaryStage.getWidth()/2, 180, 0);
        Line rightTop = new Line(480, primaryStage.getWidth()/2, 360, 0);
        Line leftBottom = new Line(300, primaryStage.getWidth()/2, 180, primaryStage.getWidth());
        Line rightBottom = new Line(480, primaryStage.getWidth()/2, 360, primaryStage.getWidth());
        // Add to track
        beginnerTrack.addBoundary(leftTop);
        beginnerTrack.addBoundary(leftBottom);
        beginnerTrack.addBoundary(rightBottom);
        beginnerTrack.addBoundary(rightTop);
        beginnerTrack.render(root);
        bit1.render(root);
        gameHUD.render(root);
        myText.setX(40);
        myText.setStyle("-fx-font-size: 20px;");
        myText.setFill(Color.WHITE);
        myText.setY(60);
        previousBitCoordinates.setX(40);
        previousBitCoordinates.setY(34);
        timer.start();
        root.getChildren().add(vectorCircle);
        root.getChildren().add(position.direction);
//        Render text for testing
//        root.getChildren().add(myText);
//        root.getChildren().add(previousBitCoordinates);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
