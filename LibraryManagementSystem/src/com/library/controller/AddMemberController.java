package com.library.controller;

//package com.library.controller;
import com.controller.*;
import com.Models.Member;
//import com.library.controller.Member;
import com.Service.MemberService;
//import com.library.util.AlertMsg;
 
import javafx.fxml.FXML;
import javafx.scene.control.*;
 
public class AddMemberController {
 
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField mobileField;
    @FXML private TextField addressField;
    @FXML private ChoiceBox<String> genderBox;
 
    private final MemberService memberService = new MemberService();
 
    @FXML
    public void initialize() {
        genderBox.getItems().addAll("Male", "Female");
    }
 
    @FXML
    private void addMember() {
        try {
            String selectedGender = genderBox.getValue();
            char gender = selectedGender.equalsIgnoreCase("Male") ? 'M' : 'F';
 
            Member member = new Member(
                0,
                nameField.getText(),
                emailField.getText(),
                mobileField.getText(),
                gender,
                addressField.getText()
            );
 
            memberService.addMember(member);
 
            nameField.clear();
            emailField.clear();
            mobileField.clear();
            addressField.clear();
            genderBox.setValue(null);
        } catch (Exception e) {
            AlertMsg.showError(e.getMessage());
        }
    }
}
