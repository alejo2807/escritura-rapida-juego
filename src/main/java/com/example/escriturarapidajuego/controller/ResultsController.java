package com.example.escriturarapidajuego.controller;

import com.example.escriturarapidajuego.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class ResultsController {
    @FXML private Label scoreLabel;
    @FXML private Label correctLevelLabel;
    @FXML private Label incorrectLevelLabel;

    /**
     * Se ejecuta al inicializar el controlador
     */
    @FXML
    public void initialize() {
        // Forzar el foco para que capture las teclas
        Platform.runLater(() -> {
            scoreLabel.getScene().getRoot().requestFocus();
        });
    }

    /**
     * Maneja la tecla ESCAPE para volver al menú principal
     */
    @FXML
    private void onEscapePressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            returnToHome();
            event.consume(); // Importante: evitar propagación
        }
    }

    /**
     * Actualiza los resultados en la pantalla
     */
    public void updateResults(int score, int correct, int incorrect) {
        scoreLabel.setText("Puntaje: " + score);
        correctLevelLabel.setText("Aciertos: " + correct);
        incorrectLevelLabel.setText("Errores: " + incorrect);
    }

    /**
     * Vuelve al menú principal
     */
    private void returnToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidajuego/view/home-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage primaryStage = Main.getPrimaryStage();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}