package com.example.javaproject2025;
import com.example.javaproject2025.ui.GameScreen;
//import com.bitracer.ui.GameScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameScreen gameScreen = new GameScreen();
        Scene scene = gameScreen.getScene();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}