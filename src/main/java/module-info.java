module com.example.javaproject2025 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.management;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.javaproject2025 to javafx.fxml;
    exports com.example.javaproject2025;
    exports com.example.javaproject2025.game;
    opens com.example.javaproject2025.game to javafx.fxml;
    exports com.example.javaproject2025.ui;
    opens com.example.javaproject2025.ui to javafx.fxml;
}