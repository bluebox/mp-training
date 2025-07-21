package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

//		GridPane root = new GridPane();
//		root.setAlignment(Pos.CENTER);
//		root.setVgap(10);
//		root.setHgap(10);
//
//		Label greet = new Label("Welcome to JavaFX!");
//		greet.setTextFill(Color.RED);
//		greet.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
//
//		root.getChildren().add(greet);

		Scene scene = new Scene(root, 400, 300);
		primaryStage.setTitle("Hello JavaFX!");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
