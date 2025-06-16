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

        // Apply input limits (without visible counters)
        addLiveLimiter(nameField, 50);
        addLiveLimiter(emailField, 50);
        addLiveLimiter(mobileField, 10, true); // digits only
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

    // Without digit-only flag
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



/*
package com.casestudyfx;
import com.casestudy.Gender;
import com.casestudy.Member;
import com.casestudy.Service;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AddMemberForm extends VBox {

    public AddMemberForm() {
        this.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField mobileField = new TextField();
        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("M - Male", "F - Female");

        TextField addressField = new TextField();

        Button submit = new Button("Add Member");
        submit.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");

        submit.setOnAction(e -> {
            // TODO: Call insert function with form values
        	String name = nameField.getText().trim();
        	String email = emailField.getText().trim();
        	String mobileStr = mobileField.getText().trim();
        	String genderStr = genderBox.getValue();  // e.g., "M - Male"
        	String address = addressField.getText().trim();

        	// Validate and parse
        	if (name.isEmpty() || email.isEmpty() || mobileStr.isEmpty() || 
        	    genderStr == null || address.isEmpty()) {
        	    // handle validation failure (e.g., show alert)
        		 UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields.");
        	    return;
        	}

        	long mobile = Long.parseLong(mobileStr);  // Can throw NumberFormatException
        	String gender = genderStr.substring(0,1);        // Get 'M' or 'F'
        	
        	
        	// Create POJO
        	Member member = new Member(name, email, mobile, Gender.fromCode(gender), address);
        	
        	Service service = new Service();
        	
        	if(service.addMemberService(member)) {
        		UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Member created successfully!");
        		nameField.clear();
        	    emailField.clear();
        	    mobileField.clear();
        	    genderBox.setValue(null);  // or genderBox.getSelectionModel().clearSelection();
        	    addressField.clear();
        	    System.out.println("Member added: " + nameField.getText() + "succesfully");
        	}else {
        		UtilMethods.showAlert(Alert.AlertType.ERROR, "Failure", "Member creation failed.");
        		 System.out.println("Member adding: " + nameField.getText() + "failed");
        	}
        	
           
        });

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
}
*/
