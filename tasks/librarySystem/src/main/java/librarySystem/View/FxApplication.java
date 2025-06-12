package librarySystem.View;


import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class FxApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("In start of application");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
		Parent root = loader.load();

        primaryStage.setTitle("FXML Demo");
		Scene newScene = new Scene(root,500,500);
		primaryStage.setScene(newScene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		System.out.println("In the application");
		launch(args);
	}

}
