package com.example.javaproject2025.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

import static com.example.javaproject2025.ui.LoginScreen.createText;

public class WinnerScreen {

    private Scene scene;
    public Pane root;
    public Image bitOneWinScreen = new Image(getClass().getResource("/images/bit0WinScreen.png").toExternalForm());
    public Image bitTwoWinScreen = new Image(getClass().getResource("/images/bit1WinScreen.png").toExternalForm());
    public ImageView winScreenImageView;
    public String winnerBit;
    public String userOneUsername;
    public String userTwoUsername;
    public String winnerUsername;
    public int winnerScore;
    public String trackName;
    public Text scoreDisplay = createText("",24, Color.WHITE, 335, 319);
    public Text winnerUsernameDisplay = createText("",32, Color.LIGHTGRAY, 120, 225);

    public WinnerScreen(Stage primaryStage, String winnerBit, String winnerUsername , int score, String track, String userOneUsername, String userTwoUsername) throws SQLException, ClassNotFoundException {
        this.userOneUsername = userOneUsername;
        this.userTwoUsername = userTwoUsername;
        this.winnerBit = winnerBit;
        this.winnerUsername = winnerUsername;
        this.winnerScore = score;
        this.trackName = track;
        scoreDisplay.setVisible(false);
        sendScoreToDatabase();

        GameScreen gameScreen = new GameScreen(primaryStage, userOneUsername, userTwoUsername,"" );
        Label mainMenu = gameScreen.mainMenuButton;
        mainMenu.setTranslateX(250);
        mainMenu.setTranslateY(500);
        root = new Pane();
        if(winnerBit.equals("bit1")){
            winScreenImageView = new ImageView(bitOneWinScreen);
            winScreenImageView.setFitHeight(600);
            winScreenImageView.setFitWidth(605);
            winScreenImageView.setPreserveRatio(true);
            winnerUsernameDisplay.setText(userOneUsername + " wins!");
            winnerUsernameDisplay.setFill(Color.RED);
            root.getChildren().addAll(winScreenImageView, winnerUsernameDisplay);
        }

        if(winnerBit.equals("bit2")){
            winScreenImageView = new ImageView(bitTwoWinScreen);
            winScreenImageView.setFitHeight(600);
            winScreenImageView.setFitWidth(605);
            winScreenImageView.setPreserveRatio(true);
            winnerUsernameDisplay.setText(userTwoUsername + " wins!");
            winnerUsernameDisplay.setFill(Color.BLUE);
            root.getChildren().addAll(winScreenImageView, winnerUsernameDisplay);
        }

        root.getChildren().addAll(mainMenu, scoreDisplay);
        scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene); primaryStage.setTitle("Bit Racer"); primaryStage.show();
    }

    public void sendScore(String winnerString ,int score) {
        scoreDisplay.setText(Integer.toString(score));
        scoreDisplay.setVisible(true);
    }


    public void sendScoreToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bitracer", "root", "bitracerDB");
        Statement statement = connection.createStatement();
        PreparedStatement ps;
        String insertScoreStatement = "INSERT INTO scores (winner, score, track, loser) VALUES (?, ?, ?, ?)";
        ps = connection.prepareStatement(insertScoreStatement);
        ps.setString(1, winnerUsername);
        ps.setInt(2, winnerScore);
        ps.setString(3, trackName);
        ps.setString(4, winnerUsername.equals(userOneUsername) ? userTwoUsername : userOneUsername);
        ps.executeUpdate();

    }

    public Scene getScene() {
        return scene;

    }
}
