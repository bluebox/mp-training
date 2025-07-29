package com.lms.controller;

import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;
import com.lms.serviceImpl.MemberService;
import com.lms.util.Validator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddMemberController {

    @FXML private TextField name;
    @FXML private TextField mailId;
    @FXML private TextField mobileNumber;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TextArea addressArea;
    @FXML private Button addButton;
    public MemberService memberService = new MemberService();
    @FXML
    public void initialize() {
        genderComboBox.getItems().addAll("Male", "Female", "Other");
    }

    @FXML
    private void handleAddMember() throws InvalidInputException {

        String enteredName = name.getText().trim();
        String enteredEmail = mailId.getText().trim();
        String enteredMobile = mobileNumber.getText().trim();
        String selectedGender = genderComboBox.getValue();
        String enteredAddress = addressArea.getText().trim();
        MemberService memberService = new MemberService();
        
        try {
        	Validator.validateName(enteredName);
			Validator.validateEmail(enteredEmail);
			Validator.validateMobileNumber(enteredMobile);
			Validator.validateGender(selectedGender);
			Validator.validateAddress(enteredAddress);
			
            memberService.validate(enteredName, enteredEmail, enteredMobile, enteredAddress, selectedGender);
        } catch (InvalidInputException e) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", e.getMessage());
            return;
        }

       
       
        Member member = new Member(enteredName, enteredEmail, enteredMobile, selectedGender, enteredAddress);

        boolean success = memberService.addMember(member);
        showAlert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR,
                  success ? "Success" : "Failure",
                  success ? "Member added successfully!" : "Failed to add member.");

        if (success) {
            clearForm();
        }
    }

    private void clearForm() {
        name.clear();
        mailId.clear();
        mobileNumber.clear();
        genderComboBox.getSelectionModel().clearSelection();
        addressArea.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

