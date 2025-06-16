package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDAO;
import enums.Gender;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Member;

public class UpdateMemberController implements Initializable {
    //write code for handle Update Member
    @FXML
    private TextField membersIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField mobileField;
    @FXML
    private ComboBox<Gender> genderBox;

    @FXML
    private TextField addressField;

    @FXML
    private void handleUpdateMember(){
        try {
            int memberId = Integer.parseInt(membersIdField.getText());

            String name = nameField.getText();
            String email = emailField.getText();
            String mobile = mobileField.getText();
            Gender gender = genderBox.getValue();
            String address = addressField.getText();

            if (name.isEmpty() || email.isEmpty() || mobile.isEmpty() || gender == null || address.isEmpty()) {
                showAlert("All fields are required");
                return;
            }
            long mobileNum=Long.parseLong(mobile);
            Member member=new Member(memberId, name, email, mobileNum, gender, address);
            // memberService.updateMember(member);
            MemberDAO dao=new MemberDAO();
            boolean updated = dao.updateMemberDetails(member);

            if (updated) {
                showAlert("member details updated successfully");
            } else {
                showAlert("Update failed. Please check Book ID");
            }

        }catch(Exception e)
        {
            System.out.println("exception occured");
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderBox.getItems().addAll(Gender.values());
    }
}
