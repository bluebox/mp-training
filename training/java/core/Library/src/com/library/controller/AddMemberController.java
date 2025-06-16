package com.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import com.library.dao.Member;
import com.library.enums.Gender;
import com.library.service.MemberService;

public class AddMemberController {
	@FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField mobileField;
    @FXML private ComboBox<String> genderBox;
    @FXML private TextField addressField;

    private final MemberService memberService = new MemberService();

    @FXML
    public void initialize() {
        genderBox.getItems().addAll("M", "F");
    }

    @FXML
    private void handleAddMember() {
        String name = nameField.getText();
        String email = emailField.getText();
        long mobile = Long.parseLong(mobileField.getText());
        String gender = genderBox.getValue();
        String address = addressField.getText();

        if (name.isEmpty() || email.isEmpty() || ((mobile>=1000000000L)&&(mobile<=9999999999L)) || gender == null || address.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }
        try {
            Member member = new Member(223445667,name,email,mobile,gender.charAt(0)=='M'?Gender.Male:Gender.Female,address);
            memberService.addMember(member);
            showAlert("Member added successfully!");
            nameField.clear();
            emailField.clear();
            mobileField.clear();
            genderBox.getSelectionModel().clearSelection();
            addressField.clear();
        } catch (NumberFormatException e) {
            showAlert("Mobile number must be numeric.");
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
        }
    }
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Member");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}