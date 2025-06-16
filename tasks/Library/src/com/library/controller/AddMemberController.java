package com.library.controller;

import com.library.domain.Member;
import com.library.serviceInterface.MemberServiceInterface;
import com.library.services.MemberService;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddMemberController {
    @FXML private TextField nameField, emailField, mobileField, addressField;
    @FXML private ChoiceBox<String> genderChoice;

    @FXML
    private Label statusLabel;
    private final MemberServiceInterface memberService = new MemberService();

    public void initialize() {
        genderChoice.getItems().addAll("M", "F");
    }

    @FXML
    private void handleAddMember() {
        try {
            Member member = new Member(0,
           nameField.getText(),
            emailField.getText(),
           Integer.parseInt(mobileField.getText()),
            genderChoice.getValue().charAt(0),
            addressField.getText());

            memberService.addMember(member);
            statusLabel.setText("Member added successfully");
        } catch (Exception e) {
        	statusLabel.setText(e.getMessage());
            
        }
    }
}
