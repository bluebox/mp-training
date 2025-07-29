package com.library_management.controllers;
import com.library_management.utilities.ResponseHandler;

import java.io.IOException;

import com.library_management.App;
import com.library_management.domain.Member;
import com.library_management.services.implementation.MemberServiceImplementation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddMemberController {

    @FXML private TextField name;
    @FXML private TextField email;
    @FXML private TextField mobile;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TextField address;
    @FXML private Button register;
    @FXML private Text message;

    @FXML
    private void backButton() throws IOException {
        App.setRoot("memberOptions");
    }
	
	@FXML
	private void homeButton() throws IOException{
		App.setRoot("home");
	}
    private MemberServiceImplementation memberService = new MemberServiceImplementation();
    @FXML
    public void initialize() {
        genderComboBox.getItems().addAll("Male","Female");

        register.setOnAction(e -> {
            String memberName=name.getText();
            String memberMail=email.getText();
            String mobileNo=mobile.getText();
            String gender=genderComboBox.getValue();
            String memberAddress=address.getText();

            Member member=new Member(memberName,memberMail,mobileNo,gender,memberAddress);

            try{
                memberService.addMember(member);
                ResponseHandler.showResponse(message, "Member Added Successfully...", Color.GREEN);
                clearForm();
            }
            catch(Exception ex) {
            	 ResponseHandler.showResponse(message, "Error while adding member data..", Color.RED);
            }
        });
    }

    private void clearForm() {
        name.clear();
        email.clear();
        mobile.clear();
        genderComboBox.getSelectionModel().clearSelection();
        address.clear();
    }
}

   
