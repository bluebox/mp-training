package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import domain.Member;
import domain.checking_enum.Gender;
import Service.MemberService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateMembersController implements Initializable {

    @FXML
    public TextField memberid, nameField, emailField, mobileField, addressField;

    @FXML
    public ComboBox<String> genderBox;

    @FXML
    public MemberService memberService=new MemberService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> items = FXCollections.observableArrayList("Male", "Female");
        genderBox.setItems(items);
        genderBox.setPromptText("Select Gender");
    }

    @FXML
    public void handleAddMember() throws Exception {
        try {
            String idText = memberid.getText();
            if (idText == null || idText.trim().isEmpty()) {
                showAlert("Member ID cannot be empty.");
                return;
            }
            int memberId = Integer.parseInt(idText.trim());

            String name = nameField.getText();
            String email = emailField.getText();
            String mobileText = mobileField.getText();
            String address = addressField.getText();
            String selectedGender = genderBox.getValue();

            if (name == null || name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || mobileText == null || mobileText.trim().isEmpty()
                || address == null || address.trim().isEmpty()
                || selectedGender == null || selectedGender.trim().isEmpty()) {
                showAlert("Please fill in all fields.");
                return;
            }

            if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                showAlert("Invalid email format. Please try again.");
                return;
            }

            if (!mobileText.matches("\\d{10}")) {
                showAlert("Mobile number must be exactly 10 digits.");
                return;
            }
            Long mobile = Long.parseLong(mobileText);

            Gender gender = selectedGender.equalsIgnoreCase("Male") ? Gender.MALE : Gender.FEMALE;

            Member member = new Member(name, email, mobile, address, gender);

            boolean updated = memberService.updateMember(member, memberId);
            if (updated) {
                showAlert("Member updated successfully.");
                Stage stage = (Stage) nameField.getScene().getWindow();
               // stage.close(); 
            } else {
                showAlert("Failed to update member. Please try again.");
            }

        } catch (NumberFormatException e) {
            showAlert("Invalid number format. Check Member ID or Mobile.");
            e.printStackTrace();
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goToPreviousScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/applicationView/ViewAllMembers.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
