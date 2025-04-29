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

import static com.example.javaproject2025.ui.MainScreen.createMenuLabel;
import static com.example.javaproject2025.ui.StageSelect.labelCreation;

public class RegisterScreen {

    private Scene scene;
    public Pane registerPane = new Pane();
    private Text winner;
    public Label submit = createMenuLabel("REGISTER ACCOUNT", Color.WHITE);
    public Label returnToLogin = labelCreation("RETURN TO LOGIN", Color.WHITE, 420, 22);

    public RegisterScreen(Stage primaryStage){
        returnToLogin.setFont(Font.font("Orbitron", 14));
        submit.setTranslateX(215);
        submit.setTranslateY(500);
        submit.setFont(Font.font("Orbitron", 15));
        registerPane.setStyle("-fx-background-color: black;");
        TextField username = new TextField();
        TextField password = new PasswordField();
        username.setTranslateX(223);
        username.setTranslateY(290);
        username.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        password.setTranslateX(223);
        password.setTranslateY(390);
        password.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        Image gameAssets = new Image(getClass().getResource("/images/registerPage.png").toExternalForm());
        ImageView gameAssetsImageView = new ImageView(gameAssets);
        gameAssetsImageView.setFitHeight(600);
        gameAssetsImageView.setFitWidth(605);
        gameAssetsImageView.setPreserveRatio(true);
        Text winnerDisplay = new Text("Winner");
        winnerDisplay.setStyle("-fx-font-size: 20px;");
        winnerDisplay.setFill(Color.WHITE);
        winnerDisplay.setX(5);
        winnerDisplay.setY(5);


        submit.setOnMouseClicked(mouseEvent -> {
            LoginScreen loginScreen = new LoginScreen(primaryStage);
            scene = loginScreen.getScene();
            primaryStage.setScene(scene);
        });

        returnToLogin.setOnMouseClicked(mouseEvent -> {
            LoginScreen loginScreen = new LoginScreen(primaryStage);
            scene = loginScreen.getScene();
            primaryStage.setScene(scene);
        });


        registerPane.getChildren().addAll(winnerDisplay, gameAssetsImageView, username, password, submit, returnToLogin);
        scene = new Scene(registerPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();

    }


    public Scene getScene() {
        return scene;

    }
}
