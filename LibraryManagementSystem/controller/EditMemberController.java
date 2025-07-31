package com.library.controller;

import java.io.IOException;

import com.library.model.Member;
import com.library.service.impl.MemberServiceImplementation;
import com.library.service.interfaces.MemberService;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class EditMemberController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField mobileField;
    @FXML private TextField addressField;
    @FXML private Label statusLabel;
    @FXML private ChoiceBox<String> genderChoice;

    private final MemberService memberService = new MemberServiceImplementation();
    private static Member selectedMember;
    private Member member; // actual working reference

    // 🔁 Set from ViewMembersController before switching
    public static void setSelectedMember(Member member) {
        selectedMember = member;
    }

    @FXML
    public void initialize() {
        genderChoice.setItems(FXCollections.observableArrayList("Male", "Female"));

        // 🔁 Use static member passed from ViewMembersController
        if (selectedMember != null) {
            this.member = selectedMember;

            nameField.setText(member.getName());
            emailField.setText(member.getEmail());
            mobileField.setText(String.valueOf(member.getMobile()));
            addressField.setText(member.getAddress());

            if (member.getGender().equalsIgnoreCase("M")) {
                genderChoice.setValue("Male");
            } else if (member.getGender().equalsIgnoreCase("F")) {
                genderChoice.setValue("Female");
            }
        }
    }

    @FXML
    private void handleUpdate() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String mobileStr = mobileField.getText().trim();
        String genderText = genderChoice.getValue();
        String address = addressField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || mobileStr.isEmpty() || genderText == null || address.isEmpty()) {
            showTemporaryMessage("All fields are required.", "red");
            return;
        }

        if (!name.matches("^[A-Za-z ]+$")) {
            showTemporaryMessage("Name must contain only letters and spaces.", "red");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            showTemporaryMessage("Invalid email format.", "red");
            return;
        }

        if (!mobileStr.matches("\\d{10}")) {
            showTemporaryMessage("Mobile must be a 10-digit number.", "red");
            return;
        }

        if (address.length() < 6) {
            showTemporaryMessage("Address must be at least 6 characters.", "red");
            return;
        }

        long mobile = Long.parseLong(mobileStr);
        String genderCode = genderText.equals("Male") ? "M" : "F";

        boolean isChanged =
            !name.equals(member.getName()) ||
            !email.equals(member.getEmail()) ||
            mobile != member.getMobile() ||
            !genderCode.equals(member.getGender()) ||
            !address.equals(member.getAddress());

        if (!isChanged) {
            showTemporaryMessage("No changes made.", "orange");
            return;
        }

        try {
            member.setName(name);
            member.setEmail(email);
            member.setMobile(mobile);
            member.setGender(genderCode);
            member.setAddress(address);

            boolean updated = memberService.modifyMember(member);
            
            statusLabel.setStyle("-fx-text-fill:green;");
            statusLabel.setText("Book updated successfully!");

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
//                Stage stage = (Stage) titleField.getScene().getWindow();
//                stage.close();
            	try {
					MainController.switchScene("ViewMembers.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch bloc	k
					e.printStackTrace();
				}
            });
            pause.play();

//            if (updated) {
//                showTemporaryMessage("Member updated successfully.", "green");
//                MainController.switchScene("ViewMembers.fxml");
//            } else {
//                showTemporaryMessage("Update failed.", "red");
//            }
        } catch (Exception e) {
            showTemporaryMessage("Error: " + e.getMessage(), "red");
            e.printStackTrace();
        }
    }

    private void showTemporaryMessage(String message, String color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + color + ";");

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> statusLabel.setText(""));
        pause.play();
    }

    @FXML
    private void handleCancel() {
        try {
            MainController.switchScene("ViewMembers.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleBack() throws IOException {
        MainController.switchScene("ViewMembers.fxml");
    }
    @FXML
    private void handleBackDash() throws IOException {
        MainController.switchScene("MainDashboard.fxml");
    }
}
