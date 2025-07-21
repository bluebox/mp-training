package javaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class HelloFX extends Application {

 @Override
 public void start(Stage primaryStage) {
   
     VBox vbox = new VBox();

     vbox.setSpacing(50);

     vbox.setPadding(new Insets(40));

     Label label = new Label("Enter your name:");
     TextField textField = new TextField();
     Button button = new Button("Submit");
     

     vbox.getChildren().addAll(label, textField, button);

     Scene scene = new Scene(vbox, 300, 200);
     primaryStage.setTitle("VBox Layout Example");
     primaryStage.setScene(scene);
     primaryStage.show();
 }

 public static void main(String[] args) {
     launch(args);
 }
}
