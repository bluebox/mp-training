package com.casestudy.fx;

import com.casestudy.domain.Gender;
import com.casestudy.domain.Member;
import com.casestudy.serviceimpl.Service;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UpdateMemberForm extends VBox {

    private final Service service = new Service();

    public UpdateMemberForm() {
        this.setPadding(new Insets(10));
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        TextField memberIdField = new TextField();
        Button fetchButton = new Button("Fetch Member");

        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField mobileField = new TextField();
        ComboBox<String> genderBox = new ComboBox<>();
        TextField addressField = new TextField();
        Button updateButton = new Button("Update Member");
        Button resetButton = new Button("Reset");
        

        // Initial disabling
        nameField.setDisable(true);
        emailField.setDisable(true);
        mobileField.setDisable(true);
        genderBox.setDisable(true);
        addressField.setDisable(true);
        updateButton.setDisable(true);
        resetButton.setDisable(true);

        genderBox.getItems().addAll("M - Male", "F - Female");

        // Limiters
        addLiveLimiter(memberIdField, 10, true);
        addNameLimiter(nameField, 50);
        addEmailLimiter(emailField, 50);
        addLiveLimiter(mobileField, 10, true);
        addLiveLimiter(addressField, 200);

        fetchButton.setOnAction(e -> {
            String memberIdText = memberIdField.getText().trim();
            if (memberIdText.isEmpty()) {
                UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter a Member ID.");
                return;
            }

            try {
                int memberId = Integer.parseInt(memberIdText);
                Member member = service.getMemberById(memberId);

                if (member != null) {
                    nameField.setText(member.getName());
                    emailField.setText(member.getEmail());
                    mobileField.setText(String.valueOf(member.getMobile()));
                    genderBox.setValue(member.getGender() == Gender.MALE ? "M - Male" : "F - Female");
                    addressField.setText(member.getAddress());

                    // Enable fields
                    memberIdField.setDisable(true);
                    nameField.setDisable(false);
                    emailField.setDisable(false);
                    mobileField.setDisable(false);
                    genderBox.setDisable(false);
                    addressField.setDisable(false);
                    updateButton.setDisable(false);
                    resetButton.setDisable(false);
                } else {
                    UtilMethods.showAlert(Alert.AlertType.ERROR, "Not Found", "Member ID not found.");
                }
            } catch (NumberFormatException ex) {
                UtilMethods.showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid Member ID format.");
            }
        });
        
        resetButton.setOnAction(e->{
        	clearFields(memberIdField, nameField, emailField, mobileField, genderBox, addressField);

            // Disable fields again
        	memberIdField.setDisable(false);
            nameField.setDisable(true);
            emailField.setDisable(true);
            mobileField.setDisable(true);
            genderBox.setDisable(true);
            addressField.setDisable(true);
            updateButton.setDisable(true);
            resetButton.setDisable(true);
        });

        updateButton.setOnAction(e -> {
            try {
                int memberId = Integer.parseInt(memberIdField.getText().trim());
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                long mobile = Long.parseLong(mobileField.getText().trim());
                String genderStr = genderBox.getValue();
                String address = addressField.getText().trim();

                if (name.isEmpty() || email.isEmpty() || genderStr == null || address.isEmpty()) {
                    UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields.");
                    return;
                }

                if (!UtilMethods.isValidEmail(email)) {
                    UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Invalid email format.");
                    return;
                }

                String gender = genderStr.substring(0, 1); // M or F

                Member member = new Member(memberId, name, email, mobile, Gender.fromCode(gender), address);

                if (service.updateMemberService(member)) {
                    UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Member updated successfully!");
                    clearFields(memberIdField, nameField, emailField, mobileField, genderBox, addressField);

                    // Disable fields again
                    memberIdField.setDisable(false);
                    nameField.setDisable(true);
                    emailField.setDisable(true);
                    mobileField.setDisable(true);
                    genderBox.setDisable(true);
                    addressField.setDisable(true);
                    updateButton.setDisable(true);
                    resetButton.setDisable(true);
                } else {
                    UtilMethods.showAlert(Alert.AlertType.ERROR, "Failure", "Failed to update member.");
                }
            } catch (NumberFormatException ex) {
                UtilMethods.showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid input.");
            }

            System.out.println("Updated Member ID: " + memberIdField.getText());
        });

        // UI Layout
        grid.add(new Label("Member ID:"), 0, 0);
        grid.add(memberIdField, 1, 0);
        grid.add(fetchButton, 2, 0);

        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Mobile:"), 0, 3);
        grid.add(mobileField, 1, 3);
        grid.add(new Label("Gender:"), 0, 4);
        grid.add(genderBox, 1, 4);
        grid.add(new Label("Address:"), 0, 5);
        grid.add(addressField, 1, 5);
        grid.add(updateButton, 1, 6);
        grid.add(resetButton, 2, 6);

        this.getChildren().add(grid);
    }

    // Only alphabets and single space allowed
    private void addNameLimiter(TextField field, int maxLength) {
        field.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() > maxLength || !newVal.matches("[a-zA-Z ]*") || newVal.matches(".*\\s{2,}.*")) {
                field.setText(oldVal);
            }
        });
    }

    private void addLiveLimiter(TextField field, int maxLength) {
        field.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() > maxLength) {
                field.setText(oldVal);
            }
        });
    }

    private void addLiveLimiter(TextField field, int maxLength, boolean digitsOnly) {
        field.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*") || newVal.length() > maxLength) {
                field.setText(oldVal);
            }
        });
    }

    private void addEmailLimiter(TextField field, int maxLength) {
        field.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() > maxLength || newVal.contains(" ")) {
                field.setText(oldVal);
            }
        });
    }

    private void clearFields(TextField id, TextField name, TextField email, TextField mobile,
                             ComboBox<String> gender, TextField address) {
        id.clear();
        name.clear();
        email.clear();
        mobile.clear();
        gender.setValue(null);
        address.clear();
    }
}

