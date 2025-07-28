package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import domain.Gender;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import service.MemberManagementService;

public class AddMemberController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField mobileField;
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private RadioButton otherRadio;
    private ToggleGroup genderGroup;
    @FXML
    private void initialize() {
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        otherRadio.setToggleGroup(genderGroup);
    }

    @FXML private TextArea addressArea;
    @FXML
    private void handleSubmit(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        String mobile = mobileField.getText();
        String address = addressArea.getText();
        Gender gender;
        if (maleRadio.isSelected()) {
            gender = Gender.M;
        } else if (femaleRadio.isSelected()) {
            gender = Gender.F;
        } else {
            gender = Gender.OTHER;
        }
        try
        {
        Member member = new Member(name, email, mobile, gender, address);
        MemberManagementService service = new MemberManagementService();
        boolean isAdded = service.registerMember(member);
        if (isAdded) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Member added successfully!");
            clearForm();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "email or mobile already exists");
        }
        }
        catch (IllegalArgumentException ex) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", ex.getMessage());
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void clearForm() {
        nameField.clear();
        emailField.clear();
        mobileField.clear();
        addressArea.clear();
        genderGroup.selectToggle(null);
    }
    @FXML
    private void handleBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/MemberManagement.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}