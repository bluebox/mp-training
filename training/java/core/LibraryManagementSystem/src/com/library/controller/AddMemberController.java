package com.library.controller;

import java.io.IOException;

import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddMemberController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField mobileField;
    @FXML private ChoiceBox<String> genderChoice;
    @FXML private TextArea addressArea;
    @FXML private Label statusLabel;

    private final MemberServiceImplementation service = new MemberServiceImplementation();

    @FXML
    public void initialize() {
        genderChoice.getItems().addAll("M", "F");
    }

    @FXML
    private void handleBack() throws IOException {
        MainController.switchScene("MainDashboard.fxml");
    }

    @FXML
    public void handleAddMember() {
        statusLabel.setStyle("-fx-text-fill: red;");

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String mobileStr = mobileField.getText().trim();
        String gender = genderChoice.getValue();
        String address = addressArea.getText().trim();

        if (name.isEmpty() || email.isEmpty() || mobileStr.isEmpty() || gender == null || address.isEmpty()) {
            statusLabel.setText("All fields are required.");
            return;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            statusLabel.setText("Name must contain only letters.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            statusLabel.setText("Invalid email format.");
            return;
        }

        if (!mobileStr.matches("\\d{10}")) {
            statusLabel.setText("Mobile must be 10 digits.");
            return;
        }

        if (address.length() < 5) {
            statusLabel.setText("Address must be at least 5 characters.");
            return;
        }

        try {
            long mobile = Long.parseLong(mobileStr);
            Member member = new Member(name, email, mobile, gender, address);

            boolean success = service.registerMember(member);
            if (success) {
                statusLabel.setStyle("-fx-text-fill: green;");
                statusLabel.setText("Member added successfully!");
                clearForm();
            } else {
                statusLabel.setText("Failed to add member.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void handleCancel() {
        ((Stage) nameField.getScene().getWindow()).close();
    }

    private void clearForm() {
        nameField.clear();
        emailField.clear();
        mobileField.clear();
        genderChoice.setValue(null);
        addressArea.clear();
    }
}
