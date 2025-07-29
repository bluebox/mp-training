package com.lms.controller;

import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;
import com.lms.serviceImpl.MemberService;
import com.lms.util.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UpdateMemberController {

    @FXML private TextField memberIdUpdate;
    @FXML private TextField nameUpdate;
    @FXML private TextField mailIdUpdate;
    @FXML private TextField mobileNumberUpdate;
    @FXML private ComboBox<String> genderUpdate;
    @FXML private TextArea addressUpdate;
    @FXML private Button fetchButtonUpdate;
    @FXML private Button updateButtonUpdate;
    @FXML private Button revertButtonUpdate;

    private Member fetchedMember = null;
    private final MemberService memberService = new MemberService();

    @FXML
    public void initialize() {
        genderUpdate.getItems().addAll("Male", "Female", "Other");
    }

    @FXML
    private void handleFetchMember() {
        try {
            String mobileno = memberIdUpdate.getText().trim();
            fetchedMember = memberService.getMemberByMobile(mobileno);

            if (fetchedMember != null) {
                nameUpdate.setText(fetchedMember.getName());
                mailIdUpdate.setText(fetchedMember.getEmail());
                mobileNumberUpdate.setText(fetchedMember.getMobile());
                genderUpdate.setValue(fetchedMember.getGender());
                addressUpdate.setText(fetchedMember.getAddress());
            } else {
                showAlert(Alert.AlertType.WARNING, "No Member Found", "No member found with: " + mobileno);
            }
        } catch (InvalidInputException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Member ID", e.getMessage());
        }
    }

    @FXML
    private void handleUpdateMember() {
        if (fetchedMember == null) {
            showAlert(Alert.AlertType.WARNING, "Fetch Required", "Please fetch a member first.");
            return;
        }

        String enteredName = nameUpdate.getText().trim();
        String enteredEmail = mailIdUpdate.getText().trim();
        String enteredMobile = mobileNumberUpdate.getText().trim();
        String selectedGender = genderUpdate.getValue();
        String enteredAddress = addressUpdate.getText().trim();

        try {
            Validator.validateName(enteredName);
            Validator.validateEmail(enteredEmail);
            Validator.validateMobileNumber(enteredMobile);
            Validator.validateGender(selectedGender);
            Validator.validateAddress(enteredAddress);

            fetchedMember.setName(enteredName);
            fetchedMember.setEmail(enteredEmail);
            fetchedMember.setMobile(enteredMobile);
            fetchedMember.setGender(selectedGender);
            fetchedMember.setAddress(enteredAddress);

            boolean updated = memberService.updateMember(fetchedMember);

            showAlert(updated ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR,
                      updated ? "Success" : "Failed",
                      updated ? "Member updated successfully!" : "Failed to update member.");

        } catch (InvalidInputException e) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Unexpected Error", "Something went wrong: " + e.getMessage());
        }
    }

    @FXML
    private void handleRevert() {
        memberIdUpdate.clear();
        nameUpdate.clear();
        mailIdUpdate.clear();
        mobileNumberUpdate.clear();
        genderUpdate.getSelectionModel().clearSelection();
        addressUpdate.clear();
        fetchedMember = null;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
