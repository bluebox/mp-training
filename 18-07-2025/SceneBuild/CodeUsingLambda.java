package scenePackage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CodeUsingLambda extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	

	public void start(Stage arg0) throws Exception {
		
		Parent root=FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		Scene scene=new Scene(root);
		arg0.setTitle("MainTitle"); 
		arg0.setScene(scene);
		arg0.show();
		
	}

}
