package com.example.escriturarapidajuego.controller;

import com.example.escriturarapidajuego.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    //Home-view buttons
    @FXML
    private Button playButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button exitGameButton;

    //Help-view button
    @FXML
    private Button returnToHomeButton;


    @FXML
    private void onPlay(ActionEvent event) throws IOException {
        loadView("/com/example/escriturarapidajuego/view/game-view.fxml");
    }

    @FXML
    private void onHelp(ActionEvent event) throws IOException {
        loadView("/com/example/escriturarapidajuego/view/help-view.fxml");
    }

    @FXML
    private void onExitGame(ActionEvent event) {
        Stage stage = Main.getPrimaryStage();
        stage.close();
    }

    @FXML
    private void onReturnToHome(ActionEvent event) throws IOException {
        loadView("/com/example/escriturarapidajuego/view/home-view.fxml");
    }

    //Here we use the static stage defined in Main
    private void loadView(String fxmlPath)throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());

        Stage primaryStage = Main.getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
