package com.example.escriturarapidajuego.controller;

import com.example.escriturarapidajuego.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main controller for the application's navigation and menu management.
 * Handles all user interactions from the home screen and help screen.
 * Manages scene transitions between different views of the application.
 *
 * @author David
 * @version 1.0
 */
public class Controller {

    // Home-view buttons
    @FXML
    private Button playButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button exitGameButton;

    // Help-view button
    @FXML
    private Button returnToHomeButton;

    /**
     * Handles the Play button action from the home screen.
     * Loads the game view to start a new typing game session.
     *
     * @param event the action event triggered by the button click
     * @throws IOException if the game view FXML file cannot be loaded
     */
    @FXML
    private void onPlay(ActionEvent event) throws IOException {
        loadView("/com/example/escriturarapidajuego/view/game-view.fxml");
    }

    /**
     * Handles the Help button action from the home screen.
     * Loads the help view with game instructions and rules.
     *
     * @param event the action event triggered by the button click
     * @throws IOException if the help view FXML file cannot be loaded
     */
    @FXML
    private void onHelp(ActionEvent event) throws IOException {
        loadView("/com/example/escriturarapidajuego/view/help-view.fxml");
    }

    /**
     * Handles the Exit Game button action from the home screen.
     * Closes the application window and terminates the program.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void onExitGame(ActionEvent event) {
        Stage stage = Main.getPrimaryStage();
        stage.close();
    }

    /**
     * Handles the Return to Home button action from the help screen.
     * Loads the home view to return to the main menu.
     *
     * @param event the action event triggered by the button click
     * @throws IOException if the home view FXML file cannot be loaded
     */
    @FXML
    private void onReturnToHome(ActionEvent event) throws IOException {
        loadView("/com/example/escriturarapidajuego/view/home-view.fxml");
    }

    /**
     * Loads a FXML view and sets it as the current scene.
     * Uses the static primary stage from Main class for consistent window management.
     *
     * @param fxmlPath the path to the FXML file to load
     * @throws IOException if the FXML file cannot be loaded or parsed
     */
    private void loadView(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());

        Stage primaryStage = Main.getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}