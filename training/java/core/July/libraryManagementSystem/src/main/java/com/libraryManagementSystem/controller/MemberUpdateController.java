package com.libraryManagementSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
	private void switchToMembers() throws Exception {
		App.setRoot("Members");
	}

	@FXML
	private void switchToPrimary() throws IOException {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<Member> membersList;
		try {
			membersList = memberService.getMembers();
			members.getItems().addAll(membersList);
			gender.getItems().addAll(MemberGender.values());

			members.setOnAction(event -> {

				if (!members.getSelectionModel().isEmpty()) {
					memberPropertySetting();
				}
			});

		} catch (InvalidException e) {
			error.setText(e.getMessage());
		}

		members.setValue(MembersViewAllController.getMemberIdSelected());
		if (!members.getSelectionModel().isEmpty()) {
			memberPropertySetting();
		}
	}

	@FXML
	public void update() {
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
				try {
					memberService.updateMember(newMember, member);
					error.setText(member.getName() + " Member updated Successfully");

					error.setStyle("-fx-text-fill: green");

					name.clear();
					email.clear();
					mobile.clear();
					address.clear();
					gender.setValue(null);

				} catch (InvalidException e) {
					error.setText(e.getMessage());
					error.setStyle("-fx-text-fill: red");
				}
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
