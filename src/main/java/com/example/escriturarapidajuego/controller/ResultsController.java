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

/**
 * Controller for the game results screen.
 * Displays final statistics after game completion and handles navigation back to the main menu.
 * Listens for ESCAPE key press to return to the home screen.
 *
 * @author David
 * @version 1.0
 */
public class ResultsController {

    @FXML private Label scoreLabel;
    @FXML private Label correctLevelLabel;
    @FXML private Label incorrectLevelLabel;

    /**
     * Initializes the controller after FXML loading.
     * Forces focus to the root container to ensure key events are captured.
     */
    @FXML
    public void initialize() {
        // Force focus to the root container to ensure key events are captured
        Platform.runLater(() -> {
            scoreLabel.getScene().getRoot().requestFocus();
        });
    }

    /**
     * Handles ESCAPE key press events to return to the main menu.
     * Consumes the event to prevent propagation to other handlers.
     *
     * @param event the key event containing key code information
     */
    @FXML
    private void onEscapePressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            returnToHome();
            event.consume(); // Prevent event propagation
        }
    }

    /**
     * Updates the results display with player statistics.
     *
     * @param score the final score achieved by the player
     * @param correct the number of correctly completed levels
     * @param incorrect the number of incorrectly completed levels
     */
    public void updateResults(int score, int correct, int incorrect) {
        scoreLabel.setText("Puntaje: " + score);
        correctLevelLabel.setText("Aciertos: " + correct);
        incorrectLevelLabel.setText("Errores: " + incorrect);
    }

    /**
     * Returns to the main menu screen.
     * Loads the home view and sets it as the current scene.
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