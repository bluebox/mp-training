package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import domain.Member;
import domain.checking_enum.Gender;
import Service.MemberService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateMembersController implements Initializable {

    @FXML
    public TextField memberid, nameField, emailField, mobileField, addressField;

    @FXML
    public ComboBox<String> genderBox;

    @FXML
    public MemberService memberService;

    @FXML
    public void handleAddMember() {
        System.out.println("gender " + genderBox.getValue().charAt(0));
        try {
            Member member = new Member(
                    nameField.getText(),
                    emailField.getText(),
                    Integer.parseInt(mobileField.getText()),
                    addressField.getText(),
                    Gender.getstatus("" + genderBox.getValue().charAt(0))
            );
            MemberService memberService = new MemberService();
            memberService.updateMember(member, Integer.parseInt(memberid.getText()));
            showAlert("Member updated successfully.");
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> items = FXCollections.observableArrayList("Male", "Female");
        genderBox.setItems(items);
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }

    @FXML
    public void goToPreviousScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/applicationView/ViewAllMembers.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
