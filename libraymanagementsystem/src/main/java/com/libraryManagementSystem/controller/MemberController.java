package com.libraryManagementSystem.controller;

import java.io.IOException;

import com.libraryManagementSystem.App;

import javafx.fxml.FXML;

public class MemberController {
	@FXML
	public void switchToBack() throws Exception {
		App.setRoot("primary");
	}

	@FXML
	public void registerMember() throws Exception {
		App.setRoot("MemberRegister");
	}

	@FXML
	public void SwitchToUpdateMember() throws IOException {
		App.setRoot("MemberUpdate");
	}

	@FXML
	public void switchToViewAllMembers() throws Exception {
		App.setRoot("MembersViewAll");
	}
}
