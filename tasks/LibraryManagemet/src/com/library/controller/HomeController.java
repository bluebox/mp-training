package com.library.controller;

import java.io.IOException;
import java.util.List;

import com.library.domain.Member;
import com.library.service.MemberService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController {

    private void loadScene(String fxmlFile, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/UI/" + fxmlFile));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Library System");
        stage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void goToAddBook(ActionEvent event) throws IOException {
        loadScene("AddBook.fxml", event);
    }

    @FXML
    private void goToAddMember(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/UI/member_form.fxml"));
            Parent root = loader.load();

            MemberController controller = loader.getController();
            controller.setAddMode(); // behave as "Add"

            Stage stage = new Stage();
            stage.setTitle("Add New Member");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToUpdateMember(ActionEvent event) {
        try {
            MemberService service = new MemberService();
            List<Member> members = service.viewAllMembers();

            if (members == null || members.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "No Members", "No members to update.");
                return;
            }

            ChoiceDialog<Member> dialog = new ChoiceDialog<>(members.get(0), members);
            dialog.setTitle("Select Member");
            dialog.setHeaderText("Choose a member to update");
            dialog.setContentText("Member:");

            dialog.showAndWait().ifPresent(selected -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/library/UI/member_form.fxml"));
                    Parent root = loader.load();

                    MemberController controller = loader.getController();
                    controller.setUpdateMode(selected);

                    Stage stage = new Stage();
                    stage.setTitle("Update Member");
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToViewBooks(ActionEvent event) throws IOException {
        loadScene("viewBooks.fxml", event);
    }

    @FXML
    private void goToViewMembers(ActionEvent event) throws IOException {
        loadScene("ViewMembers.fxml", event);
    }

    @FXML
    private void goToIssueBook(ActionEvent event) throws IOException {
        loadScene("IssueBook.fxml", event);
    }

    @FXML
    private void goToReturnBook(ActionEvent event) throws IOException {
        loadScene("ReturnBook.fxml", event);
    }

    @FXML
    private void goToIssuedRecords(ActionEvent event) throws IOException {
        loadScene("IssuedBooks.fxml", event);
    }
}
