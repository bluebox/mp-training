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
