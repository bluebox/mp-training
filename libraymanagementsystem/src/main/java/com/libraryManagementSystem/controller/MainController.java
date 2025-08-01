package com.libraryManagementSystem.controller;

import java.io.IOException;

import com.libraryManagementSystem.App;

import javafx.fxml.FXML;

public class MainController {

	@FXML
	public void switchToBook() throws IOException {

		App.setRoot("BookOptions");
	}

	@FXML
	public void switchToMemberOptions() throws IOException {
		App.setRoot("Members");
	}

	@FXML
	public void switchTOIssueAndReturn() throws Exception {
		App.setRoot("Issue&Return");
	}

	@FXML
	public void switchToReports() throws Exception {
		App.setRoot("Reports");
	}
}
