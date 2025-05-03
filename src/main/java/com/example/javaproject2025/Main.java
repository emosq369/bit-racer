package com.example.javaproject2025;

import com.example.javaproject2025.ui.GameScreen;
import com.example.javaproject2025.ui.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        MainScreen mainScreen = new MainScreen(primaryStage);
        Scene scene = mainScreen.getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();
        // this allows the window to open on the main screen
        // rather than having to tab to it
        primaryStage.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
