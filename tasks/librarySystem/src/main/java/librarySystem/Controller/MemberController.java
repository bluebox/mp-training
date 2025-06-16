package librarySystem.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import librarySystem.Service.libraryServices;
import model.MemberPojo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MemberController {
    @FXML private TextField nameField, emailField, mobileField, addressField;
    @FXML private ComboBox<String> genderCombo;
    @FXML private TableView<MemberPojo> memberTable;
    @FXML private TableColumn<MemberPojo,Integer> colId;
    @FXML private TableColumn<MemberPojo,String> colName, colEmail, colAddress;
    @FXML private TableColumn<MemberPojo,Character> colGender;
    @FXML private TableColumn<MemberPojo, Integer> colMobile;
    private final libraryServices service = new libraryServices();

    @FXML public void initialize() {
        genderCombo.getItems().addAll("M","F");
        colId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getMemberId()).asObject());
        colName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        colEmail.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEmail()));
        colMobile.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getMobile()).asObject());
        colGender.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getGender()));
        colAddress.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAddress()));
        refreshTable();
    }

    public void handleAddMember() {
        MemberPojo m = new MemberPojo();
        m.setName(nameField.getText());
        m.setEmail(emailField.getText());
        m.setMobile(Integer.parseInt(mobileField.getText()));
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
}
