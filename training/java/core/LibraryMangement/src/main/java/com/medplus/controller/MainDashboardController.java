package com.medplus.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainDashboardController {
    @FXML private Button addBookBtn;
    @FXML private Button viewBooksBtn;
    @FXML private Button issueBookBtn;
    @FXML private Button returnBookBtn;

    @FXML
    public void handleNavigation(ActionEvent event) {
        try {
            Button clickedButton = (Button) event.getSource();
            String fxmlFile = "";

            switch (clickedButton.getId()) {
                case "addBookBtn": fxmlFile = "/view/AddBookView.fxml"; break;
                case "viewBooksBtn": fxmlFile = "/view/BookListView.fxml"; break;
                case "issueBookBtn": fxmlFile = "/view/IssueBookView.fxml"; break;
                case "returnBookBtn": fxmlFile = "/view/ReturnBookView.fxml"; break;
                default: return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) clickedButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
