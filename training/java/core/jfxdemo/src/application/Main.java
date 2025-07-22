package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	
		
			 public void start(Stage stage) {      
			      
//			      BorderPane bPane = new BorderPane();   
			    
//			      bPane.setTop(new TextField("Top")); 
//			      bPane.setBottom(new TextField("Bottom")); 
//			      bPane.setLeft(new TextField("Left")); 
//			      bPane.setRight(new TextField("Right")); 
//			      bPane.setCenter(new TextField("Center")); 
			      
			     
//			      Scene scene = new Scene(bPane, 400, 300);  
			      
			     
//			      stage.setTitle("BorderPane in JavaFX"); 
//			         
			    
//			      stage.setScene(scene);          
			      
			     
//			      stage.show(); 
//			      Parent root;
				try {
					Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
					stage.setTitle("Sample JavaFX Project");
					stage.setScene(new Scene(root,400,400));
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
		} 
	
	public static void main(String[] args) throws IOException {
		launch(args);
		
	}
}
