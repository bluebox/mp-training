package controller;

import domain.Gender;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.MemberManagementService;
import util.ValidationUtil;

import java.time.format.DateTimeFormatter;

public class UpdateMemberController {

    @FXML private TextField memberIdField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField mobileField;
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private RadioButton otherRadio;
    @FXML private TextArea addressArea;
    @FXML private Button updateButton;
    @FXML private Label addedByLabel;
    @FXML private Label dateAddedLabel;

    private ToggleGroup genderGroup;
    private Member member;

    private final MemberManagementService service = new MemberManagementService();

    @FXML
    private void initialize() {
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        otherRadio.setToggleGroup(genderGroup);
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        try {
            if (member == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "No member data loaded.");
                return;
            }

            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String mobile = mobileField.getText().trim();
            String address = addressArea.getText().trim();

            ValidationUtil.validateNotEmpty("Name", name);
            ValidationUtil.validateEmail(email);
            ValidationUtil.validatePhone(mobile);
            ValidationUtil.validateNotEmpty("Address", address);

            Gender gender = getSelectedGender();

            member.setName(name);
            member.setEmail(email);
            member.setMobile(mobile);
            member.setGender(gender);
            member.setAddress(address);

            if (service.updateMember(member)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Member updated successfully.");
                closeWindow();
            } else {
                showAlert(Alert.AlertType.ERROR, "Duplicate Error", "Email or mobile already exists.");
            }

        } catch (IllegalArgumentException ex) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + ex.getMessage());
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/MemberManagement.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void closeWindow() {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Gender getSelectedGender() {
        if (maleRadio.isSelected()) return Gender.Male;
        if (femaleRadio.isSelected()) return Gender.Female;
        return Gender.Other;
    }

    public void setMemberData(Member member) {
        this.member = member;
        memberIdField.setText(String.valueOf(member.getMemberId()));
        nameField.setText(member.getName());
        emailField.setText(member.getEmail());
        mobileField.setText(member.getMobile());
        addressArea.setText(member.getAddress());

        switch (member.getGender()) {
            case Male : maleRadio.setSelected(true);
            case Female : femaleRadio.setSelected(true);
            default : otherRadio.setSelected(true);
        }

        if (addedByLabel != null) {
            addedByLabel.setText("Added By: " + member.getAddedBy());
        }

        if (dateAddedLabel != null && member.getDateAdded() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateAddedLabel.setText("Date Added: " + member.getDateAdded().format(formatter));
        }
    }
}