module com.example.escriturarapidajuego {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports com.example.escriturarapidajuego;

    opens com.example.escriturarapidajuego to javafx.fxml;
    opens com.example.escriturarapidajuego.controller to javafx.fxml;
}