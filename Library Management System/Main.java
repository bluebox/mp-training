package com.LibraryManagement.application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPageForm.fxml"));
            StackPane root = loader.load(); 
            Scene scene = new Scene(root, 700, 550);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Library Management System");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}