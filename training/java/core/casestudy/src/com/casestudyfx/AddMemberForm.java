package com.casestudyfx;

import com.casestudy.Gender;
import com.casestudy.Member;
import com.casestudy.Service;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AddMemberForm extends VBox {

    public AddMemberForm() {
        this.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        // Fields
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField mobileField = new TextField();
        TextField addressField = new TextField();
        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("M - Male", "F - Female");

        
        addLiveLimiter(nameField, 50);
        addLiveLimiter(emailField, 50);
        addLiveLimiter(mobileField, 10, true);
        addLiveLimiter(addressField, 200);

        Button submit = new Button("Add Member");
        submit.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");

        submit.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String mobileStr = mobileField.getText().trim();
            String genderStr = genderBox.getValue();
            String address = addressField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || mobileStr.isEmpty()
                    || genderStr == null || address.isEmpty()) {
                UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields.");
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Invalid email format.");
                return;
            }

            if (!mobileStr.matches("\\d{10}")) {
                UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Mobile number must be 10 digits.");
                return;
            }

            long mobile = Long.parseLong(mobileStr);
            String gender = genderStr.substring(0, 1);

            Member member = new Member(name, email, mobile, Gender.fromCode(gender), address);
            Service service = new Service();

            if (service.addMemberService(member)) {
                UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Member created successfully!");
                nameField.clear();
                emailField.clear();
                mobileField.clear();
                genderBox.setValue(null);
                addressField.clear();
            } else {
                UtilMethods.showAlert(Alert.AlertType.ERROR, "Failure", "Member creation failed.");
            }
        });

        // Layout without counter labels
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);

        grid.add(new Label("Mobile:"), 0, 2);
        grid.add(mobileField, 1, 2);

        grid.add(new Label("Gender:"), 0, 3);
        grid.add(genderBox, 1, 3);

        grid.add(new Label("Address:"), 0, 4);
        grid.add(addressField, 1, 4);

        grid.add(submit, 1, 5);

        this.getChildren().add(grid);
    }

    // Without digit-only
    private void addLiveLimiter(TextField field, int maxLength) {
        field.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() > maxLength) {
                field.setText(oldVal);
            }
        });
    }

    // With digit-only enforcement
    private void addLiveLimiter(TextField field, int maxLength, boolean digitsOnly) {
        field.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*") || newVal.length() > maxLength) {
                field.setText(oldVal);
            }
        });
    }
}

