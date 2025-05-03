package com.example.javaproject2025.ui;

import java.sql.*;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.SQLException;
import static com.example.javaproject2025.ui.MainScreen.createMenuLabel;
import static com.example.javaproject2025.ui.MainScreen.createNeonGlow;
import static com.example.javaproject2025.ui.StageSelect.labelCreation;

public class LoginScreen {

    private Scene scene;
    public Pane loginPane = new Pane();
    public Label registerHere = createMenuLabel("REGISTER HERE", Color.GREEN);
    public Pane registerPane = new Pane();
    private Text winner;
    private int usersLoggedIn;
    private boolean duplicateFound = false;
    public Label createAccount = createMenuLabel("REGISTER ACCOUNT", Color.WHITE);
    public Label returnToLogin = labelCreation("RETURN TO LOGIN", Color.WHITE, 420, 22);
    public String userOne;
    public Label userOneLoggedIn;
    public Label userTwoLoggedIn;
    public String userTwo;
    public Text duplicateFoundDisplay = new Text("NAME TAKEN, TRY AGAIN");
    public Label startGame = labelCreation("START GAME", Color.WHITE, 235, 500);
    //    public Label accountCreatedDisplay = labelCreation("ACCOUNT CREATED", Color.GREEN, 235, 500);
    public Text accountCreatedDisplay = createText("ACCOUNT CREATED", 20, Color.GREEN, 185, 550);
    public Text userDoesNotExistDisplay = createText("USER DOES NOT EXIST", 20, Color.RED, 165, 550);
    public Text incorrectPasswordDisplay = createText("INCORRECT PASSWORD", 20, Color.RED, 165, 550);
    public Text shortUserNameDisplay = createText("USERNAME IS TOO SHORT", 20, Color.RED, 155, 550);

    public LoginScreen(Stage primaryStage) throws ClassNotFoundException, SQLException {
        shortUserNameDisplay.setVisible(false);
        incorrectPasswordDisplay.setVisible(false);
        userDoesNotExistDisplay.setVisible(false);
        duplicateFoundDisplay.setVisible(false);
        accountCreatedDisplay.setVisible(false);
        registerPane.setVisible(false);
        FadeTransition duplicateAccountFade = new FadeTransition(Duration.seconds(2), duplicateFoundDisplay); duplicateAccountFade.setFromValue(1.0); duplicateAccountFade.setToValue(0.0);
        FadeTransition accountCreatedFade = new FadeTransition(Duration.seconds(2), accountCreatedDisplay); accountCreatedFade.setFromValue(1.0); accountCreatedFade.setToValue(0.0);
        FadeTransition userDoesNotExistFade = new FadeTransition(Duration.seconds(2), userDoesNotExistDisplay); userDoesNotExistFade.setFromValue(1.0); userDoesNotExistFade.setToValue(0.0);
        FadeTransition incorrectPasswordFade = new FadeTransition(Duration.seconds(2), incorrectPasswordDisplay); incorrectPasswordFade.setFromValue(1.0); incorrectPasswordFade.setToValue(0.0);
        FadeTransition shortUserNameFade = new FadeTransition(Duration.seconds(2), shortUserNameDisplay); shortUserNameFade.setFromValue(1); shortUserNameFade.setToValue(0);
        registerHere.setFont(Font.font("Orbitron", 12));
        registerHere.setTranslateX(237);
        registerHere.setTranslateY(433);
        Image registerImage = new Image(getClass().getResource("/images/registerPage.png").toExternalForm());
        ImageView registerImageView = new ImageView(registerImage);
        registerImageView.setFitHeight(600);
        registerImageView.setFitWidth(605);
        registerImageView.setPreserveRatio(true);
        TextField userInputLogin = new TextField();
        TextField userInputPassword = new PasswordField();
        userInputLogin.setTranslateX(223);
        userInputLogin.setTranslateY(290);
        userInputLogin.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        userInputPassword.setTranslateX(223);
        userInputPassword.setTranslateY(390);
        userInputPassword.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        Label onLoginClick = createMenuLabel("LOGIN", Color.WHITE);
        onLoginClick.setFont(Font.font("Orbitron", 17));
        onLoginClick.setTranslateX(265);
        onLoginClick.setTranslateY(500);

        returnToLogin.setFont(Font.font("Orbitron", 14));
        createAccount.setTranslateX(215);
        createAccount.setTranslateY(500);
        createAccount.setFont(Font.font("Orbitron", 15));
        registerPane.setStyle("-fx-background-color: black;");
        TextField userInputRegisterUsername = new TextField();
        TextField userInputRegisterPassword = new PasswordField();
        userInputRegisterUsername.setTranslateX(223);
        userInputRegisterUsername.setTranslateY(290);
        userInputRegisterUsername.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        userInputRegisterPassword.setTranslateX(223);
        userInputRegisterPassword.setTranslateY(390);
        userInputRegisterPassword.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        Image registerGameAssets = new Image(getClass().getResource("/images/registerPage.png").toExternalForm());
        ImageView registerGameAssetsImageView = new ImageView(registerGameAssets);
        registerGameAssetsImageView.setFitHeight(600);
        registerGameAssetsImageView.setFitWidth(605);
        registerGameAssetsImageView.setPreserveRatio(true);


        // once both players logged in, add startGame to pane and allow
        // for game to start
        startGame.setOnMouseClicked(event -> {
            MainScreen newMainScreen = new MainScreen(primaryStage, userOne, userTwo);
            scene = newMainScreen.getScene();
            primaryStage.setScene(scene);
        });

        // handling when user clicks log in
        onLoginClick.setOnMouseClicked(mouseEvent -> {
            try {
                if (validateLogin(userInputLogin.getText(), userInputPassword.getText()) == 1) {
                    System.out.println("IT WORKED");
                } else if (validateLogin(userInputLogin.getText(), userInputPassword.getText()) == 0) {
                    System.out.println("IT NOT WORKED");
                    userDoesNotExistDisplay.setVisible(true);
                    userDoesNotExistFade.play();
                } else if (validateLogin(userInputLogin.getText(), userInputPassword.getText()) == -1) {
                    System.out.println("OK USER EXISTS BUT ACCOUNT DOES NOT");
                    incorrectPasswordDisplay.setVisible(true);
                    incorrectPasswordFade.play();
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (usersLoggedIn == 2) {
                    onLoginClick.setVisible(false);
                    loginPane.getChildren().add(startGame);
                    loginPane.getChildren().remove(onLoginClick);
                }

            }

        });

        loginPane.setStyle("-fx-background-color: black;");

        Image gameAssets = new Image(getClass().getResource("/images/loginPage.png").toExternalForm());
        ImageView gameAssetsImageView = new ImageView(gameAssets);
        gameAssetsImageView.setFitHeight(600);
        gameAssetsImageView.setFitWidth(605);
        gameAssetsImageView.setPreserveRatio(true);
        loginPane.getChildren().addAll(gameAssetsImageView, onLoginClick, userInputLogin, userInputPassword, registerHere, userDoesNotExistDisplay, incorrectPasswordDisplay);
        registerPane.getChildren().addAll(registerGameAssetsImageView, createAccount, userInputRegisterUsername, userInputRegisterPassword, returnToLogin, duplicateFoundDisplay, accountCreatedDisplay, shortUserNameDisplay);
        registerHere.setOnMouseClicked(mouseEvent -> {
            loginPane.setDisable(true);
            registerPane.setDisable(false);
            registerPane.setVisible(true);
            scene.setRoot(registerPane);
        });

        createAccount.setOnMouseClicked(mouseEvent -> {
            System.out.println(usersLoggedIn);
            try {
                int result = createAccount(userInputRegisterUsername.getText(), userInputPassword.getText());

                if (result == 0) {
                    accountCreatedDisplay.setVisible(true);
                    accountCreatedFade.play();

                } else if (result == 1) {
                    System.out.println("SQLIntegrityConstraintViolationException, username already exists in database.");
                    duplicateAccountFade.play();

                } else if (result == 2) {
                    shortUserNameDisplay.setVisible(true);
                    shortUserNameFade.play();
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        });

        returnToLogin.setOnMouseClicked(mouseEvent -> {
            registerPane.setDisable(true);
            loginPane.setDisable(false);
            loginPane.setVisible(true);
            scene.setRoot(loginPane);
        });

        scene = new Scene(loginPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();

    }

    public Scene getScene() {
        return scene;
    }

    public int validateLogin(String username, String password) throws ClassNotFoundException, SQLException {
        String userNameFromDatabase;
        String userPasswordFromDatabase;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bitracer", "root", "bitracerDB");
        Statement statement = connection.createStatement();
        ResultSet databaseQuery = statement.executeQuery(
                "SELECT username, password FROM users WHERE username = '" + username + "'"
        );

        if (databaseQuery.next()) {
            System.out.println("WE ARE QUERYING RIGHT NOW");
            userNameFromDatabase = databaseQuery.getString(1);
            userPasswordFromDatabase = databaseQuery.getString(2);
//            System.out.println("found user -> " + userNameFromDatabase);
//            System.out.println("found user & their password is -> " + userPasswordFromDatabase);
//            System.out.println("username already exists : " + dupliactedUsername);
            // TODO : implement two users not logging in with the same username and password, aka same account.
            if (password.equals(userPasswordFromDatabase)) {
                usersLoggedIn++;
                if (usersLoggedIn == 1) {
                    userOne = userNameFromDatabase;
                    userOneLoggedIn = labelCreation("Player One Logged In", Color.RED, 45, 50);
                    loginPane.getChildren().add(userOneLoggedIn);
                } else if (usersLoggedIn == 2) {
                    userTwo = userNameFromDatabase;
                    userTwoLoggedIn = labelCreation("Player Two Logged In", Color.BLUE, 350, 50);
                    loginPane.getChildren().add(userTwoLoggedIn);
                }
                // if user signed in correctly, return one.
                return 1;
            } else {
                // if the user exists but the password is wrong, return -1.
                System.out.println("OK ACCOUNT EXISTS BUT WRONG PASSWORD");
                return -1;
            }
        }

        // if the user does not exist in the database, return 0.
        return 0;
    }

    public Text createText(String text, int fontSize, Color color, int x, int y) {
        Text createText = new Text(text);
        DropShadow strongGlow = createNeonGlow(color);
        strongGlow.setRadius(40);
        createText.setEffect(strongGlow);
        createText.setFont(Font.font("Orbitron", fontSize));
        createText.setFill(color);
        createText.setTranslateX(x);
        createText.setTranslateY(y);
        return createText;
    }

    public int createAccount(String usernameFromInput, String passwordFromInput) throws ClassNotFoundException, SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bitracer", "root", "bitracerDB");

            Statement statement = connection.createStatement();

            PreparedStatement ps;

            // validate length
            if(usernameFromInput.length() > 0){
                String userInput = "insert into users values('" + usernameFromInput + "','" + passwordFromInput + "')";

                ps = connection.prepareStatement(userInput);
                ps.executeUpdate();
            }
            else{
                // return if user does not enter anything into username field
                return 2;
            }


        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                // if duplicate found
                duplicateFoundDisplay.setVisible(true);
                duplicateFoundDisplay.setFont(Font.font("Orbitron", 20));
                duplicateFoundDisplay.setFill(Color.RED);
                duplicateFoundDisplay.setX(165);
                duplicateFoundDisplay.setY(550);
                return 1;
            }
        }

        // if no duplicate found, insert into table
        return 0;
    }
}

