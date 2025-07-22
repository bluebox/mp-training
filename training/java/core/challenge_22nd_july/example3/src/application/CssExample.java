package application;

import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  

public class CssExample extends Application { 
   @Override 
   public void start(Stage stage) {      
  
      Text text1 = new Text("Email");
      Text text2 = new Text("Password");       
      TextField textField1 = new TextField();     
      PasswordField textField2 = new PasswordField(); 
      
      Button button1 = new Button("Submit"); 
      Button button2 = new Button("Clear");  
      

      GridPane gridPane = new GridPane();    
      gridPane.setMinSize(400, 200);
      gridPane.setPadding(new Insets(10, 10, 10, 10)); 

      gridPane.setVgap(5); 
      gridPane.setHgap(5);       
      
      gridPane.setAlignment(Pos.CENTER); 
       
      gridPane.add(text1, 0, 0); 
      gridPane.add(textField1, 1, 0); 
      gridPane.add(text2, 0, 1);       
      gridPane.add(textField2, 1, 1); 
      gridPane.add(button1, 0, 2); 
      gridPane.add(button2, 1, 2); 
       
      //Styling nodes  
      button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
      button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
       
      text1.setStyle("-fx-font: normal bold 20px 'serif' "); 
      text2.setStyle("-fx-font: normal bold 20px 'serif' ");  
      gridPane.setStyle("-fx-background-color: BEIGE;"); 
       
      Scene scene = new Scene(gridPane, 400, 300); 
      stage.setTitle("CSS Example in JavaFX"); 
      stage.setScene(scene);
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}
