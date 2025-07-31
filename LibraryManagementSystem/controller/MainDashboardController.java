package com.library.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class MainDashboardController {

    @FXML
    public void handleAddBook(ActionEvent event) {
        try {
            MainController.switchScene("AddBook.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewBooks(ActionEvent event) {
        try {
            MainController.switchScene("ViewBooks.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddMember(ActionEvent event) {
        try {
            MainController.switchScene("AddMember.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewMembers(ActionEvent event) {
        try {
            MainController.switchScene("ViewMembers.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleIssueBook(ActionEvent event) {
        try {
            MainController.switchScene("IssueBookForm.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleReturnBook(ActionEvent event) {
        try {
            MainController.switchScene("ReturnBookForm.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleReports(ActionEvent event) {
    	try {
            MainController.switchScene("Reports.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
