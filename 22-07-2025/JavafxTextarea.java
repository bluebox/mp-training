package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
public class JavafxTextarea extends Application {
   @Override
   public void start(Stage stage) {
      Label label = new Label("Try typing Text in the box...");
      TextArea txtArea = new TextArea();
      txtArea.setPrefSize(200, 200); 
      txtArea.setWrapText(true);
      HBox box = new HBox(label, txtArea);
      box.setAlignment(Pos.BASELINE_CENTER);
      box.setSpacing(10);
      Scene scene = new Scene(box, 400, 300);
      stage.setScene(scene);
      stage.setTitle("TextArea in JavaFX");
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}
