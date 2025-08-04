package com.LibraryManagement.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/com/LibraryManagement/application/" + fxmlFile));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 800, 500));
    }
}
