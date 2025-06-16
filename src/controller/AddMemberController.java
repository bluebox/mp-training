package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Member;
import service.MemberService;

public class AddMemberController {

    @FXML private TextField memberIdField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField mobileField;
    @FXML private ComboBox<String> genderBox;
    @FXML private TextField addressField;

    private final MemberService memberService = new MemberService();

    @FXML
    public void initialize() {
        genderBox.getItems().addAll("Male", "Female", "Other");
    }

    @FXML
    private void handleAddMember() {
        String memberId = memberIdField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String mobileStr = mobileField.getText();
        String gender = genderBox.getValue();
        String address = addressField.getText();

        if (memberId.isEmpty() || name.isEmpty() || email.isEmpty() || mobileStr.isEmpty() || gender == null || address.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        try {
            long mobile = Long.parseLong(mobileStr);

            Member member = new Member();
            
            member.setName(name);
            member.setEmail(email);
            member.setMobile(mobile);
            member.setGender(gender.charAt(0));
            member.setAddress(address);

            memberService.addMember(member);
            showAlert("Member added successfully!");

            // Clear fields
            memberIdField.clear();
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
