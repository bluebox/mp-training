package com.library;


import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) throws SQLException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/com/library/UI/Home.fxml"));
		primaryStage.setTitle("Book Entry Form");
		primaryStage.setScene(new Scene(root, 600, 600));
		primaryStage.show();

	}

}