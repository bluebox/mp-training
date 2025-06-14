package com.library.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {

	private void loadScene(String fxmlFile, ActionEvent event) throws IOException {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/UI/" + fxmlFile));
	    Scene scene = new Scene(loader.load());
	    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    stage.setScene(scene);
	    stage.setTitle("Library System");
	    stage.show();
	}


    @FXML private void goToAddBook(ActionEvent event) throws IOException {
        loadScene("AddBook.fxml", event);
    }

    @FXML private void goToAddMember(ActionEvent event) throws IOException {
        loadScene("AddMember.fxml", event);
    }

    @FXML private void goToViewBooks(ActionEvent event) throws IOException {
        loadScene("ViewBooks.fxml", event);
    }

    @FXML private void goToViewMembers(ActionEvent event) throws IOException {
        loadScene("ViewMembers.fxml", event);
    }

    @FXML private void goToIssueBook(ActionEvent event) throws IOException {
        loadScene("IssueBook.fxml", event);
    }

    @FXML private void goToReturnBook(ActionEvent event) throws IOException {
        loadScene("ReturnBook.fxml", event);
    }

    @FXML private void goToIssuedRecords(ActionEvent event) throws IOException {
        loadScene("IssuedRecords.fxml", event);
    }
}
