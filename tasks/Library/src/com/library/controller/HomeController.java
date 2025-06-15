package com.library.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    public void handleIssueBook() {
        loadScene("/com/library/ui/IssueBook.fxml", "Issue Book");
    }

    @FXML
    public void handleDisplayRecords() {
        loadScene("/com/library/ui/IssueRecords.fxml", "Issued Records");
    }

    @FXML
    private void goToAddMember() {
        loadScene("/com/library/ui/AddMember.fxml", "Add Member");
    }

    @FXML
    private void goToViewBooks() {
        loadScene("/com/library/ui/ViewBooks.fxml", "View All Books");
    }

    @FXML
    private void goToReturnBook() {
        loadScene("/com/library/ui/ReturnBook.fxml", "Return Book");
    }

    private void loadScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage(); 
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
