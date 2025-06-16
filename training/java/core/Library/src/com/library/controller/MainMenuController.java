package com.library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {
	@FXML
	private void handleAddBook(ActionEvent event) {
	    openWindow("/ui/views/AddBookView.fxml", "Add Book");
	}
	@FXML
	private void handleAddMember(ActionEvent event) {
	    openWindow("/ui/views/AddMemberView.fxml", "Add Member");
	}
	@FXML
	private void handleIssueBook(ActionEvent event) {
	    openWindow("/ui/views/IssueBookView.fxml", "Issue Book");
	}
	@FXML
	private void handleReturnBook(ActionEvent event) {
	    openWindow("/ui/views/ReturnBookView.fxml", "Return Book");
	}
    @FXML
    private void handleViewBooks(ActionEvent event) {
        openWindow("/ui/views/ViewBooks.fxml", "View Books");
    }
    @FXML
    private void handleViewMembers(ActionEvent event) {
        showAlert("View Members clicked");
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void openWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}