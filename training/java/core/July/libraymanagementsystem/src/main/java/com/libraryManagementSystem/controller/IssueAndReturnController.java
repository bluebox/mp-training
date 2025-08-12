package com.libraryManagementSystem.controller;

import java.io.IOException;

import com.libraryManagementSystem.App;

import javafx.fxml.FXML;

public class IssueAndReturnController {

	@FXML
	public void switchToIssueBook() throws Exception {
		App.setRoot("IssueBook");
	}

	@FXML
	public void switchToReturnBook() throws IOException {
		App.setRoot("ReturnBook");
	}

	@FXML
	public void switchToPrimary() throws IOException {
		App.setRoot("primary");
	}
}
