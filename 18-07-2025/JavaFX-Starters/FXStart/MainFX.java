package fxapp;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello JavaFX 24!");
        Scene scene = new Scene(label, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX SDK 24 App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
