package com.example.escriturarapidajuego.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;


public class FeedbackController {

    @FXML
    private void onReturnToGame(ActionEvent event) {
        // Obtener la ventana actual del botón que se clickeó
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cerrar solo esta ventana (la modal de feedback)
        currentWindow.close();
    }
}
