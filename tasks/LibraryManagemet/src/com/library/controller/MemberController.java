package com.library.controller;

import com.library.domain.Member;
import com.library.service.LibraryService;
import com.library.service.LibraryServiceImplementation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class MemberController {

    @FXML private TextField name;
    @FXML private TextField email;
    @FXML private TextField mobile;
    @FXML private TextField address;

    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private RadioButton other;
    @FXML private Button backButton;

    private ToggleGroup genderGroup;

    private final LibraryServiceImplementation libraryService = new LibraryServiceImplementation(); // assumes default "member" table

    @FXML
    public void initialize() {
        genderGroup = new ToggleGroup();
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
        other.setToggleGroup(genderGroup);
    }

    @FXML
    private void saveMember() {
        String nameText = name.getText().trim();
        String emailText = email.getText().trim();
        String mobileText = mobile.getText().trim();
        String addressText = address.getText().trim();
        Toggle selectedToggle = genderGroup.getSelectedToggle();

        // === Validation ===
        if (nameText.isEmpty()) {
            showAlert("Name is required.");
            return;
        }

        if (!emailText.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            showAlert("Invalid email format.");
            return;
        }

        if (!mobileText.matches("\\d{10}")) {
            showAlert("Mobile number must be exactly 10 digits.");
            return;
        }

        if (selectedToggle == null) {
            showAlert("Please select a gender.");
            return;
        }

        if (addressText.isEmpty()) {
            showAlert("Address is required.");
            return;
        }

        // Create Member object
        char gender = selectedToggle.getUserData().toString().charAt(0);
        Member member = new Member(nameText, emailText, Long.parseLong(mobileText), gender, addressText);

        boolean success = libraryService.addMember(member);
        if (success) {
            showAlert("Member added successfully!", AlertType.INFORMATION);
            clearFields();
        } else {
            showAlert("Failed to add member. Please try again later.");
        }
    }


    private void showAlert(String message) {
        showAlert(message, AlertType.WARNING);
    }

    private void showAlert(String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Member Form");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        name.clear();
        email.clear();
        mobile.clear();
        address.clear();
        genderGroup.selectToggle(null);
    }
    @FXML
    private void handleBack() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/com/library/UI/Home.fxml"));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) backButton.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.setTitle("Library System - Home");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
