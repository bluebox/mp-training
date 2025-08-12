package com.libraryManagementSystem.controller;

import java.io.IOException;

import com.libraryManagementSystem.App;

import javafx.fxml.FXML;

public class BookOptionsController {

	@FXML
	private void switchToBack() throws IOException {

		App.setRoot("primary");
	}

	@FXML
	private void switchToAddBook() throws IOException {

		App.setRoot("BookAdd");
	}

	@FXML
	private void switchToViewAllBooks() throws IOException {

		App.setRoot("BooksViewAll");
	}

	@FXML
	private void switchToUpdateBook() throws Exception {
		App.setRoot("BookUpdate");
	}

//	@FXML
//	private void switchToBookAvailability() throws Exception {
//		App.setRoot("BookAvailabilty");
//	}
}