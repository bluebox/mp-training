package com.LibraryManagement.controller;

import java.sql.SQLException;
import com.LibraryManagement.models.Member;
import com.LibraryManagement.service.implementation.MemberServiceImplementation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MemberController {

    @FXML private Button addMemberButton, viewMembersButton, updateButton;
    @FXML private TextField memberIdField, nameField, emailField, mobileField;
    @FXML private ComboBox<String> genderCombo;
    @FXML private TextArea addressArea;
    @FXML private Label nameErrorLabel, emailErrorLabel, mobileErrorLabel, genderErrorLabel, addressErrorLabel;
    @FXML private TableView<Member> membersTable;
    @FXML private TableColumn<Member, Integer> memberIdColumn;
    @FXML private TableColumn<Member, String> nameColumn, emailColumn, genderColumn, addressColumn;
    @FXML private TableColumn<Member, Long> mobileColumn;
    @FXML private TableColumn<Member, Void> actionColumn;

    private final MemberServiceImplementation memberService = new MemberServiceImplementation();
    private Member selectedMember;

    @FXML
    private void initialize() throws SQLException {
        if (membersTable != null) {
            setupTableColumns();
            addUpdateButtonToTable();
            refreshTable();
        }
    }

    private void setupTableColumns() {
        memberIdColumn.setCellValueFactory(cell -> cell.getValue().memberIdProperty().asObject());
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        emailColumn.setCellValueFactory(cell -> cell.getValue().emailProperty());
        mobileColumn.setCellValueFactory(cell -> cell.getValue().mobileProperty().asObject());
        genderColumn.setCellValueFactory(cell -> cell.getValue().genderProperty());
        addressColumn.setCellValueFactory(cell -> cell.getValue().addressProperty());
    }

    private void refreshTable() throws SQLException {
        ObservableList<Member> list = FXCollections.observableArrayList(memberService.getAllMembers());
        membersTable.setItems(list);
    }

    private void addUpdateButtonToTable() {
        Callback<TableColumn<Member, Void>, TableCell<Member, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btn = new Button("Update");
            {
                btn.setOnAction(e -> {
                    selectedMember = getTableView().getItems().get(getIndex());
                    openMemberForm(selectedMember);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        };
        actionColumn.setCellFactory(cellFactory);
    }

    private void openMemberForm(Member member) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/LibraryManagement/application/members/UpdateMemberForm.fxml"));
            Parent root = loader.load();

            UpdateMemberController controller = loader.getController();
            controller.setMember(member);

            Stage popupStage = new Stage();
            popupStage.setTitle("Update Member");
            popupStage.setScene(new Scene(root));
            popupStage.initOwner(membersTable.getScene().getWindow());
            popupStage.setResizable(false);
            popupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    

    @FXML
    private void handleViewMembers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/LibraryManagement/application/members/ViewMembersForm.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) viewMembersButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("View Members");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegister() {
        if (!validateForm()) return;

        Member m = new Member(
            nameField.getText().trim(),
            emailField.getText().trim(),
            Long.parseLong(mobileField.getText().trim()),
            String.valueOf(genderCombo.getValue().equals("Male") ? "M" : "F"),
            addressArea.getText().trim()
        );

        try {
            memberService.registerMember(m);
            showAlert("Success", "Member registered successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public void prefillMember(Member m) {
        if (m == null) return;

        memberIdField.setText(String.valueOf(m.getMemberId()));
        nameField.setText(m.getName());
        emailField.setText(m.getEmail());
        mobileField.setText(String.valueOf(m.getMobile()));
        genderCombo.setValue(m.getGender().equals("M") ? "Male" : "Female");
        addressArea.setText(m.getAddress());
        updateButton.setVisible(true);
        selectedMember = m;
    }

    private boolean validateForm() {
        boolean valid = true;

        nameErrorLabel.setText("");
        emailErrorLabel.setText("");
        mobileErrorLabel.setText("");
        genderErrorLabel.setText("");
        addressErrorLabel.setText("");

        if (nameField.getText().trim().isEmpty()) {
            nameErrorLabel.setText("Name is required *");
            valid = false;
        }
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            emailErrorLabel.setText("Email is required *");
            valid = false;
        } else if (!email.endsWith("@gmail.com")) {
            emailErrorLabel.setText("Enter valid email address *");
            valid = false;
        }
        String mobile = mobileField.getText().trim();
        if (mobile.isEmpty()) {
            mobileErrorLabel.setText("Mobile is required *");
            valid = false;
        } else if (!mobile.matches("\\d{10}")) {
            mobileErrorLabel.setText("Mobile must be exactly 10 digits *");
            valid = false;
        }
        if (genderCombo.getValue() == null) {
            genderErrorLabel.setText("Gender is required *");
            valid = false;
        }
        if (addressArea.getText().trim().isEmpty()) {
            addressErrorLabel.setText("Address is required *");
            valid = false;
        }

        return valid;
    }

  
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
