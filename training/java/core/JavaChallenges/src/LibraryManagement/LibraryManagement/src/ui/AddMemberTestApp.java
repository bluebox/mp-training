package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddMemberTestApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = FXMLLoader.load(getClass().getResource("/views/AddMemberView.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Test Add Member UI");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
