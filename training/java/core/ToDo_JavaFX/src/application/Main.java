package application;
	
import java.io.IOException;

import Data.TodoDataSave;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Todo Application");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		try {
			TodoDataSave.getInstance().storeTodoItems();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	
	
	}

	@Override
	public void init() throws Exception {
		try {
				TodoDataSave.getInstance().loadTodoItems();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
