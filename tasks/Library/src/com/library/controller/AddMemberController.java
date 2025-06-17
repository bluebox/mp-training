package com.library.controller;

import com.library.domain.Member;
import com.library.serviceInterface.MemberServiceInterface;
import com.library.services.MemberService;
import com.library.util.ValidationException;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddMemberController {
    @FXML private TextField nameField, emailField, mobileField, addressField;
    @FXML private ChoiceBox<String> genderChoice;

    @FXML
    private Label statusLabel;
    private final MemberServiceInterface memberService = new MemberService();

    public void initialize() {
        genderChoice.getItems().addAll("M", "F");
    }

    @FXML
    private void handleAddMember() {
    	
    	
    	
    	
        try {
        	
        	if(mobileField.getText().length() < 10)
        	{
        		throw new ValidationException("mobile number is not valid");
        	}
            Member member = new Member(0,
           nameField.getText(),
            emailField.getText(),
           Integer.parseInt(mobileField.getText()),
            genderChoice.getValue().charAt(0),
            addressField.getText());

            memberService.addMember(member);
            statusLabel.setText("Member added successfully");
        }
        catch(ValidationException e)
        {
        	statusLabel.setText(e.getMessage());
        }
        
        catch (Exception e) {
        	System.out.println(e.getMessage());;
        }
       
    }
}