package com.lms.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

import com.lms.Utils.ControllerUtil;
import com.lms.Utils.ValidatorsUtil;
import com.lms.main.Main;
import com.lms.model.Member;
import com.lms.service.MemberService;
import com.lms.service.impl.MemberServiceImpl;

import javafx.event.ActionEvent;

public class AddNewMemberController {
	MemberService service;
    @FXML
    public TextField nameField;
    
    @FXML
    public TextField emailField;
    
    @FXML
    public TextField mobileField;
    
    @FXML
    public ComboBox<String> genderChoiceBox;
    
    @FXML
    public TextArea addressArea;
    
    @FXML
    public Button submitButton;
    
    @FXML
    public Button cancelButton;
    
   @FXML
    public void initialize() {
	   genderChoiceBox.getItems().addAll(
    	        "M",
    	        "F"         
    	);
	   service=new MemberServiceImpl();
    }
    
    @FXML
    public void handleSubmit() {
    		String name = nameField.getText();
            String email = emailField.getText().trim();
            String mobile = mobileField.getText().trim();
            String gender = genderChoiceBox.getValue();
            String address = addressArea.getText().trim();
            saveMember(name, email, mobile, gender, address);
    }
    
    
    @FXML
    public void handleCancel(ActionEvent event) {
        clearForm();
    }
   
    public void saveMember(String name, String email, String mobile, String gender, String address) {
    	try{
    		Member member=new Member(name, email, mobile,gender.charAt(0), address);
    		ValidatorsUtil.validateMember(member);
    		service.addNewMember(member);
    		showAlert("Success", "Member added successfully!");
    		clearForm();
    	}
    	catch (NullPointerException | IllegalArgumentException e) {
    		ControllerUtil.createAlert(AlertType.INFORMATION, "Error", "Add Member Failed", e.getMessage());
    	}
        
        
        try {
			Main.changePage("LibraryHome");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void clearForm() {
        try {
			Main.changePage("LibraryHome");
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}