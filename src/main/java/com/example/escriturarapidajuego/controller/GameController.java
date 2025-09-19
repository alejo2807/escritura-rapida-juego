package com.example.escriturarapidajuego.controller;

import com.example.escriturarapidajuego.Main;
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

public class GameController {

    @FXML
    private Label levelLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label wordLabel;

    @FXML
    private TextField inputField;

    private Game game; // Model of the Game

    @FXML
    public void initialize() {
        // Crear el juego, pasándole el label del tiempo
        game = new Game(timeLabel, this);

        // Configurar vista inicial
        updateView();

        // Un listener para ENTER
        inputField.setOnKeyPressed(this::onEnterPressed);
    }

    @FXML
    private void onEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleEnter();
            event.consume();
        }
    }

    private void endGame() {
        if (game != null && game.getTimer() != null) {
            game.getTimer().stopTimer();
        }
        if (game != null) {
            game.showResults();
            showGameOver();
        }
    }

    // Cuando el usuario presiona ENTER
    private void handleEnter() {
        String typedWord = inputField.getText();

        if (!typedWord.isEmpty()) {
            boolean isCorrect = game.playTurn(typedWord);


            if (isCorrect) {
                System.out.println("correct");
                //in case the answer is correct, we show the correct view
                showFeedback(isCorrect);

            } else {
                System.out.println("incorrect");
                //in case the answer is incorrect, we show the incorrect view
                showFeedback(isCorrect);
            }

            inputField.clear();
            updateView();
        }
    }


    // Show feedback and then return to game
    private void showFeedback(boolean isCorrectAnswer) {
        try {
            String fxmlFile = isCorrectAnswer ? "/com/example/escriturarapidajuego/view/correct-view.fxml" : "/com/example/escriturarapidajuego/view/incorrect-view.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // OBTENER EL CONTROLADOR
            FeedbackController feedbackController = loader.getController();

            Stage feedbackStage = new Stage();
            feedbackStage.setScene(new Scene(root));
            feedbackStage.setTitle(isCorrectAnswer ? "Correcto" : "Incorrecto");
            feedbackStage.setResizable(false);
            feedbackStage.show();

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


    public void timeIsUp()
    {
        // Tomar lo que el usuario haya escrito hasta el momento
        String userInput = inputField.getText();

        if (userInput.isEmpty()) {
            // Si no escribió nada - Game Over
            System.out.println("Tiempo agotado: No ingresó nada");
            showFeedback(false); // at first, we show incorrect answer screen here

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> showGameOver());
            pause.play();
        }
        else {
            boolean isCorrect = game.playTurn(userInput);

            if (isCorrect)
            {
                // if the user was corect, we advance to the next level
                System.out.println("¡Acertó justo a tiempo! Pasando al siguiente nivel");
                showFeedback(true); // we show positive feedback here

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
            }
            else {
                // Escribió algo pero incorrecto - Game Over
                System.out.println("Tiempo agotado: Respuesta incorrecta");
                showFeedback(false);
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> showGameOver());
                pause.play();
                endGame();
            }
        }
    }

    private void showGameOver()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidajuego/view/results-view.fxml"));
            Parent root = loader.load();

            // Obtener el controlador
            ResultsController resultsController = loader.getController();

            resultsController.updateResults(
                game.getPlayer().getScore(),
                game.getPlayer().getCorrectLevels(),
                game.getPlayer().getIncorrectLevels()
            );

            Stage primaryStage = Main.getPrimaryStage();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Resultados");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
