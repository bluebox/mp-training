package library.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import library.exception.LibraryValidationException;
import library.model.Member;
import library.model.enums.Gender;
import library.service.MemberServiceImpl;

public class UpdateMemberForm {

    @FXML
    private TextField memberID;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    
    @FXML
    private RadioButton maleRadioButton;    
    @FXML
    private RadioButton femaleRadioButton;  
    @FXML
    private RadioButton otherRadioButton;   
    @FXML
    private ToggleGroup genderToggleGroup; 

    @FXML
    private TextField address;
    @FXML
    private Label updateStatusLabel;

    private Member selectedMember;
    private MemberServiceImpl memberService;
    private ViewMembersScreen parentController;

    @FXML
    public void initialize() {
        memberService = new MemberServiceImpl();
        if (genderToggleGroup == null) {
            genderToggleGroup = new ToggleGroup();
        }
        maleRadioButton.setToggleGroup(genderToggleGroup);
        femaleRadioButton.setToggleGroup(genderToggleGroup);
        otherRadioButton.setToggleGroup(genderToggleGroup);
    }

    public void setMember(Member member) {
        this.selectedMember = member;
        if (selectedMember != null) {
            memberID.setText(String.valueOf(selectedMember.getMemberID()));
            name.setText(selectedMember.getName());
            email.setText(selectedMember.getEmail());
            phoneNumber.setText(String.valueOf(selectedMember.getPhoneNumber()));
            address.setText(selectedMember.getAddress());
            memberID.setEditable(false);

            if (selectedMember.getGender() == Gender.MALE) {
                maleRadioButton.setSelected(true);
            } else if (selectedMember.getGender() == Gender.FEMALE) {
                femaleRadioButton.setSelected(true);
            } else if (selectedMember.getGender() == Gender.OTHER) {
                otherRadioButton.setSelected(true);
            } else {
                genderToggleGroup.selectToggle(null);
            }
        }
    }

    public void setParentController(ViewMembersScreen parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        updateStatusLabel.setText("");
        updateStatusLabel.setTextFill(Color.RED);

        if (selectedMember == null) {
            updateStatusLabel.setText("No member selected for update.");
            return;
        }

        String newName = name.getText();
        String newEmail = email.getText();
        String newPhoneNumberText = phoneNumber.getText();
        String newAddress = address.getText();
        
        Gender newGender = null;
        if (genderToggleGroup.getSelectedToggle() == maleRadioButton) {
            newGender = Gender.MALE;
        } else if (genderToggleGroup.getSelectedToggle() == femaleRadioButton) {
            newGender = Gender.FEMALE;
        } else if (genderToggleGroup.getSelectedToggle() == otherRadioButton) {
            newGender = Gender.OTHER;
        }


        if (newName.isEmpty() || newEmail.isEmpty() || newPhoneNumberText.isEmpty() || newGender == null || newAddress.isEmpty()) {
            updateStatusLabel.setText("Please fill in all fields.");
            return;
        }

        long newPhoneNumber;
        try {
            newPhoneNumber = Long.parseLong(newPhoneNumberText);
        } catch (NumberFormatException e) {
            updateStatusLabel.setText("Phone number cannot be zero or negative.");
            return;
        }

        try {
            Member updatedMember = new Member(
                selectedMember.getMemberID(),
                newName,
                newEmail,
                newPhoneNumber,
                newGender, 
                newAddress
            );
            memberService.updateMember(updatedMember);
            updateStatusLabel.setText("Member updated successfully.");
            updateStatusLabel.setTextFill(Color.GREEN);
            if (parentController != null) {
                parentController.refreshMembersTable();
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (LibraryValidationException e) {
            updateStatusLabel.setText(e.getMessage());
            updateStatusLabel.setTextFill(Color.RED);
        } catch (Exception e) {
            updateStatusLabel.setText("Error updating member: " + e.getMessage());
            updateStatusLabel.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}