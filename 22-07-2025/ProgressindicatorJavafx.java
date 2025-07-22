package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
public class ProgressindicatorJavafx extends Application {
   @Override
   public void start(Stage stage) {
      ProgressIndicator progress = new ProgressIndicator();
      Scene scene = new Scene(progress, 400, 300);
      stage.setScene(scene);
      stage.setTitle("Progress Indicator in Javafx");
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}
