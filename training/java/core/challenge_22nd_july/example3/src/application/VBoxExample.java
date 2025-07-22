package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxExample extends Application {   
	   @Override 
	   public void start(Stage stage) {       
	      TextField textField = new TextField();       
	      Button playButton = new Button("Play");       
	      Button stopButton = new Button("stop"); 
	      VBox box = new VBox(10, textField, playButton, stopButton);    
	      box.setAlignment( Pos.CENTER);
	      Scene scene = new Scene(box, 400, 300);  
	      stage.setTitle("Hbox Example in JavaFX"); 
	      stage.setScene(scene); 
	      stage.show(); 
	   } 
	   public static void main(String args[]){ 
	      launch(args); 
	   } 
	}
