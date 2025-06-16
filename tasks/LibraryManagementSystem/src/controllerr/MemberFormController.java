package controllerr;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Member;
import service.MemberService;

public class MemberFormController {

    @FXML private TextField idField, nameField, emailField, mobileField, addressField;
    @FXML private ChoiceBox<String> genderBox;
    @FXML private Label messageLabel;

    private final MemberService memberService = new MemberService();

    @FXML
    public void initialize() {
        genderBox.getItems().addAll("M", "F");
    }

    @FXML
    private void handleUpdateMember() {
        try {
            String idText = idField.getText().trim();
            if (idText.isEmpty() || !idText.matches("\\d+")) {
                throw new IllegalArgumentException("Member ID must be a valid number.");
            }
            int memberId = Integer.parseInt(idText);

            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String mobileText = mobileField.getText().trim();
            if (!mobileText.matches("\\d{10}")) {
                throw new IllegalArgumentException("Mobile number must be exactly 10 digits.");
            }
            long mobile = Long.parseLong(mobileText); // changed to long

            String genderStr = genderBox.getValue();
            if (genderStr == null) {
                throw new IllegalArgumentException("Gender is required.");
            }

            String address = addressField.getText().trim();

            Member member = new Member(memberId, name, email, mobile, genderStr.charAt(0), address);
            memberService.updateMember(member);

            messageLabel.setText("Member updated successfully.");
        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
        }
    }


}
