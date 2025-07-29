
package com.lms.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class HomeController {

    @FXML private Button addBookHome;
    @FXML private Button updateBookHome;
    @FXML private Button viewAllBooksHome;
    @FXML private Button bookAvabilityUpdateHome;
    @FXML private Button addMemberHome;
    @FXML private Button updateMemberHome;
    @FXML private Button viewAllMembersHome;
    @FXML private Button issuedBooksHome;
    @FXML private Button returnBooksHome;
    @FXML private Button reportsHome;

    @FXML private AnchorPane contentHomeAnchorPane;

    @FXML
    void initialize() {
        addBookHome.setOnAction(e -> setContent("/com/lms/ui/AddBook.fxml"));
        updateBookHome.setOnAction(e -> setContent("/com/lms/ui/UpdateBook.fxml"));
        viewAllBooksHome.setOnAction(e -> setContent("/com/lms/ui/ViewAllBooks.fxml"));

        addMemberHome.setOnAction(e -> setContent("/com/lms/ui/AddMember.fxml"));
        updateMemberHome.setOnAction(e -> setContent("/com/lms/ui/UpdateMember.fxml"));
        viewAllMembersHome.setOnAction(e -> setContent("/com/lms/ui/ViewMember.fxml"));

        issuedBooksHome.setOnAction(e -> setContent("/com/lms/ui/IssueBook.fxml"));
        returnBooksHome.setOnAction(e -> setContent("/com/lms/ui/ReturnBook.fxml"));
        reportsHome.setOnAction(e -> setContent("/com/lms/ui/ReportsView.fxml"));
    }

    private void setContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node node = loader.load();
            contentHomeAnchorPane.getChildren().setAll(node);
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);
        } catch (IOException e) {
            showAlert("Failed to Load", "Could not load: " + fxmlPath + "\n" + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
