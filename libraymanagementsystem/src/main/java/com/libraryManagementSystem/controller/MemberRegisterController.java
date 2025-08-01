package com.libraryManagementSystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.MemberService;
import com.libraryManagementSystem.services.impl.MemberServiceImpl;
import com.libraryManagementSystem.utilities.MemberGender;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MemberRegisterController implements Initializable {

	MemberService memberService = new MemberServiceImpl();

	@FXML
	private ComboBox<MemberGender> gender;

	@FXML
	private TextField name;

	@FXML
	private TextField email;

	@FXML
	private TextField mobile;

	@FXML
	private TextField adress;
	@FXML
	private Label error;

	public void switchToMembers() throws Exception {
		App.setRoot("Members");
	}

	public void switchToPrimary() throws Exception {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		gender.getItems().addAll(MemberGender.values());
	}

	@FXML
	public void submitDetails() {

		String memberName = name.getText();
		String memberEmail = email.getText();
		long memberMobile;
		try {
			memberMobile = Long.parseLong(mobile.getText());
		} catch (NumberFormatException e) {
			memberMobile = 0L;
		}

		MemberGender memberGender = gender.getSelectionModel().getSelectedItem();
		String memberAdress = adress.getText();

		Member newMember = new Member(-1, memberName, memberEmail, memberMobile, memberGender, memberAdress);

		try {
			memberService.registerMember(newMember);

			error.setText(newMember.getName() + " Member added Successfully");

			error.setStyle("-fx-text-fill: green");

			name.clear();
			email.clear();
			mobile.clear();
			adress.clear();

			gender.setValue(null);

			gender.setPromptText("Select Gender");

		} catch (InvalidException e) {
			error.setText(e.getMessage());
			error.setStyle("-fx-text-fill: red");
		}
	}

}
