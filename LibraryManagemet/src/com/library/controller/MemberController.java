package com.library.controller;

import com.library.domain.Member;
import com.library.service.MemberService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MemberController {

    @FXML private TextField name;
    @FXML private TextField email;
    @FXML private TextField mobile;
    @FXML private TextField address;
    @FXML private Label titleLabel;

    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private RadioButton other;
    private ToggleGroup gender;

    private boolean isUpdateMode = false;
    private Member selectedMember = null;

    private final MemberService service = new MemberService();

    @FXML
    public void initialize() {
        gender = new ToggleGroup();
        male.setToggleGroup(gender);
        female.setToggleGroup(gender);
        other.setToggleGroup(gender);

        // Set gender user data
        male.setUserData("M");
        female.setUserData("F");
        other.setUserData("O");
    }

    /** Called by HomeController for Add operation */
    public void setAddMode() {
        // If you touch any @FXML fields here, make sure they are not null
        isUpdateMode = false;
        selectedMember = null;

        if (titleLabel != null) {
            titleLabel.setText("Add New Member");
        }
    }


    public void setUpdateMode(Member member) {
        isUpdateMode = true;
        selectedMember = member;
        if(titleLabel!=null)
            titleLabel.setText("Update Member Details");

        // Pre-fill fields
        name.setText(member.getName());
        email.setText(member.getEmail());
        mobile.setText(String.valueOf(member.getMobile()));
        address.setText(member.getAddress());

        // Set selected gender
        for (Toggle toggle : gender.getToggles()) {
            if (toggle.getUserData().toString().charAt(0) == member.getGender()) {
                gender.selectToggle(toggle);
                break;
            }
        }
    }

    @FXML
    private void saveMember() {
        try {
            String memberName = getTextSafely(name);
            String memberEmail = getTextSafely(email);
            String mobileInput = getTextSafely(mobile);
            String memberAddress = getTextSafely(address);
            Toggle selectedGender = gender.getSelectedToggle();

            // Validation
            if (memberName.isEmpty() || memberEmail.isEmpty() || mobileInput.isEmpty()
                    || memberAddress.isEmpty() || selectedGender == null) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all fields.");
                return;
            }

            long memberMobile;
            try {
                memberMobile = Long.parseLong(mobileInput);
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Mobile number must be numeric.");
                return;
            }

            String memberGender = selectedGender.getUserData().toString();

            if (isUpdateMode) {
                if (selectedMember == null) {
                    showAlert(Alert.AlertType.ERROR, "Error", "No member selected for update.");
                    return;
                }

                selectedMember.setName(memberName);
                selectedMember.setEmail(memberEmail);
                selectedMember.setMobile(memberMobile);
                selectedMember.setGender(memberGender.charAt(0));
                selectedMember.setAddress(memberAddress);

                service.updateMember(selectedMember);
            } else {
                Member newMember = new Member(memberName, memberEmail, memberMobile, memberGender.charAt(0), memberAddress);
                service.addMember(newMember);
            }

            closeWindow();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Unexpected Error", "An error occurred while saving member:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private String getTextSafely(TextField field) {
        return field.getText() != null ? field.getText().trim() : "";
    }

    private void closeWindow() {
        Stage stage = (Stage) name.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
