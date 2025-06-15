import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LibraryTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Button btn = new Button("Click Me!");

		// Set an action when the button is clicked
		btn.setOnAction(e -> System.out.println("Hello JavaFX!"));

		// Create a layout and add the button
		StackPane root = new StackPane();
		root.getChildren().add(btn);

		// Create a scene with the layout
		Scene scene = new Scene(root, 300, 200);

		// Set the stage
		primaryStage.setTitle("JavaFX Demo");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
