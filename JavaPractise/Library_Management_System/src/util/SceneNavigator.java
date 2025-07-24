package util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneNavigator {
 public static void switchScene(Stage stage, String fxmlPath) throws IOException {
     Parent root = FXMLLoader.load(SceneNavigator.class.getResource(fxmlPath));
     stage.setScene(new Scene(root));
     stage.show();
 }
}

