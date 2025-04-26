package com.example.javaproject2025;
import com.example.javaproject2025.game.Bit;
import com.example.javaproject2025.game.Track;
import com.example.javaproject2025.game.Vector;
import com.example.javaproject2025.ui.GameHUD;
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
        Image gifImage = new Image(getClass().getResource("/images/shootingStar.gif").toExternalForm());
        ImageView gifView = new ImageView(gifImage);
        gifView.setFitHeight(600);
        gifView.setFitWidth(600);
        gifView.setPreserveRatio(true);

        Pane root = new Pane();
        Scene scene = new Scene(root, 600,600, Color.BLACK);
        Text myText = new Text();
        GameHUD gameHUD = new GameHUD();
        Text previousBitCoordinates = new Text();
        Bit bit1 = new Bit("bit1", 237, 568, Color.RED);
        root.getChildren().add(gifView);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();

        // this makes the window show in focus rather than having to be opened
        // by selecting it.
        primaryStage.requestFocus();

        Vector position = new Vector(200, 200);
        Vector velocity = new Vector(2.5, 4);
        Circle vectorCircle = new Circle(position.x, position.y, 20);
        Vector circleToCursorPosition = new Vector(vectorCircle.getCenterX(), vectorCircle.getCenterY());
        Vector fakeMove = new Vector(2,2);
        Line circleToCursor = new Line(circleToCursorPosition.x, circleToCursorPosition.y, 0, 0 );
        circleToCursor.setFill(Color.RED);
        circleToCursor.setStroke(Color.RED);
        vectorCircle.setFill(Color.WHITE);
        vectorCircle.setStroke(Color.BLACK);
        // I believe game physics will be handled in here. This function constantly
        // checks for updates within our app.
        AnimationTimer timer = new AnimationTimer() {
            boolean move = false;
            @Override
            public void handle(long l) {
                circleToCursor.setStartX(vectorCircle.getCenterX());
                circleToCursor.setStartY(vectorCircle.getCenterY());
                scene.setOnMouseClicked(mouseEvent ->{
                    move = true;
//                    fakeMove.x = (vectorCircle.getCenterX() - mouseEvent.getX()) * 1/2;
//                    fakeMove.y = (vectorCircle.getCenterY() - mouseEvent.getY()) * 1/2;
//                    //position.add(velocity);
//                    //vectorCircle.setCenterX(position.x);
//                    //vectorCircle.setCenterY(position.y);
//                    //velocity.x += 1;
//                    //kvelocity.y += 1;
//                    position.add(fakeMove);
//                    vectorCircle.setCenterX(position.x);
//                    vectorCircle.setCenterY(position.y);
//
                });

                if(!move) {
                }
                else{
                    position.add(velocity);
                    vectorCircle.setCenterX(position.x);
                    vectorCircle.setCenterY(position.y);
                    if (position.x > 600 || position.x < 0) {
                       velocity.x = velocity.x * -1;
                       move = false;
                       circleToCursorPosition.x = vectorCircle.getCenterX();
                       circleToCursorPosition.y = vectorCircle.getCenterY();
                    }
                    if (position.y > 600 || position.y < 0) {
                        velocity.y = velocity.y * -1;
                        move = false;
                    }
                }
            }
        }; // Animation timer ends here

//                bit1.bitRendered.setOnMouseDragged(jmouseEvent -> {
//                    bit1.bitRendered.setCenterX(bit1.bitRendered.getCenterX() + 10);
//                });
//                bit1.bitRendered.setCenterX(bit1.bitRendered.getCenterX() + .10);

        scene.setOnMouseMoved(jmouseEvent ->{
            circleToCursor.setEndX(jmouseEvent.getX());
            circleToCursor.setEndY(jmouseEvent.getY());
            //System.out.println("x" + Math.abs(circleToCursor.getEndX() - vectorCircle.getCenterX()));
            //System.out.println("y" + Math.abs(circleToCursor.getEndY() - vectorCircle.getCenterY()));
        });

        // functionality for restarting, just for testing reasons.

        System.out.println("info about boundary");

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

        //Sample track
        Track beginnerTrack = new Track("Beginner Track");
        Line leftTop = new Line(300, primaryStage.getWidth()/2, 180, 0);
        Line rightTop = new Line(480, primaryStage.getWidth()/2, 360, 0);
        Line leftBottom = new Line(300, primaryStage.getWidth()/2, 180, primaryStage.getWidth());
        Line rightBottom = new Line(480, primaryStage.getWidth()/2, 360, primaryStage.getWidth());
        // Add to track
        //gameHUD.render(root);
        myText.setX(40);
        myText.setStyle("-fx-font-size: 20px;");
        myText.setFill(Color.WHITE);
        myText.setY(60);
        previousBitCoordinates.setX(40);
        previousBitCoordinates.setY(34);
        timer.start();
        root.getChildren().add(vectorCircle);
        root.getChildren().add(position.direction);
        root.getChildren().add(bit1.getShape());
        root.getChildren().add(circleToCursor);
//        Render text for testing
//        root.getChildren().add(myText);
//        root.getChildren().add(previousBitCoordinates);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
