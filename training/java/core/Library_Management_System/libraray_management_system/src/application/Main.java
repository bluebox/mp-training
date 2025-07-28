package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
public class Main extends Application {
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("Library_Managemnet_System");
		showHomeView();

	}
	
	public void showHomeView() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/applicationview/HomeView.fxml"));
		Parent root = fxmlLoader.load();
		Scene Homescene=new Scene(root);
		primaryStage.setScene(Homescene);
		primaryStage.show();
	}	
	public static void main(String[] args) {
		launch(args);
	}
}
