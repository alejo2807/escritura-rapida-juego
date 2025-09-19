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

/**
 * Main game controller that manages the gameplay logic, user input, and UI updates.
 * Handles word validation, timer management, feedback display, and game state transitions.
 * Coordinates between the game model and the JavaFX UI components.
 *
 * @author David
 * @version 1.0
 */
public class GameController {

    @FXML
    private Label levelLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label wordLabel;
    @FXML
    private TextField inputField;

    private Game game; // Game model instance

    /**
     * Initializes the game controller after FXML loading.
     * Sets up the game model, initial UI state, and event listeners.
     */
    @FXML
    public void initialize() {
        // Create the game instance with time label and controller reference
        game = new Game(timeLabel, this);

        // Configure initial view
        updateView();

        // Set up listener for ENTER key in the input field
        inputField.setOnKeyPressed(this::onEnterPressed);
    }

    /**
     * Handles ENTER key press events in the input field.
     * Processes the user's answer when ENTER is pressed.
     *
     * @param event the key event containing key code information
     */
    @FXML
    private void onEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleEnter();
            event.consume(); // Prevent event propagation
        }
    }

    /**
     * Ends the game by stopping the timer and showing results.
     * Called when time expires or game completion conditions are met.
     */
    private void endGame() {
        if (game != null && game.getTimer() != null) {
            game.getTimer().stopTimer();
        }
        if (game != null) {
            game.showResults(); // Show results in console
            showGameOver();     // Show game over screen
        }
    }

    /**
     * Processes the user's input when ENTER is pressed.
     * Validates the answer and updates game state accordingly.
     */
    private void handleEnter() {
        String typedWord = inputField.getText();

        if (!typedWord.isEmpty()) {
            boolean isCorrect = game.playTurn(typedWord);

            if (isCorrect) {
                System.out.println("correct");
                showFeedback(isCorrect); // Show correct feedback
            } else {
                System.out.println("incorrect");
                showFeedback(isCorrect); // Show incorrect feedback
            }

            inputField.clear();
            updateView(); // Refresh UI with new game state
        }
    }

    /**
     * Shows feedback modal window for correct or incorrect answers.
     *
     * @param isCorrectAnswer true for correct answer feedback, false for incorrect
     */
    private void showFeedback(boolean isCorrectAnswer) {
        try {
            String fxmlFile = isCorrectAnswer ?
                    "/com/example/escriturarapidajuego/view/correct-view.fxml" :
                    "/com/example/escriturarapidajuego/view/incorrect-view.fxml";

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Get the feedback controller
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

    /**
     * Updates the UI labels with current game state information.
     */
    private void updateView() {
        levelLabel.setText("Nivel: " + game.getCurrentLevel());
        wordLabel.setText("Escribe: " + game.getCurrentWord());
        timeLabel.setText("Tiempo: " + game.getTimer().getTimeLeft());
    }

    /**
     * Handles the time expiration event from the game timer.
     * Processes any partial input and determines game outcome.
     */
    public void timeIsUp() {
        String userInput = inputField.getText();

        if (userInput.isEmpty()) {
            // No input provided - Game Over
            System.out.println("Tiempo agotado: No ingresó nada");
            showFeedback(false); // Show incorrect feedback

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> showGameOver());
            pause.play();
        } else {
            boolean isCorrect = game.playTurn(userInput);

            if (isCorrect) {
                // Correct answer at the last moment - proceed to next level
                System.out.println("¡Acertó justo a tiempo! Pasando al siguiente nivel");
                showFeedback(true); // Show positive feedback

                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> {
                    inputField.clear();
                    updateView();
                    // Restart timer for the next level
                    if (game != null) {
                        game.getTimer().startTimer(game.getCurrentLevel());
                    }
                });
                pause.play();
            } else {
                // Incorrect answer - Game Over
                System.out.println("Tiempo agotado: Respuesta incorrecta");
                showFeedback(false);
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> showGameOver());
                pause.play();
                endGame();
            }
        }
    }

    /**
     * Shows the game over screen with final results.
     * Transitions to the results view displaying player statistics.
     */
    private void showGameOver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidajuego/view/results-view.fxml"));
            Parent root = loader.load();

            // Get the results controller and update with final statistics
            ResultsController resultsController = loader.getController();
            resultsController.updateResults(
                game.getPlayer().getScore(),
                game.getPlayer().getCorrectLevels(),
                game.getPlayer().getIncorrectLevels()
            );

            // Switch to results screen using the primary stage
            Stage primaryStage = Main.getPrimaryStage();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Resultados");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}