package controllerr;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Member;
import service.MemberService;

public class AddMemberController {

    @FXML private TextField nameId, emailId, mobileField, addressField;
    @FXML private ChoiceBox<String> genderBox;
    @FXML private Label messageLabel;

    private final MemberService memberService = new MemberService();

    @FXML
    public void initialize() {
        genderBox.getItems().addAll("M", "F");
    }

    @FXML
    private void handleAddMember() {
    	try {
            Member member = new Member(
                0, nameId.getText(),
                emailId.getText(),
                Long.parseLong(mobileField.getText()),
                genderBox.getValue().charAt(0),
                addressField.getText()
            );

            memberService.addMember(member);
            messageLabel.setText("Member added successfully!");
            clearForm();
        } catch (Exception e) {
            messageLabel.setText(e.getMessage());
        }
    }
    
    private void clearForm() {
        nameId.clear();
        emailId.clear();
        addressField.clear();
        mobileField.clear();
        genderBox.setValue(null);
    }


}
