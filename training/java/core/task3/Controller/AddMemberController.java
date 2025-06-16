package Controller;

import casestudy.Member;
import Service.MemberService;
import casestudy.LibraryException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMemberController {
    @FXML private TextField nameField, emailField, mobileField, addressField;
    @FXML private ComboBox<String> genderCombo;
    private MemberService memberService = new MemberService();
    private Stage primaryStage;

    public AddMemberController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleAddMember() {
        try {
            String genderValue = genderCombo.getValue();
            if (genderValue == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select a gender");
                return;
            }
            
            Member member = new Member(
                nameField.getText(),
                emailField.getText(),
                Long.parseLong(mobileField.getText()),
                genderValue.charAt(genderValue.length() - 2),
                addressField.getText()
            );
            memberService.addMember(member);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Member added successfully");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid mobile number format");
        } catch (LibraryException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void goBack() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        MainController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleClear() {
        nameField.clear();
        emailField.clear();
        mobileField.clear();
        addressField.clear();
        genderCombo.setValue(null);
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        mobileField.clear();
        addressField.clear();
        genderCombo.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}