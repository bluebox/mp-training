package application;

import java.nio.file.Path;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		// Creating a simple group as a root.
		Group root =new Group();
		Scene scene=new Scene(root,Color.BLACK);
		primaryStage.setTitle("Rohit Varma Stage...");
		Image icon=new Image("lmsIcon.png");
		primaryStage.getIcons().add(icon);
		primaryStage.setScene(scene);
//		primaryStage.setWidth(600);
//		primaryStage.setHeight(600);
//		primaryStage.setResizable(false);
//		primaryStage.setX(50);
//		primaryStage.setY(50);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("click x to escape...");
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("x"));
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
