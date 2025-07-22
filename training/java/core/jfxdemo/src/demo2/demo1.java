package demo2;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.stage.Stage;

public class demo1  extends Application{
 public static void main(String[] args)
 {
	 launch(args);
 }
 public void start(Stage ps)
 {
	
	
	 ChoiceBox<String> box = new ChoiceBox<String>();
     
     ObservableList<String> oslist = box.getItems();
     
     oslist.addAll("Windows7", "Windows8", "Windows10", "Windows11", "MAC OS");
    
     box.setTranslateX(200);
     box.setTranslateY(15);
    
     Label lbl = new Label("Select your Operating System:");
     lbl.setTranslateX(20);
     lbl.setTranslateY(20);
   
     Group newgrp = new Group(box, lbl);
    
     Scene scene = new Scene(newgrp, 500, 200);
     ps.setTitle("Choice Box in JavaFX");
     ps.setScene(scene);
     ps.show();
	 
	 
 }
}
