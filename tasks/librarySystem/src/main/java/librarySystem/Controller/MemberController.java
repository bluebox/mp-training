package librarySystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.event.ActionEvent;
import librarySystem.Service.libraryServices;
import model.MemberPojo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;

public class MemberController {
    @FXML private TextField nameField, emailField, mobileField, addressField;
    @FXML private ComboBox<String> genderCombo;
    @FXML private TableView<MemberPojo> memberTable;
    @FXML private TableColumn<MemberPojo,Integer> colId;
    @FXML private TableColumn<MemberPojo,String> colName, colEmail, colAddress;
    @FXML private TableColumn<MemberPojo,Character> colGender;
    @FXML private TableColumn<MemberPojo, String> colMobile;

    private final libraryServices service = new libraryServices();
    private MemberPojo selectedMember;

    @FXML
    public void initialize() {
        genderCombo.getItems().addAll("M", "F");
        colId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getMemberId()).asObject());
        colName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        colEmail.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEmail()));
        colMobile.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMobile()));
        colGender.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getGender()));
        colAddress.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAddress()));
        
        memberTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        memberTable.setOnMouseClicked(e -> {
            selectedMember = memberTable.getSelectionModel().getSelectedItem();
            if (selectedMember != null) {
                nameField.setText(selectedMember.getName());
                emailField.setText(selectedMember.getEmail());
                mobileField.setText(selectedMember.getMobile());
                addressField.setText(selectedMember.getAddress());
                genderCombo.setValue(String.valueOf(selectedMember.getGender()));
            }
        });
        refreshTable();
    }

    public void handleAddMember() {
        if (!validateInputs()) return;

        MemberPojo m = new MemberPojo();
        m.setName(nameField.getText().trim());
        m.setEmail(emailField.getText().trim());
        m.setMobile(mobileField.getText().trim());
        m.setGender(genderCombo.getValue().charAt(0));
        m.setAddress(addressField.getText().trim());

        if (service.registerMember(m)) {
            refreshTable();
            clearFields();
            showAlert("Success", "Member added successfully.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Could not register member.", Alert.AlertType.ERROR);
        }
    }

    public void handleUpdateMember() {
        if (selectedMember == null) {
            showAlert("Warning", "Please select a member to update.", Alert.AlertType.WARNING);
            return;
        }

        if (!validateInputs()) return;

        MemberPojo updated = new MemberPojo();
        updated.setName(nameField.getText().trim());
        updated.setEmail(emailField.getText().trim());
        updated.setMobile(mobileField.getText().trim());
        updated.setGender(genderCombo.getValue().charAt(0));
        updated.setAddress(addressField.getText().trim());

        if (service.updateMember(selectedMember, updated)) {
            showAlert("Success", "Member updated successfully.", Alert.AlertType.INFORMATION);
            refreshTable();
            clearFields();
            selectedMember = null;
        } else {
            showAlert("Error", "Update failed.", Alert.AlertType.ERROR);
        }
    }

    public void handleDeleteMember() {
        MemberPojo selected = memberTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Warning", "Please select a member to delete.", Alert.AlertType.WARNING);
            return;
        }

        if (service.deleteMember(selected.getMemberId())) {
            refreshTable();
            clearFields();
            showAlert("Success", "Member deleted successfully.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Failed to delete member.", Alert.AlertType.ERROR);
        }
    }

    private boolean validateInputs() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String mobile = mobileField.getText().trim();
        String address = addressField.getText().trim();
        String gender = genderCombo.getValue();

        if (name.isEmpty()) {
            showAlert("Validation", "Please enter name.", Alert.AlertType.WARNING);
            return false;
        }
        if (email.isEmpty() || !email.matches(("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$"))) {
            showAlert("Validation", "Please valid email id.", Alert.AlertType.WARNING);
            return false;
        }
        if (mobile.isEmpty()|| !mobile.matches("\\d{10}")) {
            showAlert("Validation", "Please enter valid 10 digit moblie number.", Alert.AlertType.WARNING);
            return false;
        }
        if (gender == null || (!gender.equals("M") && !gender.equals("F"))) {
            showAlert("Validation", "Please select gender (M or F).", Alert.AlertType.WARNING);
            return false;
        }
        if (address.isEmpty()) {
            showAlert("Validation", "Please enter address.", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private void refreshTable() {
        ObservableList<MemberPojo> list = FXCollections.observableArrayList(service.viewAllMembers());
        memberTable.setItems(list);
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        mobileField.clear();
        addressField.clear();
        genderCombo.setValue(null);
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setContentText(content);
        a.showAndWait();
    }

    public void handleBooks(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/BookView.fxml");
    }

    public void handleMembers(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/MemberView.fxml");
    }

    public void handleIssueReturn(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/IssueReturn.fxml");
    }

    public void handleReports(ActionEvent event) throws IOException {
        switchScene(event, "/librarySystem/View/ReportsView.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) memberTable.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Library System");
        stage.show();
    }
}
