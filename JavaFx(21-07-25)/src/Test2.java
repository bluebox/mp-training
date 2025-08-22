import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test2 extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StackPane root=new StackPane();
		Scene scene=new Scene(root,Color.BLACK);
		 primaryStage.setTitle("My Tittle");
		 primaryStage.setScene(scene);
		primaryStage.show(); 
		Image icon = new Image("Cartoon.jpg");
		primaryStage.getIcons().add(icon);
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.setResizable(false);
		primaryStage.setX(50);
		primaryStage.setY(50);
		primaryStage.setFullScreen(true);
	}
}
