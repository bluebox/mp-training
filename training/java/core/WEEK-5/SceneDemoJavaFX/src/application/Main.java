package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
//		Group root=new Group();
//		Scene scene=new Scene(root,600,600,Color.SKYBLUE);
//		Image icon=new Image("lmsIcon.png");
//		
//		Text text=new Text();
//		text.setText("This is my first text in JAVAFX");
//		text.setX(50);
//		text.setY(50);
//		text.setFont(Font.font("Verdana",20));
//		text.setFill(Color.LIMEGREEN);
//		
//		Line line=new Line();
//		line.setStartX(100);
//		line.setStartY(100);
//		line.setEndX(600);
//		line.setEndY(600);
//		line.setStrokeWidth(8);
//		line.setStroke(Color.RED);
//		line.setOpacity(0.5);
//		line.setRotate(45);
//		
//		root.getChildren().addAll(text,line);
//		stage.getIcons().add(icon);
//		stage.setScene(scene);
//		stage.setWidth(600);
//		stage.setHeight(600);
//		stage.setTitle("Rohit's Stage...");
		
		Parent root=FXMLLoader.load(getClass().getResource("/Sample.fxml"));
		Scene scene=new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
