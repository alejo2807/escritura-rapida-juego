package com.example.escriturarapidajuego.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {

    @FXML
    private TextField inputAnswer; // TextField de la ventana de juego


    //Home-view buttons
    @FXML private Button playButton;
    @FXML private Button helpButton;
    @FXML private Button exitGameButton;

    //Help-view button
    @FXML private Button returnToHomeButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // ---------------------------
    // Eventos de BOTONES
    // ---------------------------

    @FXML
    private void onPlay(ActionEvent event) throws IOException {
        cargarVista("/com/example/escriturarapidajuego/view/game-view.fxml");
    }

    @FXML
    private void onHelp(ActionEvent event) throws IOException {
        cargarVista("/com/example/escriturarapidajuego/view/help-view.fxml");
    }

    @FXML
    private void onExitGame(ActionEvent event) {
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    private void onReturnToHome(ActionEvent event) throws IOException {
        cargarVista("/com/example/escriturarapidajuego/view/home-view.fxml");
    }

    // ---------------------------
    // Eventos de TECLADO
    // ---------------------------

    // Enter en la ventana de juego → valida la palabra
    @FXML
    private void onEnterPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            String answer = inputAnswer.getText().trim();
            if (!answer.isEmpty()) {
                // Aquí llamas a GameRules para validar la respuesta
                boolean correctAnswer = validarRespuesta(answer);

                if (correctAnswer) {
                    cargarVista("/com/example/escriturarapidajuego/view/correct-view.fxml");
                } else {
                    cargarVista("/com/example/escriturarapidajuego/view/incorrect-view.fxml");
                }
            }
        }
    }

    // Escape en resultados → volver al menú inicial
    @FXML
    private void onEscapePressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            cargarVista("/com/example/escriturarapidajuego/images/view/home.fxml");
        }
    }

    // ---------------------------
    // Métodos auxiliares
    // ---------------------------

    private void cargarVista(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

        // Pasa el stage al nuevo controlador
        Object controller = loader.getController();
        if (controller instanceof GameController) {
            ((GameController) controller).setStage(stage);
        }

        stage.show();
    }

    private boolean validarRespuesta(String respuesta) {
        // TODO: conectar con GameRules para validar
        // Por ahora simulemos que la respuesta "hola" es correcta
        return respuesta.equalsIgnoreCase("hola");
    }
}
