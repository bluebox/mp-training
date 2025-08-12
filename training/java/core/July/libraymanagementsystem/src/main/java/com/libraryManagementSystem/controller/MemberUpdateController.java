package com.libraryManagementSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.InvalidMemberDataException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;
import com.libraryManagementSystem.services.MemberService;
import com.libraryManagementSystem.services.impl.MemberServiceImpl;
import com.libraryManagementSystem.utilities.MemberGender;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class MemberUpdateController implements Initializable {

	MemberService memberService = new MemberServiceImpl();

	@FXML
	private ComboBox<Member> members;
	@FXML
	private Label memberId;
	@FXML
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private TextField mobile;
	@FXML
	private TextField address;
	@FXML
	private ComboBox<MemberGender> gender;
	@FXML
	private Label error;
	 @FXML
	    private TextField statusField;

	@FXML
	private void switchToMembers() throws Exception {
		App.setRoot("Members");
	}

	@FXML
	private void switchToPrimary() throws IOException {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Member> membersList = null;
		try {
			membersList = memberService.getMembers();
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		} catch (StatementPreparationException e) {
			e.printStackTrace();
		}
		ObservableList<Member> observableMembers = FXCollections.observableArrayList(membersList);

		FilteredList<Member> filteredMembers = new FilteredList<>(observableMembers, p -> true);
		members.setItems(filteredMembers);

		members.setConverter(new StringConverter<Member>() {
			@Override
			public String toString(Member member) {
				return member != null ? member.getName() : "";
			}

			@Override
			public Member fromString(String string) {
				return members.getItems().stream().filter(m -> m.getName().equals(string)).findFirst().orElse(null);
			}
		});

		members.setEditable(true);
		TextField editor = members.getEditor();

		editor.textProperty().addListener((obs, oldValue, newValue) -> {
			String filter = (newValue == null) ? "" : newValue.toLowerCase();
			filteredMembers.setPredicate(member -> filter.isEmpty() || member.getName().toLowerCase().contains(filter));
			members.show();
		});

		members.setOnAction(event -> {
			if (!members.getSelectionModel().isEmpty()) {
				memberPropertySetting();
			}
		});

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
		address.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches("[a-zA-Z0-9 ,./-]{0,100}")) {
				error.setText("Enter a valid address (letters, numbers, , . / - allowed)");
				error.setStyle("-fx-text-fill: red;");
				address.setText(oldValue);
			} else {
				error.setText("");
			}
		});

	}
	
	

	@FXML
	public void update() throws DatabaseConnectionException, StatementPreparationException, InvalidMemberDataException {
		Member member;

		if (!members.getSelectionModel().isEmpty()) {
			member = members.getSelectionModel().getSelectedItem();

			String memberName = name.getText();
			String memberEmail = email.getText();
			long memberMobile;
			try {
				memberMobile = Long.parseLong(mobile.getText());
			} catch (NumberFormatException e) {
				memberMobile = 0L;
			}

			String memberAddress = address.getText();

			MemberGender memberGender = gender.getSelectionModel().getSelectedItem();

			Member newMember = new Member(member.getMemberId(), memberName, memberEmail, memberMobile, memberGender,
					memberAddress);

			if (!(member.equals(newMember))) {
				memberService.updateMember(newMember, member);
				error.setText(member.getName() + " Member updated Successfully");

				error.setStyle("-fx-text-fill: green");

				name.clear();
				email.clear();
				mobile.clear();
				address.clear();
				gender.setValue(null);
			} else {
				error.setText("Please edit at least one Field");
				error.setStyle("-fx-text-fill: red");
			}

		} else {
			error.setText("Please Select Member Frist");
			error.setStyle("-fx-text-fill: red");
		}

	}

	private void memberPropertySetting() {

		Member member = members.getSelectionModel().getSelectedItem();

		email.setDisable(false);
		email.setText(member.getEmail());

		name.setDisable(false);
		name.setText(member.getName());

		memberId.setText(String.valueOf(member.getMemberId()));

		mobile.setDisable(false);
		mobile.setText(String.valueOf(member.getMobile()));

		gender.setDisable(false);
		gender.getSelectionModel().select(member.getGender());

		address.setDisable(false);
		address.setText(member.getAddress());

	}

}
