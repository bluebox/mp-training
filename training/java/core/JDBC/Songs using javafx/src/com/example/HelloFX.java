package com.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloFX extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX App");
        Label label = new Label("Hello, JavaFX from Eclipse!");
        Scene scene = new Scene(label, 300, 200);
        stage.setScene(scene);
        
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
