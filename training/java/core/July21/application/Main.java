package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Empty
			//Group root = new Group();
			
//			Line
//			Line line = new Line();
//			line.setStartX(100);
//			line.setStartY(150);
//			line.setEndX(300);
//			line.setEndY(150);
//			Group root = new Group(line);
			
//			Text
			Text text = new Text();
			text.setX(200);
			text.setY(200);
			text.setText("Hello! This is Sahithi");
//			Group root = new Group();
//			root.getChildren().add(text);
			Group root = new Group(text);
			
//			
			
			Scene scene = new Scene(root,400,400);
//			scene.setFill(Color.BLUE);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Sample Application");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
