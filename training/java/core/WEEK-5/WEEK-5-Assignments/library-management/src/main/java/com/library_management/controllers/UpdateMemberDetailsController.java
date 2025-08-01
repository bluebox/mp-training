package com.library_management.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.library_management.App;
import com.library_management.dao.implementation.MemberDaoImplementation;
import com.library_management.domain.Member;
import com.library_management.exceptions.DatabaseException;
import com.library_management.exceptions.InvalidDetailsException;
import com.library_management.services.implementation.MemberServiceImplementation;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class UpdateMemberDetailsController {

    private List<Member> memberList;

    public void setMembersList(List<Member> membersList) {
        this.memberList = membersList;
    }

    public List<Member> getMembersList() {
        return this.memberList;
    }

    @FXML
    private void backButton() throws IOException {
        App.setRoot("memberOptions");
    }
	
	@FXML
	private void homeButton() throws IOException{
		App.setRoot("home");
	}
    @FXML
    private ComboBox<String> memberSelector;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField mobileNoField;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML 
    private TextField addressField;

    @FXML
    private Text message;

    @FXML
    public void initialize() throws SQLException, DatabaseException {
        List<Member> membersList = new MemberDaoImplementation().getAllMembers();
        this.setMembersList(membersList);

        for (Member member : membersList) {
            memberSelector.getItems().add(member.getMemberId() + " = " + member.getMemberName());
        }

        genderComboBox.getItems().addAll("Male", "Female");
    }

    @FXML
    public void loadMemberDetails() {
        try {
            int id = Integer.parseInt(memberSelector.getValue().split("=")[0].trim());
            Member selectedMember = this.getMembersList().stream()
                .filter(m -> m.getMemberId() == id)
                .findFirst()
                .orElse(null);

            if (selectedMember != null) {
                nameField.setText(selectedMember.getMemberName());
                emailField.setText(selectedMember.getMemberMail());
                mobileNoField.setText(selectedMember.getMobileNo());
                genderComboBox.setValue(selectedMember.getGender());
                addressField.setText(selectedMember.getMemberAddress());
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	
            message.setText("Error loading member details.");
            message.setFill(Color.RED);
        }
    }

    @FXML
    public void handleButtonClick() {
        try {
            int id = Integer.parseInt(memberSelector.getValue().split("=")[0].trim());

            Member updatedMember = new Member();
            updatedMember.setMemberId(id);
            updatedMember.setMemberName(nameField.getText());
            updatedMember.setMemberMail(emailField.getText());
            updatedMember.setMobileNo(mobileNoField.getText());
            updatedMember.setGender(genderComboBox.getValue());
            updatedMember.setMemberAddress(addressField.getText());

            // Use service class to validate and update
            new MemberServiceImplementation().validateUpdateMemberDetails(updatedMember);

            message.setText("Member updated successfully.");
            message.setFill(Color.GREEN);

        } catch (InvalidDetailsException | DatabaseException | SQLException e) {
            message.setText(e.getMessage());
            message.setFill(Color.RED);
        } catch (Exception e) {
            message.setText("Unexpected error occurred.");
            message.setFill(Color.RED);
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> message.setText(""));
        pause.play();
    }
}