package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
public class ShowAlert extends Application {
   @Override
   public void start(Stage stage) {
      Label label = new Label("On clicking the below button, it will display an alert....");
      Button button = new Button("Show Alert");
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Alert Box");
      alert.setHeaderText(null);
      alert.setContentText("Showing an Alert in JavaFX!");
      button.setOnAction(e -> alert.showAndWait());
      VBox vbox = new VBox(label, button);
      vbox.setAlignment(Pos.CENTER); 
      Scene scene = new Scene(vbox, 400, 300);
      stage.setTitle("Alert in JavaFX");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}