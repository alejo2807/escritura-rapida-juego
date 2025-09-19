package com.example.escriturarapidajuego;

import com.example.escriturarapidajuego.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Main application class for the Fast Typing Game.
 * Initializes the JavaFX application and provides global access to the primary stage.
 * Serves as the entry point for the application and manages the main window.
 *
 * @author David
 * @version 1.0
 */
public class Main extends Application {

    // Static reference to allow access to the primary stage from any controller
    private static Stage primaryStage;

    /**
     * Main entry point for the JavaFX application.
     * Initializes the primary stage and loads the home view.
     *
     * @param stage the primary stage for this application
     * @throws Exception if the FXML file cannot be loaded or parsed
     */
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage; // Store reference to the primary stage

        // Load the home view FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidajuego/view/home-view.fxml"));
        Scene scene = new Scene(loader.load());

        // Configure the primary stage
        primaryStage.setTitle("Escritura Rapida");
        primaryStage.setScene(scene);
        // Prevent window resizing for consistent UI experience
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Provides global access to the primary application stage.
     * This static method allows any controller to retrieve the main stage reference
     * for scene management and window operations.
     *
     * @return the primary stage of the application
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Main method launching the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}