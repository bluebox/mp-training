// package Library;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private void handleAddBook(ActionEvent event) {
        try {
            Parent addBookRoot = FXMLLoader.load(getClass().getResource("view/AddBook.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add New Book");
            stage.setScene(new Scene(addBookRoot));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleUpdateBook(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/update_options.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Update Book Options");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddMember(ActionEvent event) {
        System.out.println("Add Member button clicked");
        // Add your code here to open Add Member UI
    }

    @FXML
    private void handleIssueReturn(ActionEvent event) {
        System.out.println("Issue/Return button clicked");
        // Add your code here to open Issue/Return UI
    }

    @FXML
    private void handleReports(ActionEvent event) {
        System.out.println("Reports button clicked");
        // Add your code here to open Reports UI
        // use enums for active and inacctive ,available not availabale and gender
        // feiilds
        // try to use concepts of core java like inner classess,generics,interfaces and
        // abstract classess

    }
}