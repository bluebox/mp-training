package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
public class DemoSpinner extends Application {
   @Override
   public void start(Stage stage) {
      Label newlabel = new Label("Enter Date of Birth: ");
      Label setYear = new Label("Year: ");
      Label setMonth = new Label("Month: ");
      Label setDay = new Label("Day: ");
      Spinner year = new Spinner(1900, 2025, 2000);
      year.setPrefSize(65, 25);
      Spinner month = new Spinner(1, 12, 1);
      month.setPrefSize(60, 25);
      Spinner day = new Spinner(1, 31, 1);
      day.setPrefSize(60, 25);
      HBox box1 = new HBox();
      box1.setPadding(new Insets(15, 12, 15, 12));
      box1.setSpacing(10);
      box1.getChildren().addAll(setYear, year, setMonth, month, setDay, day);
      VBox box2 = new VBox();
      box2.setAlignment(Pos.CENTER); 
      box2.setPadding(new Insets(15, 12, 15, 12));
      box2.setSpacing(10);
      box2.getChildren().addAll(newlabel, box1);
      Scene scene = new Scene(box2, 400, 400);
      stage.setScene(scene);
      stage.setTitle("Spinner in JavaFX");
      stage.show();
   }
      public static void main(String[] args) {
      launch(args);
   }
}