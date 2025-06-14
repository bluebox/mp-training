package com.library;

import java.sql.Connection;

import com.library.utilities.ConnectionMaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		//launch(args);
		Connection conn=ConnectionMaker.getConnection();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		primaryStage.setTitle("Book Entry Form");
		primaryStage.setScene(new Scene(root, 600, 600));
		primaryStage.show();

	}

}
