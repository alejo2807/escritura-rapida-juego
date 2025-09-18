package com.example.escriturarapidajuego.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultsController {
    @FXML
    private Label scoreLabel;

    @FXML
    private Label correctLevelLabel;

    @FXML
    private Label incorrectLevelLabel;

    private Stage stage;

    public void updateResults(int score, int correct, int incorrect) {
        scoreLabel.setText("Puntaje: " + score);
        correctLevelLabel.setText("Aciertos: " + correct);
        incorrectLevelLabel.setText("Errores: " + incorrect);
    }

    //method to detect the esc key in results view and return to home view
    @FXML
    private void onEscapePressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            comeBackToHome();
        }
    }

    private void comeBackToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidajuego/view/home-view.fxml"));
            Parent root = loader.load();

            Stage homeStage = new Stage();
            homeStage.setScene(new Scene(root));
            homeStage.setTitle("Escritura Rápida");
            homeStage.setResizable(false);
            homeStage.show();

            // Cerrar esta ventana actual
            Stage currentStage = (Stage) scoreLabel.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarVista(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        // Pasa el stage al nuevo controlador
        Object controller = loader.getController();
        if (controller instanceof Controller) {
            ((Controller) controller).setStage(stage);
        }

        stage.setResizable(false);

        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}