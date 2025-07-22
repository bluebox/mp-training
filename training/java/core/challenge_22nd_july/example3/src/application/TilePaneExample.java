package application;

import javafx.application.Application; 
import javafx.geometry.Orientation; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.TilePane; 
import javafx.stage.Stage;

public class TilePaneExample extends Application { 
   @Override 
   public void start(Stage stage) {    
      //Creating an array of Buttons 
      Button[] buttons = new Button[] { 
         new Button("SunDay"), 
         new Button("MonDay"), 
         new Button("TuesDay"), 
      };   
      TilePane tilePane = new TilePane();  
      tilePane.setTileAlignment(Pos.CENTER_LEFT); 
      tilePane.setPrefRows(4);
      tilePane.getChildren().addAll(buttons);
      Scene scene = new Scene(tilePane, 400, 300);
      stage.setTitle("Tile Pane Example"); 
      stage.setScene(scene); 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}