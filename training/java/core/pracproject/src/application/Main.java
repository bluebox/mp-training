package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
	 @Override
	    public void start(Stage primaryStage) {
	        try {
	            // Load the FXML file
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelloView.fxml"));
	            Parent root = fxmlLoader.load(); // This loads the UI and also creates and links the controller

	            // Create a scene with the loaded FXML root
	            Scene scene = new Scene(root, 400, 250); // Set initial width and height

	            // Set the stage title
	            primaryStage.setTitle("My JavaFX FXML App");
	            // Set the scene on the stage
	            primaryStage.setScene(scene);
	            // Show the stage
	            primaryStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the exception, e.g., show an error dialog
	            System.err.println("Error loading FXML file: " + e.getMessage());
	        }
	    }

	    public static void main(String[] args) {
	        launch(args); // Launches the JavaFX application
	    }
}
