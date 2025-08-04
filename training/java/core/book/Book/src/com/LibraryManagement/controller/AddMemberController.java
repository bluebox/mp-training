package com.LibraryManagement.controller;

import com.LibraryManagement.models.Member;
import com.LibraryManagement.service.implementation.MemberServiceImplementation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddMemberController {

    @FXML private TextField nameField, emailField, mobileField;
    @FXML private ComboBox<String> genderCombo;
    @FXML private TextArea addressArea;
    @FXML private Label nameErrorLabel, emailErrorLabel, mobileErrorLabel, genderErrorLabel, addressErrorLabel;
    @FXML private Button addMemberButton;

    private final MemberServiceImplementation memberService = new MemberServiceImplementation();
    
    @FXML
	public void Back(ActionEvent event) {
		try {
			MainController.switchScene("members/MembersForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    @FXML
    private void initialize() {
        genderCombo.getItems().addAll("Male", "Female");
    }

    @FXML
    private void handleRegister() {
        clearErrors();
        if (!validateForm()) return;

        Member member = new Member(
            nameField.getText().trim(),
            emailField.getText().trim(),
            Long.parseLong(mobileField.getText().trim()),
            genderCombo.getValue().equals("Male") ? "M" : "F",
            addressArea.getText().trim()
        );

        try {
           int id= memberService.registerMember(member);
            showAlert("Success", "Member registered successfully! and alloted member id : "+id);
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to register member.");
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        if (nameField.getText().trim().isEmpty()) {
            nameErrorLabel.setText("Name is required *");
            valid = false;
        }
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            emailErrorLabel.setText("Email is required *");
            valid = false;
        } else if (!email.endsWith("@gmail.com")) {
            emailErrorLabel.setText("Enter valid email address *");
            valid = false;
        }
        String mobile = mobileField.getText().trim();
        if (mobile.isEmpty()) {
            mobileErrorLabel.setText("Mobile is required *");
            valid = false;
        } else if (!mobile.matches("\\d{10}")) {
            mobileErrorLabel.setText("Mobile must be exactly 10 digits *");
            valid = false;
        }
        if (genderCombo.getValue() == null) {
            genderErrorLabel.setText("Gender is required *");
            valid = false;
        }
        if (addressArea.getText().trim().isEmpty()) {
            addressErrorLabel.setText("Address is required *");
            valid = false;
        }
        return valid;
    }

    private void clearErrors() {
        nameErrorLabel.setText("");
        emailErrorLabel.setText("");
        mobileErrorLabel.setText("");
        genderErrorLabel.setText("");
        addressErrorLabel.setText("");
    }

    private void clearForm() {
        nameField.clear();
        emailField.clear();
        mobileField.clear();
        genderCombo.setValue(null);
        addressArea.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
