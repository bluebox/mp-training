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
        openWindow("/com/library/ui/add_book.fxml", "Add Book");
    }

    @FXML private void openViewBooks() {
        openWindow("/com/library/ui/view_books.fxml", "View Books");
    }

    @FXML private void openAddMember() {
        openWindow("/com/library/ui/add_member.fxml", "Add Member");
    }

    @FXML private void openViewMembers() {
        openWindow("/com/library/ui/view_members.fxml", "View Members");
    }

    @FXML private void openIssueBook() {
        openWindow("/com/library/ui/issue_book.fxml", "Issue Book");
    }

    @FXML private void openReturnBook() {
        openWindow("/com/library/ui/return_book.fxml", "Return Book");
    }
    
    @FXML private void openViewAllIssuedBooks() {
        openWindow("/com/library/ui/view_all_issuedbooks.fxml", "View Issued Books");
    }
}