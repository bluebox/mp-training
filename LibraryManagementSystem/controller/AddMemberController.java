package com.library.controller;

import java.io.IOException;

import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        genderChoice.getItems().addAll("Male", "Female");
    }

    @FXML
    private void handleBack() throws IOException {
        MainController.switchScene("MainDashboard.fxml");
    }

    @FXML
    public void handleAddMember() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String mobileStr = mobileField.getText().trim();
        String gender = genderChoice.getValue();
        String address = addressArea.getText().trim();

        if (name.isEmpty() || email.isEmpty() || mobileStr.isEmpty() || gender == null || address.isEmpty()) {
            showTemporaryMessage("All fields are required.", "red");
            return;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            showTemporaryMessage("Name must contain only letters.", "red");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showTemporaryMessage("Invalid email format.", "red");
            return;
        }

        if (!mobileStr.matches("\\d{10}")) {
            showTemporaryMessage("Mobile must be 10 digits.", "red");
            return;
        }

        if (address.length() < 5) {
            showTemporaryMessage("Address must be at least 5 characters.", "red");
            return;
        }

        try {
            long mobile = Long.parseLong(mobileStr);
            char genderChar = gender.equals("Male") ? 'M' : 'F';

            Member member = new Member(name, email, mobile, String.valueOf(genderChar), address);

            if (service.doesMemberExist(email, mobile)) {
                showTemporaryMessage("Member with this email or phone already exists.", "red");
                return;
            }

            boolean success = service.registerMember(member);
            if (success) {
                showTemporaryMessage("Member added successfully!", "green");
                clearForm();
            } else {
                showTemporaryMessage("Failed to add member.", "red");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showTemporaryMessage("Error: " + e.getMessage(), "red");
        }
    }

    private void clearForm() {
        nameField.clear();
        emailField.clear();
        mobileField.clear();
        genderChoice.setValue(null);
        addressArea.clear();
    }

    @FXML
    public void handleCancel() throws IOException {
//        ((Stage) nameField.getScene().getWindow()).close();
    	MainController.switchScene("MainDashboard.fxml");
    }

    private void showTemporaryMessage(String message, String color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + color + ";");

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> statusLabel.setText(""));
        pause.play();
    }
}
