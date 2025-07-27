package com.library.controller;

import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;
import com.library.service.interfaces.MemberService;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EditMemberController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField mobileField;
    @FXML private TextField addressField;
    @FXML private Label statusLabel;
    @FXML private ChoiceBox<String> genderChoice;

    private final MemberService memberService = new MemberServiceImplementation();
    private Member member;

    @FXML
    public void initialize() {
        genderChoice.setItems(FXCollections.observableArrayList("M", "F"));
    }

    public void setMember(Member member) {
        this.member = member;

        nameField.setText(member.getName());
        emailField.setText(member.getEmail());
        mobileField.setText(String.valueOf(member.getMobile()));
        genderChoice.setValue(member.getGender());
        addressField.setText(member.getAddress());
    }

    @FXML
    private void handleUpdate() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String mobileStr = mobileField.getText().trim();
        String gender = genderChoice.getValue();
        String address = addressField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || mobileStr.isEmpty() || gender == null || address.isEmpty()) {
            statusLabel.setText("All fields are required.");
            return;
        }
        if (!name.matches("^[A-Za-z ]+$")) {
            statusLabel.setText("Name must contain only letters and spaces.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            statusLabel.setText("Invalid email format.");
            return;
        }

        if (!mobileStr.matches("\\d{10}")) {
            statusLabel.setText("Mobile must be a 10-digit number.");
            return;
        }

        if (address.length() < 6) {
            statusLabel.setText("Address must be at least 6 characters.");
            return;
        }

        long mobile = Long.parseLong(mobileStr);

        boolean isChanged =
            !name.equals(member.getName()) ||
            !email.equals(member.getEmail()) ||
            mobile != member.getMobile() ||
            !gender.equals(member.getGender()) ||
            !address.equals(member.getAddress());

        if (!isChanged) {
            statusLabel.setText("No changes made.");
            return;
        }

        try {
            member.setName(name);
            member.setEmail(email);
            member.setMobile(mobile);
            member.setGender(gender);
            member.setAddress(address);

            boolean updated = memberService.modifyMember(member);

            if (updated) {
                statusLabel.setText("Member updated successfully.");

                // close after 1 sec
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(e -> ((Stage) nameField.getScene().getWindow()).close());
                delay.play();
            } else {
                statusLabel.setText("Update failed.");
            }
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        ((Stage) nameField.getScene().getWindow()).close();
    }
}
