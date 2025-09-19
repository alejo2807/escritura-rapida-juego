package com.example.escriturarapidajuego.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Controller for feedback modal windows (correct/incorrect responses).
 * Handles the closure of feedback popup windows without affecting the main game.
 * Designed to work with both correct-view.fxml and incorrect-view.fxml.
 *
 * @author David
 * @version 1.0
 */
public class FeedbackController {

    /**
     * Handles the return to game action from feedback modal windows.
     * Closes the current feedback window without affecting the main game window.
     * This method is typically connected to a "Continue" or "Close" button.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void onReturnToGame(ActionEvent event) {
        // Get the current window from the button that was clicked
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Close only this window (the feedback modal) without affecting the main game
        currentWindow.close();
    }
}