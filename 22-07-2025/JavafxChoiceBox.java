package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class JavafxChoiceBox extends Application {
   public void start(Stage stage) {
      ChoiceBox<String> box = new ChoiceBox<String>();
      ObservableList<String> oslist = box.getItems();
      oslist.addAll("Windows7", "Windows8", "Windows10", "Windows11", "MAC OS");
      box.setTranslateX(200);
      box.setTranslateY(15);
      Label setlabel = new Label("Select your Operating System:");
      setlabel.setTranslateX(20);
      setlabel.setTranslateY(20);
      Group newgrp = new Group(box, setlabel);
      Scene scene = new Scene(newgrp, 500, 200);
      stage.setTitle("Choice Box in JavaFX");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}
