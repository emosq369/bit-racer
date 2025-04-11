module com.example.javaproject2025 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.javaproject2025 to javafx.fxml;
    exports com.example.javaproject2025;
}