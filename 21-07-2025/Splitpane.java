package application;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
public class Splitpane extends Application {
   @Override
   public void start(Stage stage) {
      SplitPane splitP = new SplitPane();
      splitP.setOrientation(Orientation.VERTICAL);
      VBox box1 = new VBox();
      VBox box2 = new VBox();
      box1.getChildren().add(new Label("This is \nthe \nfirst section"));
      box2.getChildren().add(new Label("This is \nthe \nsecond section"));
      splitP.getItems().addAll(box1, box2);
      splitP.setDividerPositions(0.5);
      Scene scene = new Scene(splitP, 400, 300);
      stage.setTitle("SplitPane in JavaFX");
      stage.setScene(scene);
      stage.show();
   }
      public static void main(String[] args) {
      launch(args);
   }
}