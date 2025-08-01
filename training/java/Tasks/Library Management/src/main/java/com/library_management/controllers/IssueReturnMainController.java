package com.library_management.controllers;

import java.io.IOException;
import com.library_management.App;
import javafx.fxml.FXML;


public class IssueReturnMainController {
	@FXML
    private void switchToIssueBook() throws IOException {
        App.setRoot("IssueBook");
    }
	
	@FXML
	private void switchToReturnBook() throws  IOException{
		App.setRoot("ReturnBook");
	}
	
	@FXML
	private void switchToViewAllIssues() throws IOException{
		App.setRoot("viewAllIssues");
	}
	@FXML
	private void backButton() throws IOException{
		App.setRoot("home");
	}
}
