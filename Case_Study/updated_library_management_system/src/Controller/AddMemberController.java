package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import domain.Member;
import domain.checking_enum.Gender;
import Service.MemberService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

public class AddMemberController implements Initializable {

    @FXML
    private TextField nameField, emailField, mobileField, addressField;

    @FXML
    private ComboBox<String> genderBox;

    private final MemberService memberService = new MemberService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderBox.getItems().addAll("Male", "Female");
    }

    @FXML
    private void handleAddMember() {
        try {
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
            if (selectedGender == null) {
                showAlert("Please select a gender.");
                return;
            }
            long mobile = Long.parseLong(mobileText);

            Gender gender = selectedGender.equalsIgnoreCase("Male") ? Gender.MALE : Gender.FEMALE;

            Member member = new Member(name, email, mobile, address, gender);
            System.out.println(member);
            boolean added = memberService.addMember(member);

            if (added) {
                showAlert("Member added successfully.");
                Stage stage = (Stage) nameField.getScene().getWindow();
                stage.close();
            } else {
                showAlert("Failed to add member. Please try again.");
            }

        } catch (NumberFormatException e) {
            showAlert("Mobile number must be a valid integer.");
        }
        catch(SQLIntegrityConstraintViolationException e) {
        	System.out.println("hi hello how are you");
            showAlert("duplicate entry.");

        	
        }
        
        catch (Exception e) {
            showAlert("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    public void goToPreviousScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/applicationView/ViewAllMembers.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Unable to return to previous screen.");
        }
    }
}
