package com.library_management.controllers;

import java.io.IOException;

import com.library_management.App;

import javafx.fxml.FXML;

public class MemberOptionsController {

	@FXML
	private void onRegister() throws IOException {
	    App.setRoot("addMember");
	}

	@FXML
	private void updateMember() throws IOException {
	    App.setRoot("updateMember");
	}

	@FXML
	private void viewAllMembers() throws IOException {
	    App.setRoot("viewAllMembers");
	}
	
	@FXML
	private void backButton() throws IOException{
		App.setRoot("home");
	}
}
