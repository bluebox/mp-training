package com.library.main;

import com.library.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainDashboard.fxml"));
        Parent root = loader.load();
        stage.setTitle("Library Management System");
        stage.setScene(new Scene(root, 800, 500));
        stage.setResizable(false);
        stage.show();

        // Set the primary stage globally
        MainController.setPrimaryStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
