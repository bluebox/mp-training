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

import java.io.IOException;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MemberController {
    @FXML private TextField nameField, emailField, mobileField, addressField;
    @FXML private ComboBox<String> genderCombo;
    @FXML private TableView<MemberPojo> memberTable;
    @FXML private TableColumn<MemberPojo,Integer> colId;
    @FXML private TableColumn<MemberPojo,String> colName, colEmail, colAddress;
    @FXML private TableColumn<MemberPojo,Character> colGender;
    @FXML private TableColumn<MemberPojo, String> colMobile;
    private final libraryServices service = new libraryServices();

    @FXML public void initialize() {
        genderCombo.getItems().addAll("M","F");
        colId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getMemberId()).asObject());
        colName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        colEmail.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEmail()));
        colMobile.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMobile()));
        colGender.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getGender()));
        colAddress.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAddress()));
        refreshTable();
    }

    public void handleAddMember() {
        MemberPojo m = new MemberPojo();
        m.setName(nameField.getText());
        m.setEmail(emailField.getText());
        m.setMobile(mobileField.getText());
        m.setGender(genderCombo.getValue().charAt(0));
        m.setAddress(addressField.getText());
        if (service.registerMember(m)) {
            refreshTable();
            clearFields();
        } else showAlert("Error","Could not register member.");
    }

    private void refreshTable() {
        ObservableList<MemberPojo> list = FXCollections.observableArrayList(service.viewAllMembers());
        memberTable.setItems(list);
    }

    private void clearFields() {
        nameField.clear(); emailField.clear(); mobileField.clear();
        addressField.clear(); genderCombo.setValue(null);
    }

    private void showAlert(String t, String c) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(t); a.setContentText(c); a.showAndWait();
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
