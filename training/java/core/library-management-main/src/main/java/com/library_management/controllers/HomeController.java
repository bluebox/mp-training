package com.library_management.controllers;

import java.io.IOException;

import com.library_management.App;
import com.library_management.dao.implementation.ReportsDaoImplementation;

import javafx.fxml.FXML;

public class HomeController {
	 @FXML
	    private void bookManagement() throws IOException {
		 	new ReportsDaoImplementation().countOfBooksPerCategory();
	        App.setRoot("bookOptions");
	    }
	 
	 @FXML
	    private void memberManagement() throws IOException {
	        App.setRoot("memberOptions");
	    }
	 
	 @FXML
	    private void issueAndReturnBooks() throws IOException {
	        App.setRoot("IssueReturnMain");
	    }
	 
	 @FXML
	    private void reports() throws IOException {
	        App.setRoot("reports");
	    }
}
