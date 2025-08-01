package com.libraryManagementSystem.controller;

import com.libraryManagementSystem.App;

import javafx.fxml.FXML;

public class ReportsController {

	@FXML
	public void switchToReports() throws Exception {
		App.setRoot("primary");
	}

	@FXML
	public void switchToOverDueBooks() throws Exception {
		App.setRoot("OverDueBooks");
	}

	@FXML
	public void switchToCountOfBooks() throws Exception {
		App.setRoot("CountOfBooks");
	}

	@FXML
	public void switchToActiveBooks() throws Exception {
		App.setRoot("MembersWithActiveIssuedBooks");
	}

}
