package library.main;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.exception.LibraryException;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws LibraryException {
		try {

//			DataLoader.loadInitialBookData();
//			DataLoader.loadInitialMemberData();
			URL fxmlLocation = getClass().getClassLoader().getResource("MainScreen.fxml");

			if (fxmlLocation == null) {
				System.err.println("Error: MainScreen.fxml not found on classpath.");
				throw new IOException("FXML file not found: MainScreen.fxml");
			}
			
			FXMLLoader loader = new FXMLLoader(fxmlLocation);
			Parent root = loader.load();

			Scene scene = new Scene(root, 900,600);

			primaryStage.setTitle("Library Management System - Main Menu");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			System.err.println("Failed to load FXML file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
