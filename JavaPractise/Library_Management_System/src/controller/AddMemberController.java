package controller;

import domain.Gender;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.MemberManagementService;
import util.ValidationUtil;

import java.time.LocalDateTime;

public class AddMemberController {

	@FXML
	private TextField nameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField mobileField;
	@FXML
	private RadioButton maleRadio;
	@FXML
	private RadioButton femaleRadio;
	@FXML
	private RadioButton otherRadio;
	@FXML
	private TextArea addressArea;

	private ToggleGroup genderGroup;
	private final MemberManagementService service = new MemberManagementService();

	@FXML
	private void initialize() {
		genderGroup = new ToggleGroup();
		maleRadio.setToggleGroup(genderGroup);
		femaleRadio.setToggleGroup(genderGroup);
		otherRadio.setToggleGroup(genderGroup);
	}

	@FXML
	private void handleSubmit(ActionEvent event) {
		try {
			String name = nameField.getText().trim();
			String email = emailField.getText().trim();
			String mobile = mobileField.getText().trim();
			String address = addressArea.getText().trim();

			if (genderGroup.getSelectedToggle() == null) {
				throw new IllegalArgumentException("Please select a gender.");
			}

			ValidationUtil.validateNotEmpty("Name", name);
			ValidationUtil.validateEmail(email);
			ValidationUtil.validatePhone(mobile);
			ValidationUtil.validateNotEmpty("Address", address);

			Gender gender = getSelectedGender();

			Member member = new Member();
			member.setName(name);
			member.setEmail(email);
			member.setMobile(mobile);
			member.setGender(gender);
			member.setAddress(address);
			member.setDateAdded(LocalDateTime.now());
			member.setAddedBy("ADMIN");

			boolean isAdded = service.registerMember(member);

			if (isAdded) {
				showAlert(Alert.AlertType.INFORMATION, "Success", "Member added successfully!");
				clearForm();
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Email or mobile already exists.");
			}

		} catch (IllegalArgumentException ex) {
			showAlert(Alert.AlertType.ERROR, "Invalid Input", ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			showAlert(Alert.AlertType.ERROR, "Error", "Unexpected error: " + ex.getMessage());
		}
	}

	private Gender getSelectedGender() {
		if (maleRadio.isSelected())
			return Gender.Male;
		if (femaleRadio.isSelected())
			return Gender.Female;
		return Gender.Other;
	}

	private void clearForm() {
		nameField.clear();
		emailField.clear();
		mobileField.clear();
		addressArea.clear();
		genderGroup.selectToggle(null);
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	private void handleBack(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/resources/MemberManagement.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not navigate back.");
		}
	}
}