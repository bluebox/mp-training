package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.geometry.Pos; 
public class Seperator extends Application {
   @Override
   public void start(Stage stage) {
      GridPane newgrid = new GridPane();
      newgrid.setHgap(10); 
      newgrid.setVgap(10); 
      newgrid.setAlignment(Pos.CENTER); 
      Button btn1 = new Button("Button 1");
      Button btn2 = new Button("Button 2");
      Button btn3 = new Button("Button 3");
      Button btn4 = new Button("Button 4");
      newgrid.add(btn1, 0, 0); 
      newgrid.add(btn2, 2, 0); 
      newgrid.add(btn3, 0, 1); 
      newgrid.add(btn4, 2, 1); 
      Separator sep1 = new Separator();
      Separator sep2 = new Separator();
      sep2.setOrientation(Orientation.VERTICAL);
      sep1.setStyle("-fx-background-color: #D2691E;"); 
      sep2.setStyle("-fx-background-color: #D2681E;"); 
      newgrid.add(sep1, 0, 2, 2, 1); 
      newgrid.add(sep2, 1, 0, 1, 2); 
      Scene scene = new Scene(newgrid, 400, 300);
      stage.setTitle("Separator in JavaFX");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}
