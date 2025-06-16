package com.casestudyfx;
import com.casestudy.Gender;
import com.casestudy.Member;
import com.casestudy.Service;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UpdateMemberForm extends VBox {

    public UpdateMemberForm() {
        this.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        TextField memberIdField = new TextField();
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField mobileField = new TextField();
        
        

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("M - Male", "F - Female");

        TextField addressField = new TextField();
        
        addLiveLimiter(memberIdField, 10 , true);
        addLiveLimiter(nameField, 50);
        addLiveLimiter(emailField, 50);
        addLiveLimiter(mobileField, 10, true);
        addLiveLimiter(addressField, 200);

        Button submit = new Button("Update Member");
        submit.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");

        submit.setOnAction(e -> {
            // Update member details in DB
        	try {
        	    int memberId = Integer.parseInt(memberIdField.getText().trim());
        	    String name = nameField.getText().trim();
        	    String email = emailField.getText().trim();
        	    long mobile = Long.parseLong(mobileField.getText().trim());
        	    String genderStr = genderBox.getValue(); // "M - Male"
        	    String address = addressField.getText().trim();

        	    if (name.isEmpty() || email.isEmpty() || genderStr == null || address.isEmpty()) {
        	        UtilMethods.showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields.");
        	        return;
        	    }

        	    String gender = genderStr.substring(0,1); // 'M' or 'F'

        	    
        	    Member member = new Member(memberId, name, email, mobile, Gender.fromCode(gender), address);
        	    Service service = new Service();

        	    if (service.updateMemberService(member)) {
        	        UtilMethods.showAlert(Alert.AlertType.INFORMATION, "Success", "Member updated successfully!");
        	        memberIdField.clear();
        	        nameField.clear();
        	        emailField.clear();
        	        mobileField.clear();
        	        genderBox.setValue(null);  
        	        addressField.clear(); 
        	    } else {
        	        UtilMethods.showAlert(Alert.AlertType.ERROR, "Failure", "Failed to update member.");
        	    }

        	} catch (NumberFormatException ex) {
        	    UtilMethods.showAlert(Alert.AlertType.ERROR, "Input Error", "Invalid Member ID or Mobile number.");
        	}

        	
        	
            System.out.println("Updated Member ID: " + memberIdField.getText());
        });

        grid.add(new Label("Member ID:"), 0, 0);
        grid.add(memberIdField, 1, 0);
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
        grid.add(submit, 1, 6);

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
