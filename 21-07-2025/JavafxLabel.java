package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
public class JavafxLabel extends Application {
   public void start(Stage stage) {
      Label label = new Label("Sample label of JavaFX");
      Font font = Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.REGULAR, 25);
      label.setFont(font);
      label.setTextFill(Color.BLUEVIOLET);
      label.setTranslateX(150);
      label.setTranslateY(25);
      Group root = new Group();
      root.getChildren().add(label);
      Scene scene = new Scene(root, 400, 300, Color.BEIGE);
      stage.setTitle("Label");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}