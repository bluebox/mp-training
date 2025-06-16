package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ResourceBundle;

import enums.Gender;
import model.Member;
import service.MemberService;

public class AddMemberController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField mobileField;
    @FXML
    private ComboBox<Gender> genderBox;
    @FXML
    private TextField addressField;

    private MemberService memberService = new MemberService();

    @FXML
    private void handleSave() {
        try {
            String name = nameField.getText();
            String email = emailField.getText();
            String mobileStr = mobileField.getText();
            Gender gender = genderBox.getValue();
            String address = addressField.getText();

            if (name.isEmpty() || email.isEmpty() || mobileStr.isEmpty() || gender == null || address.isEmpty()) {
                showAlert("All fields are required,");
                return;
            }

            long mobile = Long.parseLong(mobileStr);

            Member member = new Member(0, name, email, mobile, gender, address);
            boolean success = memberService.registerMember(member);
            if (success)
                showAlert("Member added succssfully");
            else
                showAlert("Failed to add member");

        } catch (NumberFormatException e) {
            showAlert("Mobile number must be numeric");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderBox.getItems().addAll(Gender.values());
    }
}
