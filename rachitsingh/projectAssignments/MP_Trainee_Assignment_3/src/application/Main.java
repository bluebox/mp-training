package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/PustakaLokam/library/ui/menu.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			primaryStage.setTitle("PustakaLokam Library Management System");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured while loading menu.fxml");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
