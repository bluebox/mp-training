package com.lms.controller;

import com.lms.Utils.ControllerUtil;
import com.lms.main.Main;
import com.lms.model.Member;
import com.lms.service.MemberService;
import com.lms.service.impl.MemberServiceImpl;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;


import java.io.IOException;
import java.util.List;

public class ViewAllMembersController {
	
	MemberService service;
    
    @FXML
    private TableView<Member> membersTable;
    
    @FXML
    private TableColumn<Member, String> id;
    
    @FXML
    private TableColumn<Member, String> name;
    
    @FXML
    private TableColumn<Member, String> email;
    
    @FXML
    private TableColumn<Member, String> mobile;
    
    @FXML
    private TableColumn<Member, String> gender;
    
    @FXML
    private TableColumn<Member, String> address;
    
    @FXML
    private TableColumn<Member, Void> actionColumn;
    
    @FXML
    private Button backButton;
    
    private ObservableList<Member> membersList = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
    	
    	service = new MemberServiceImpl();
    	List<Member> members = service.getAllMembers();
    	
    	updateMembersList(members);
        id.setCellValueFactory(new PropertyValueFactory<>("member_Id"));
        name.setCellValueFactory(new PropertyValueFactory<>("member_Name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile_No"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        actionColumn.setCellFactory(param -> new UpdateButtonCell());

        membersTable.setItems(membersList);

    }
    
    
    private class UpdateButtonCell extends TableCell<Member, Void> {
        private final Button updateButton = new Button("Update");

        public UpdateButtonCell() {
            updateButton.setOnAction(event -> {
                Member member = getTableView().getItems().get(getIndex());
                showUpdateDialog(member);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : updateButton);
        }
    }

    private void showUpdateDialog(Member member) {
        Dialog<Member> dialog = new Dialog<>();
        dialog.setTitle("Update Member");
        dialog.setHeaderText("Update member information");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lms/view/UpdateMemberDialog.fxml"));
            GridPane dialogPane = loader.load();

            TextField nameField = (TextField) dialogPane.lookup("#nameField");
            TextField emailField = (TextField) dialogPane.lookup("#emailField");
            TextField mobileField = (TextField) dialogPane.lookup("#mobileField");
            ComboBox<String> genderComboBox = (ComboBox<String>) dialogPane.lookup("#genderComboBox");
            TextArea addressField = (TextArea) dialogPane.lookup("#addressField");
            
            genderComboBox.getItems().addAll(
                    "M",
                    "F"
                );

            nameField.setText(member.getMember_Name());
            emailField.setText(member.getEmail());
            mobileField.setText(member.getMobile_No());
            genderComboBox.setValue( String.valueOf(member.getGender()));
            addressField.setText(member.getAddress());

            dialog.getDialogPane().setContent(dialogPane);

            ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == updateButtonType) {
                	String name = nameField.getText().trim();
                	String email = emailField.getText().trim();
                	String mobile = mobileField.getText().trim();
                	char gender = genderComboBox.getValue().trim().charAt(0);
                	String address = addressField.getText().trim();
                	
                    Member tempMember = new Member(name,email,mobile,gender,address);
                    return tempMember;
                }	
                return null;
            });

            dialog.showAndWait().ifPresent(updatedMember -> {
                try{
                	service.updateMember(updatedMember);
                	
                	member.setMember_Name(updatedMember.getMember_Name());
                    member.setEmail(updatedMember.getEmail());
                    member.setMobile_No(updatedMember.getMobile_No());
                    member.setGender(updatedMember.getGender());
                    member.setAddress(updatedMember.getAddress());
                	
                	membersTable.refresh();
                }catch(IllegalArgumentException e) {
                	ControllerUtil.createAlert(AlertType.INFORMATION, "Fail", "Update failed", e.getMessage());
                }
            });

        } catch (IOException e) {
            System.out.println("Error loading update dialog FXML: " + e.getMessage());
        }
    }

    public void updateMembersList(List<Member> members) {
       membersList.clear();
       membersList.addAll(members);
    }
    
    @FXML
    private void handleBackButton() {
        try {
			Main.changePage("LibraryHome");
		} catch (IOException e) {
			System.out.println("Had a problem with going back to main: " + e.getMessage());
		}
    }
}
