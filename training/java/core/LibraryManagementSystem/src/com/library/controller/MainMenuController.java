package com.library.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class MainMenuController {
 
    private void openWindow(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    @FXML private void openAddBook() {
        openWindow("/com/Fxml/AddBookFxml.fxml", "Add Book");
    }
 
    @FXML private void openViewBooks() {
        openWindow("/com/Fxml/ViewBooks.fxml", "View Books");
    }
    @FXML private void openUpdateBooks() {
        openWindow("/com/Fxml/UpdateBook.fxml", "Update Book");
    }
 
    @FXML private void openAddMember() {
        openWindow("/com/Fxml/AddMember.fxml", "Add Member");
    }
 
    @FXML private void openViewMembers() {
        openWindow("/com/Fxml/ViewMembers.fxml", "View Members");
    }
 
    @FXML private void openIssueBook() {
        openWindow("/com/Fxml/IssueBook.fxml", "Issue Book");
    }
 
    @FXML private void openReturnBook() {
        openWindow("/com/Fxml/ReturnBook.fxml", "Return Book");
    }
    
    @FXML private void openViewAllIssuedBooks() {
        openWindow("/com/Fxml/ViewAllIssuedBooks.fxml", "View Issued Books");
    }
}