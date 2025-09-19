package com.example.escriturarapidajuego;

import com.example.escriturarapidajuego.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {

    //static reference to get the stage from any other controller
    private static Stage primaryStage;


    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapidajuego/view/home-view.fxml"));
        Scene scene = new Scene(loader.load());


        primaryStage.setTitle("Escritura Rapida");
        primaryStage.setScene(scene);
        //We dont allow a redimension of thw window
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    //This getter allows us to obtain the stage from any other controller
    //This is the improvement we needed to not lose the reference of the stage.
    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
