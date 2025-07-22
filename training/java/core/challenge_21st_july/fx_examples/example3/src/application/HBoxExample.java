package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos; 
import javafx.scene.control.Button; 
import javafx.scene.control.TextField; 
import javafx.scene.layout.HBox;


//public class Main extends Application {
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//		launch(args);
//	}
//}


public class HBoxExample extends Application {   
   @Override 
   public void start(Stage stage) {       
      //creating a text field   
      TextField textField = new TextField();       
      
      //Creating the play button 
      Button playButton = new Button("Play");       
      
      //Creating the stop button 
      Button stopButton = new Button("stop"); 
       
      //Instantiating the HBox class  
      HBox box = new HBox(10, textField, playButton, stopButton);    
      box.setAlignment( Pos.CENTER);

      //Creating a scene object
      Scene scene = new Scene(box, 400, 300);  
      
      //Setting title to the Stage 
      stage.setTitle("Hbox Example in JavaFX"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}
