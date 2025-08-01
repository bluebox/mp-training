package com.library_management.controllers;

import java.io.IOException;

import com.library_management.App;

import javafx.fxml.FXML;

public class BookOptionsController {
	
	@FXML
    private void switchToAddBook() throws IOException {
        App.setRoot("addBook");
    }
	
	@FXML
	private void switchToUpdateBookDetails() throws IOException{
		App.setRoot("updateBookDetails");
	}
	
	@FXML
	private void switchToViewAllBooks() throws IOException{
		App.setRoot("viewAllBooks");
	}
	
	@FXML
	private void backButton() throws IOException{
		App.setRoot("home");
	}
}
