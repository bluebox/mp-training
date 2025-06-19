package com.casestudy.fx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
    	System.out.println("this is running !!!");
        MainMenu mainMenu = new MainMenu();
        Scene scene = new Scene(mainMenu.getRoot(), 800, 600);
        primaryStage.setTitle("Librarian Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
