package com.library.controller;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;

import com.library.domain.Member;
import com.library.service.MemberService;

public class MemberController {
	
	@FXML
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private TextField mobile;
	@FXML
	private ToggleGroup gender;
	@FXML
	private TextField address;
	@FXML
	private void addAMember() {

		MemberService memberService = new MemberService();

		String memberName = name.getText();
		String memberEmail = email.getText();
		long memberMobile = Long.valueOf(mobile.getText());
		String memberAddress = address.getText();

		System.out.println("Name: " + memberName);
		System.out.println("Email: " + memberEmail);
		System.out.println("Mobile: " + memberMobile);
		System.out.println("Address: " + memberAddress);

		Toggle genderToggle = gender.getSelectedToggle();
//        if(genderToggle!=null) {
		String gender = (String) ((RadioButton) genderToggle).getUserData();
		System.out.println("Gender: " + gender);
//        }
		memberService.addMember(new Member(memberName, memberEmail, memberMobile, gender.charAt(0), memberAddress));
	}
}