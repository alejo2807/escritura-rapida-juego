module com.example.escriturarapidajuego {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.escriturarapidajuego to javafx.fxml;
    exports com.example.escriturarapidajuego;
}