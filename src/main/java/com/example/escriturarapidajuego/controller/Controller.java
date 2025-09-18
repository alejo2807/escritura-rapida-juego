package com.example.escriturarapidajuego.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {


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
    // Métodos auxiliares
    // ---------------------------

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


}
