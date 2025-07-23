package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("events.fxml"));

		Scene scene = new Scene(root, 400, 300);
		primaryStage.setTitle("JavaFX Events");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("Escape key disabled");
                event.consume();
            }
        });

	}

	public static void main(String[] args) {
		launch(args);
	}
}
