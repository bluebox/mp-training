package com.lms.controller;

import com.lms.exceptions.DAOException;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;
import com.lms.serviceImpl.MemberService;
import com.lms.util.Validator;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddMemberController {

    @FXML private TextField name;
    @FXML private TextField mailId;
    @FXML private TextField mobileNumber;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TextArea addressArea;
    @FXML private Button addButton;

    private final MemberService memberService = new MemberService();

    @FXML
    public void initialize() {
        genderComboBox.getItems().addAll("Male", "Female", "Other");
    }

    @FXML
    private void handleAddMember() {
    	Member member = new Member();
		member.setName(name.getText().trim()); 
		member.setEmail(mailId.getText().trim());
		member.setMobile(mobileNumber.getText().trim());
		member.setGender(genderComboBox.getValue());
		member.setAddress(addressArea.getText().trim());
	        try {
            Validator.validateMember(member); // Controller-level validation
            boolean success = memberService.addMember(member); // Service + DAO + uniqueness check

            showAlert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR,
                      success ? "Success" : "Failure",
                      success ? "Member added successfully!" : "Failed to add member.");

            if (success) {
                clearForm();
            }

        } catch (InvalidInputException | DAOException e) {
            showAlert(Alert.AlertType.ERROR, "Validation or DB Error", e.getMessage());
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