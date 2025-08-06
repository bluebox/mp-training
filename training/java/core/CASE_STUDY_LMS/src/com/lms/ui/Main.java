package com.lms.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lms/ui/Home.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Library Management System");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
