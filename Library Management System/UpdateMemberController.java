package com.LibraryManagement.controller;

import com.LibraryManagement.models.Member;
import com.LibraryManagement.service.implementation.MemberServiceImplementation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UpdateMemberController {

    @FXML private TextField memberIdField, nameField, emailField, mobileField;
    @FXML private ComboBox<String> genderCombo;
    @FXML private TextArea addressArea;
    @FXML private Button updateButton;
    @FXML private Label nameErrorLabel, emailErrorLabel, mobileErrorLabel, genderErrorLabel, addressErrorLabel;

    private final MemberServiceImplementation memberService = new MemberServiceImplementation();
    private Member selectedMember;

    public void setMember(Member member) {
        this.selectedMember = member;

        memberIdField.setText(String.valueOf(member.getMemberId()));
        nameField.setText(member.getName());
        emailField.setText(member.getEmail());
        mobileField.setText(String.valueOf(member.getMobile()));
        genderCombo.setValue(member.getGender().equals("M") ? "Male" : "Female");
        addressArea.setText(member.getAddress());
    }

    @FXML
    private void handleUpdate() {
        if (!validateForm()) return;

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        long mobile = Long.parseLong(mobileField.getText().trim());
        String gender = genderCombo.getValue().equals("Male") ? "M" : "F";
        String address = addressArea.getText().trim();

        boolean isSame = name.equals(selectedMember.getName()) &&
                         email.equals(selectedMember.getEmail()) &&
                         mobile == selectedMember.getMobile() &&
                         gender.equals(selectedMember.getGender()) &&
                         address.equals(selectedMember.getAddress());

        if (isSame) {
            showAlert("No Changes Detected", "All fields are same as before. Please modify some details to update.");
            return;
        }

        selectedMember.setName(name);
        selectedMember.setEmail(email);
        selectedMember.setMobile(mobile);
        selectedMember.setGender(gender);
        selectedMember.setAddress(address);

        try {
            memberService.updateMember(selectedMember);
            showAlert("Success", "Member updated successfully!");

            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        nameErrorLabel.setText("");
        emailErrorLabel.setText("");
        mobileErrorLabel.setText("");
        genderErrorLabel.setText("");
        addressErrorLabel.setText("");

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
