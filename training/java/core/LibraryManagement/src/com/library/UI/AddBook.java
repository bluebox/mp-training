package com.library.UI;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddBook extends Application {

    @Override
    public void start(Stage primaryStage) {
    	Parent root;
        
        try {
			root=FXMLLoader.load(getClass().getResource("AddBook.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return; 
		}
        primaryStage.setTitle("Started");
        primaryStage.setScene(new Scene(root,500,500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
