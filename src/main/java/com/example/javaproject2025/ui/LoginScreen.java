package com.example.javaproject2025.ui;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import static com.example.javaproject2025.ui.MainScreen.createMenuLabel;
import static com.example.javaproject2025.ui.MainScreen.createNeonGlow;

public class LoginScreen{

    private Scene scene;
    public Pane loginPane = new Pane();
    public Label register = createMenuLabel("REGISTER HERE", Color.GREEN);

    public LoginScreen(Stage primaryStage){

        register.setFont(Font.font("Orbitron", 12));
        register.setTranslateX(237);
        register.setTranslateY(433);

        Image registerImage = new Image(getClass().getResource("/images/registerPage.png").toExternalForm());
        ImageView registerImageView = new ImageView(registerImage);
        registerImageView.setFitHeight(600);
        registerImageView.setFitWidth(605);
        registerImageView.setPreserveRatio(true);
        TextField username = new TextField();
        TextField password = new PasswordField();
        username.setTranslateX(223);
        username.setTranslateY(290);
        username.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        password.setTranslateX(223);
        password.setTranslateY(390);
        password.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        Label login = createMenuLabel("LOGIN", Color.WHITE);
        login.setFont(Font.font("Orbitron", 17));
        login.setTranslateX(265);
        login.setTranslateY(500);
        login.setOnMouseClicked(mouseEvent -> {
            MainScreen mainScreen = new MainScreen(primaryStage);
            login.setStyle("-fx-background-color: black;"); // or any color matching your app
            scene = mainScreen.getScene();
            primaryStage.setScene(mainScreen.getScene());
            System.out.println(username.getText());
        });

        loginPane.setStyle("-fx-background-color: black;");
        Image gameAssets = new Image(getClass().getResource("/images/loginPage.png").toExternalForm());
        ImageView gameAssetsImageView = new ImageView(gameAssets);
        gameAssetsImageView.setFitHeight(600);
        gameAssetsImageView.setFitWidth(605);
        gameAssetsImageView.setPreserveRatio(true);

        register.setOnMouseClicked(mouseEvent -> {
            RegisterScreen registerScreen = new RegisterScreen(primaryStage);
            registerScreen.registerPane.setStyle("-fx-background-color: black;");
            scene = registerScreen.getScene();
            primaryStage.setScene(scene);
            //jregisterPane.setVisible(true);
        });

        loginPane.getChildren().addAll(gameAssetsImageView, login, username, password, register);
        scene = new Scene(loginPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();

    }


    public Scene getScene() {
        return scene;

    }
}
