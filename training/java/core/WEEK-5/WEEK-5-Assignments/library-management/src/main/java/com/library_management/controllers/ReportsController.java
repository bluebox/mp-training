package com.library_management.controllers;

import java.io.IOException;

import com.library_management.App;
import com.library_management.dao.implementation.ReportsDaoImplementation;

import javafx.fxml.FXML;

public class ReportsController {
	
	@FXML
    private void backButton() throws IOException {
        App.setRoot("home");
    }
	
	 @FXML
	    private void overDueBooks() throws IOException {
		 	
	        App.setRoot("overDueBooks");
	    }
	 
	 @FXML
	    private void booksPerCategory() throws IOException {
	        App.setRoot("categoryCountReport");
	    }
	 
	 @FXML
	    private void activeIssuedBooks() throws IOException {
	        App.setRoot("listOfActiveBooks");
	    }
	 
}
