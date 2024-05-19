package com.example.projeto_final_hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Aquisição do controlo de uma Scene baseada em XML (FXML) e associação à primaryStage
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        //Definiçoes da Stage
        Settings.setPrimaryStage(primaryStage);
        //Abertura da Stage
        primaryStage.show();

    }
}
