package com.library_management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import com.library_management.dao.implementation.ReportsDaoImplementation;
import com.library_management.utilities.DBConnection;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	stage.getIcons().add(new Image("/Images/lmsIcon.png"));
        scene = new Scene(loadFXML("home"), 760, 550);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
    	DBConnection.connectToDB("jdbc:mysql://localhost:3306/lms");
        launch();
        DBConnection.closeStatement();
        DBConnection.closeConn();
    }

}