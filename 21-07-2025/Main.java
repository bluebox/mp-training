package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/application/javafx.fxml"));
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.setTitle("FXML Form");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}