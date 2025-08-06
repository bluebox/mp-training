package javaFxintro.app;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@SuppressWarnings("exports")
	@Override
	public void start(Stage stage) throws IOException {

		scene = new Scene(loadFXML("stackPaneExample"), 640, 480);

		stage.setScene(scene);
		stage.show();
//		
//		scene = new Scene(loadFXML("gridPaneexample"), 640, 480);
//		scene = new Scene(loadFXML("hboxExample"), 640, 480);
//		scene = new Scene(loadFXML("borderPaneExample"), 640, 480);
//		scene = new Scene(loadFXML("flowPaneExample"), 640, 480);
//		scene = new Scene(loadFXML("vboxExample"), 640, 480);
//		scene = new Scene(loadFXML("tilePaneExample"), 640, 480);
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}