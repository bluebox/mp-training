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
      TextField textField = new TextField();       
      Button playButton = new Button("Play");       
      Button stopButton = new Button("stop"); 
      HBox box = new HBox(10, textField, playButton, stopButton);    
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
