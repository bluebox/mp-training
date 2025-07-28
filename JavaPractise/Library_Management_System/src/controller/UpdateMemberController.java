package controller;

import java.io.IOException;
import domain.Gender;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import service.MemberManagementService;

public class UpdateMemberController {
	@FXML private TextField memberIdField;
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
    @FXML private Button updateButton;
    @FXML
    private void handleUpdate() {
    	int memberId = Integer.parseInt(memberIdField.getText().trim());
		String name=nameField.getText().trim();
        String email = emailField.getText().trim();
        String mobile = mobileField.getText().trim();
        Gender gender;
        if (maleRadio.isSelected()) {
            gender = Gender.M;
        } else if (femaleRadio.isSelected()) {
            gender = Gender.F;
        } else {
            gender = Gender.OTHER;
        }
        String address = addressArea.getText().trim();
        if (email.isEmpty() || mobile.isEmpty() || address.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all fields.");
            return;
        }
        try {
            Member member = new Member(name, email, mobile, gender, address);
            MemberManagementService service = new MemberManagementService();
             boolean success=service.updateMember(memberId, member);
             if(success)
             {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Member updated successfully!");
            clearForm();
            closeWindow();
             }
             else
             {
            	 showAlert(Alert.AlertType.ERROR, "Update Failed", "Member update failed. Please try again.");
             }
        } catch (IllegalArgumentException ex) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", ex.getMessage());
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }
        }
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/resources/MemberManagement.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    private void closeWindow() {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }
    private void clearForm() {
    	memberIdField.clear();
        nameField.clear();
        emailField.clear();
        mobileField.clear();
        addressArea.clear();
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}