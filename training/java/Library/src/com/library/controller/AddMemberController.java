package com.library.controller;

import com.library.dao.Member;
import com.library.services.MemberService;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddMemberController {
    @FXML private TextField nameField, emailField, mobileField, addressField;
    @FXML private ChoiceBox<String> genderChoice;

    private final MemberService memberService = new MemberService();

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
            new Alert(Alert.AlertType.INFORMATION, "Member added successfully").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
