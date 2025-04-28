package com.example.javaproject2025.ui;

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

import static com.example.javaproject2025.ui.MainScreen.createNeonGlow;

public class LoginScreen{

    private Scene scene;
    public Pane loginPane = new Pane();
    public Pane registerPane = new Pane();
    private Text winner;
    public Label register = new Label("REGSTER HERE");
    public Label registerAccount = new Label("REGISTER ACCOUNT");

    public LoginScreen(Stage primaryStage){
        registerPane.setStyle("-fx-background-color: black;");
        register.setFont(Font.font("Orbitron", 16));
        register.setTextFill(Color.LIGHTGRAY);
        register.setEffect(createNeonGlow(Color.LIGHTGRAY));
        register.setTranslateX(480);
        register.setTranslateY(10);
        Image registerImage = new Image(getClass().getResource("/images/registerPage.png").toExternalForm());
        ImageView registerImageView = new ImageView(registerImage);
        registerImageView.setFitHeight(600);
        registerImageView.setFitWidth(605);
        registerImageView.setPreserveRatio(true);
        TextField username = new TextField();
        TextField password = new PasswordField();
        registerPane.getChildren().addAll(registerImageView, username, password);
        username.setTranslateX(223);
        username.setTranslateY(290);
        username.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        password.setTranslateX(223);
        password.setTranslateY(390);
        password.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        GameScreen gameScreen = new GameScreen(primaryStage);
        Label login = gameScreen.mainMenuButton;
        login.setTranslateX(250);
        login.setTranslateY(500);
        loginPane.setStyle("-fx-background-color: black;");
        Image gameAssets = new Image(getClass().getResource("/images/loginPage.png").toExternalForm());
        ImageView gameAssetsImageView = new ImageView(gameAssets);
        gameAssetsImageView.setFitHeight(600);
        gameAssetsImageView.setFitWidth(605);
        gameAssetsImageView.setPreserveRatio(true);
        Text winnerDisplay = new Text("Winner");
        winnerDisplay.setStyle("-fx-font-size: 20px;");
        winnerDisplay.setFill(Color.WHITE);
        winnerDisplay.setX(5);
        winnerDisplay.setY(5);
        register.setOnMouseClicked(mouseEvent -> {
            loginPane.setVisible(false);
            scene = new Scene(registerPane);
            primaryStage.setScene(scene);
            //jregisterPane.setVisible(true);
        });
        loginPane.getChildren().addAll(winnerDisplay, gameAssetsImageView, login, username, password, register);
        scene = new Scene(loginPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();

    }


    public Scene getScene() {
        return scene;

    }
}
