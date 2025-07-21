package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
public class StaticSpinner extends Application {
   @Override
   public void start(Stage stage) {
      Label newlabel = new Label("Sample Spinner: ");
      Spinner newSpinner = new Spinner(0, 100, 25); 
      VBox vbox = new VBox(newlabel, newSpinner);
      vbox.setAlignment(Pos.CENTER); 
      Scene scene = new Scene(vbox, 400, 300);
      stage.setScene(scene);
      stage.setTitle("Spinner in JavaFX");
      stage.show();
   }
      public static void main(String[] args) {
      launch(args);
   }
}
