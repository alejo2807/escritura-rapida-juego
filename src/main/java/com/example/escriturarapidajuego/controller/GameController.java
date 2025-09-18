package com.example.escriturarapidajuego.controller;

import com.example.escriturarapidajuego.model.Game;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class GameController {

    @FXML
    private Label levelLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label wordLabel;

    @FXML
    private TextField inputField;

    private Game game; // Modelo del juego

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        // Crear el juego, pasándole el label del tiempo
        game = new Game(timeLabel, this);

        // Configurar vista inicial
        updateView();

        // Un solo listener para ENTER y ESCAPE
        inputField.setOnKeyPressed(this::onKeyPressed);
    }

    @FXML
    // Maneja tanto ENTER como ESCAPE
    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleEnter();
        } else if (event.getCode() == KeyCode.ESCAPE) {
            // Lógica de finalización del juego
            game.showResults();
        }
    }

    // Cuando el usuario presiona ENTER
    private void handleEnter() {
        String typedWord = inputField.getText();

        if (!typedWord.isEmpty()) {
            boolean correct = game.playTurn(typedWord);


            if (correct) {
                System.out.println("correct");
                //in case the answer is correct, we show the correct view
                showFeedback(correct);

            } else {
                System.out.println("incorrect");
                //in case the answer is incorrect, we show the incorrect view
                showFeedback(correct);

            }

            inputField.clear();
            updateView();
        }
    }


    // Show feedback and then return to game
    private void showFeedback(boolean respuestaCorrecta) {
        try {
            String fxmlFile = respuestaCorrecta ? "/com/example/escriturarapidajuego/view/correct-view.fxml" : "/com/example/escriturarapidajuego/view/incorrect-view.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // OBTENER EL CONTROLADOR
            FeedbackController feedbackController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(respuestaCorrecta ? "Correcto" : "Incorrecto");
            stage.setResizable(false);

            // ¡IMPORTANTE! Pasar el stage al controlador
            feedbackController.setStage(stage);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Refresca los labels con info del modelo
    private void updateView() {
        levelLabel.setText("Nivel: " + game.getCurrentLevel());
        wordLabel.setText("Escribe: " + game.getCurrentWord());
        timeLabel.setText("Tiempo: " + game.getTimer().getTimeLeft());
    }


    public void timeIsUp() {
        // Tomar lo que el usuario haya escrito hasta el momento
        String userInput = inputField.getText();

        if (userInput.isEmpty()) {
            // Si no escribió nada - Game Over
            System.out.println("Tiempo agotado: No ingresó nada");
            showFeedback(false); // ← Primero mostrar feedback de incorrecto
            // Esperar un poco y luego mostrar game over
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> showGameOver());
            pause.play();
        } else
        {
            // Validar si lo que escribió es correcto
            boolean isCorrect = game.playTurn(userInput);

            if (isCorrect) {
                // if the user was corect, we advance to the next level
                System.out.println("¡Acertó justo a tiempo! Pasando al siguiente nivel");
                showFeedback(true); // ← Primero mostrar feedback de correcto

                // Esperar un poco y luego continuar
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> {
                    inputField.clear();
                    updateView();
                    // Reiniciar timer para el siguiente nivel
                    if (game != null) {
                        game.getTimer().startTimer(game.getCurrentLevel());
                    }
                });
                pause.play();


            } else {
                // Escribió algo pero incorrecto - Game Over
                System.out.println("Tiempo agotado: Respuesta incorrecta");
                showFeedback(false); // ← FALTABA ESTA LÍNEA
                // Esperar y luego mostrar game over
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> showGameOver());
                pause.play();
            }
        }
    }

    private void showGameOver() {
        game.showResults();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidajuego/view/results-view.fxml"));
            Parent root = loader.load();

            // Obtener el controlador
            ResultsController controller = loader.getController();

            // ELIMINA esta línea: controller.setStage(stage); //

            // Crear NUEVO stage para resultados
            Stage resultsStage = new Stage();
            resultsStage.setScene(new Scene(root));
            resultsStage.setTitle("Resultados");
            resultsStage.setResizable(false);

            // Pasar el NUEVO stage al controlador
            controller.setStage(resultsStage);

            // Pasar los datos
            controller.updateResults(
                    game.getPlayer().getScore(),
                    game.getPlayer().getCorrectLevels(),
                    game.getPlayer().getIncorrectLevels()
            );

            resultsStage.show();

            Platform.runLater(() -> {
                root.requestFocus();
            });

            // Cerrar ventana actual
            Stage currentStage = (Stage) inputField.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
