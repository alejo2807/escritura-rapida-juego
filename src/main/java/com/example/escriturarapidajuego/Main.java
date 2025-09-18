package com.example.escriturarapidajuego;

import com.example.escriturarapidajuego.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidajuego/view/home-view.fxml"));
        Scene scene = new Scene(loader.load());

        //Create the controller and passing it to the stage
        Controller controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Escritura Rapida");
        primaryStage.setScene(scene);

        //We dont allow a redimension of thw window
        primaryStage.setResizable(false);

        primaryStage.show();

    }
}
