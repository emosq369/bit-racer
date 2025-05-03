package com.example.javaproject2025.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class WinnerScreen {

    private Scene scene;
    public Pane root;
    public Image bit0WinScreen = new Image(getClass().getResource("/images/bit0WinScreen.png").toExternalForm());
    public Image bit1WinScreen = new Image(getClass().getResource("/images/bit1WinScreen.png").toExternalForm());
    public ImageView winScreenImageView;
    public String winnerBit;
    public String userOneUsername;
    public String userTwoUsername;
    public String winnerUsername;
    public int winnerScore;
    public String trackName;

    public WinnerScreen(Stage primaryStage, String winnerBit, String winnerUsername , int score, String track, String userOneUsername, String userTwoUsername) throws SQLException, ClassNotFoundException {
        this.userOneUsername = userOneUsername;
        this.userTwoUsername = userTwoUsername;
        this.winnerBit = winnerBit;
        this.winnerUsername = winnerUsername;
        this.winnerScore = score;
        this.trackName = track;
        System.out.println("CONGRATS TO " + winnerUsername + " your score was" + score + " On teh track " + track);
        sendScoreToDatabase();
        GameScreen gameScreen = new GameScreen(primaryStage, userOneUsername, userTwoUsername,"" );
        Label mainMenu = gameScreen.mainMenuButton;
        mainMenu.setTranslateX(250);
        mainMenu.setTranslateY(500);
        root = new Pane();
        if(winnerBit.equals("bit1")){
            winScreenImageView = new ImageView(bit0WinScreen);
            winScreenImageView.setFitHeight(600);
            winScreenImageView.setFitWidth(605);
            winScreenImageView.setPreserveRatio(true);
            root.getChildren().add(winScreenImageView);
        }
        if(winnerBit.equals("bit2")){
            winScreenImageView = new ImageView(bit1WinScreen);
            winScreenImageView.setFitHeight(600);
            winScreenImageView.setFitWidth(605);
            winScreenImageView.setPreserveRatio(true);
            root.getChildren().add(winScreenImageView);
        }

        root.setStyle("-fx-background-color: black;");
        root.getChildren().add(mainMenu);
        scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bit Racer");
        primaryStage.show();
    }

    public void sendScore(String winnerString ,int score) {
//        if (winnerString.equals("BIT 1")) {
//            winnerBit = "Bit 1";
//        }
//        else if (winnerString.equals("BIT 2")){
//            winnerBit = "Bit 2";
//        }
        Text displayScore = new Text(Integer.toString(score));
        displayScore.setFont(Font.font("Orbitron", FontWeight.BOLD, 32));
        displayScore.setX(335);
        displayScore.setY(275);
        displayScore.setFill(Color.WHITE);
        root.getChildren().add(displayScore);

    }


    public void sendScoreToDatabase() throws ClassNotFoundException, SQLException {
        String userNameFromDatabase;
        String userPasswordFromDatabase;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bitracer", "root", "Gedobrivagiju30.");
        Statement statement = connection.createStatement();
        PreparedStatement ps = null;
        String insertScoreStatement = "insert into scores values('" + winnerUsername + "','" + winnerScore + "','" + trackName + "')";
        ps = connection.prepareStatement(insertScoreStatement);
        ps.executeUpdate();
    }

    public Scene getScene() {
        return scene;

    }
}
