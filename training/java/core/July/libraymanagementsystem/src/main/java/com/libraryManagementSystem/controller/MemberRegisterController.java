package com.libraryManagementSystem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.InvalidMemberDataException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;
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

	@FXML
	private Label successerror;

	public void switchToMembers() throws Exception {
		App.setRoot("Members");
	}

	public void switchToPrimary() throws Exception {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		name.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches("[a-zA-Z ]{0,50}")) {
				error.setText("Enter alphabets only");
				error.setStyle("-fx-text-fill: red;");
				name.setText(oldValue);
			} else {
				error.setText("");
			}
		});
		email.textProperty().addListener((obs, oldValue, newValue) -> {
			String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

			if (!newValue.isEmpty() && !newValue.matches(emailPattern)) {
				error.setText("Enter a valid email address");
				error.setStyle("-fx-text-fill: red;");
			} else {
				error.setText("");
			}
		});

		gender.getItems().addAll(MemberGender.values());

		mobile.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches("\\d{0,10}")) {
				mobile.setText(oldValue);
				return;
			}

			if (!newValue.isEmpty() && !newValue.matches("^[6-9]\\d{0,9}$")) {
				error.setText("Enter a valid 10-digit mobile number");
				error.setStyle("-fx-text-fill: red;");
			} else {
				error.setText("");
			}
		});
		adress.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches("[a-zA-Z0-9 ,./-]{0,100}")) {
				error.setText("Enter a valid address (letters, numbers, , . / - allowed)");
				error.setStyle("-fx-text-fill: red;");
				adress.setText(oldValue);
			} else {
				error.setText("");
			}
		});

	}

	@FXML
	public void submitDetails() throws DatabaseConnectionException, StatementPreparationException,
			InvalidMemberDataException, DatabaseOperationException {

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

			successerror.setText(newMember.getName() + " Member added Successfully");

			successerror.setStyle("-fx-text-fill: Black");

			name.clear();
			email.clear();
			mobile.clear();
			adress.clear();

			gender.setValue(null);

			gender.setPromptText("Select Gender");

		} catch (InvalidMemberDataException e) {
			successerror.setText(e.getMessage());
			successerror.setStyle("-fx-text-fill: red");
		}
	}

}
