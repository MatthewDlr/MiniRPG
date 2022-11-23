module com.example.rpgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rpgui to javafx.fxml;
    exports com.example.rpgui;
}