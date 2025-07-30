package library.ui;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import library.exception.LibraryException;
import library.model.Member;
import library.model.enums.Gender;
import library.service.MemberServiceImpl;

public class AddMemberForm {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private RadioButton otherRadioButton; 

    @FXML
    private ToggleGroup genderToggleGroup;

    @FXML
    private TextField addressField;

    @FXML
    private Label messageLabel;

    private MemberServiceImpl memberService;

    @FXML
    public void initialize() {
        memberService = new MemberServiceImpl();
        if (genderToggleGroup == null) {
            genderToggleGroup = new ToggleGroup();
        }
        maleRadioButton.setToggleGroup(genderToggleGroup);
        femaleRadioButton.setToggleGroup(genderToggleGroup);
        otherRadioButton.setToggleGroup(genderToggleGroup); 
    }

    @FXML
    private void handleAddMember(ActionEvent event) {
        messageLabel.setText("");
        messageLabel.setTextFill(Color.RED);

        if (nameField.getText().isEmpty() || emailField.getText().isEmpty()
                || phoneNumberField.getText().isEmpty() || genderToggleGroup.getSelectedToggle() == null
                || addressField.getText().isEmpty()) {
            messageLabel.setText("Please fill in all fields.");
            return;
        }

        try {
            String name = nameField.getText();
            String email = emailField.getText();

            if (!phoneNumberField.getText().matches("\\d+")) {
                messageLabel.setText("Phone number cannot be zero or negative.");
                return;
            }
            long phoneNumber = Long.parseLong(phoneNumberField.getText());

            Gender gender;
            if (maleRadioButton.isSelected()) {
                gender = Gender.MALE;
            } else if (femaleRadioButton.isSelected()) {
                gender = Gender.FEMALE;
            } else if (otherRadioButton.isSelected()) { 
                gender = Gender.OTHER;
            } else {
                messageLabel.setText("Gender must be 'MALE', 'FEMALE', or 'OTHER'."); 
                return;
            }

            String address = addressField.getText();

            Member newMember = new Member(0, name, email, phoneNumber, gender, address);
            String resultMessage = memberService.addMember(newMember);

            messageLabel.setText(resultMessage);
            messageLabel.setTextFill(Color.GREEN);
            clearFields();

        } catch (NumberFormatException e) {
            messageLabel.setText("Phone number cannot be zero or negative.");
        } catch (LibraryException e) {
            messageLabel.setText(e.getMessage());
            messageLabel.setTextFill(Color.RED);
        } catch (Exception e) {
            messageLabel.setText("Error adding member: " + e.getMessage());
            messageLabel.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToMainMenu(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            URL fxmlLocation = getClass().getClassLoader().getResource("MainScreen.fxml");
            if (fxmlLocation == null) {
                System.err.println("Failed to load MainScreen.fxml: MainScreen.fxml not found on classpath.");
                messageLabel.setText("Error: Could not load Main Menu screen.");
                messageLabel.setTextFill(Color.RED);
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Scene scene = new Scene(root, 900, 600);
            stage.setScene(scene);
            stage.setTitle("Library Management System - Main Menu");
            stage.show();

        } catch (IOException e) {
            System.err.println("Failed to load MainScreen.fxml: " + e.getMessage());
            messageLabel.setText("Error navigating to Main Menu.");
            messageLabel.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        phoneNumberField.clear();
        if (genderToggleGroup.getSelectedToggle() != null) {
            genderToggleGroup.getSelectedToggle().setSelected(false);
        }
        addressField.clear();
    }
}