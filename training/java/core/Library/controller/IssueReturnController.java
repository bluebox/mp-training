package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IssueReturnController {

    public void handleIssueBook() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IssueBook.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Issue Book");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleReturnBook() {
       
       
       
        System.out.println("Book returned successfully.");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReturnBook.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Issue Book");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
}
