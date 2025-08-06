package com.lms.controller;

import com.lms.exceptions.DAOException;
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
        String mobile = memberIdUpdate.getText().trim();
        try {
            fetchedMember = memberService.getMemberByMobile(mobile);

            if (fetchedMember != null) {
                nameUpdate.setText(fetchedMember.getName());
                mailIdUpdate.setText(fetchedMember.getEmail());
                mobileNumberUpdate.setText(fetchedMember.getMobile());
                genderUpdate.setValue(fetchedMember.getGender());
                addressUpdate.setText(fetchedMember.getAddress());
            } else {
                showAlert(Alert.AlertType.WARNING, "Not Found", "No member found with mobile: " + mobile);
            }

        } catch (InvalidInputException e) {
            showAlert(Alert.AlertType.ERROR, "Search Error", e.getMessage());
        }
    }

    @FXML
    private void handleUpdateMember() {
        if (fetchedMember == null) {
            showAlert(Alert.AlertType.WARNING, "Action Blocked", "Please fetch a member first.");
            return;
        }

    	Member updatedMember = new Member();
    	updatedMember.setName(nameUpdate.getText().trim());
    	updatedMember.setEmail(mailIdUpdate.getText().trim());
    	updatedMember.setMobile(mobileNumberUpdate.getText().trim());
    	updatedMember.setGender(genderUpdate.getValue());
    	updatedMember.setAddress(addressUpdate.getText().trim());
    	updatedMember.setMemberId(fetchedMember.getMemberId());
        try {
            Validator.validateMember(updatedMember);
            boolean success = memberService.updateMember(updatedMember);

            showAlert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR,
                      success ? "Success" : "Failed",
                      success ? "Member updated successfully!" : "Failed to update member.");

            if (success) handleRevert();

        } catch (InvalidInputException | DAOException e) {
            showAlert(Alert.AlertType.ERROR, "Update Error", e.getMessage());
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