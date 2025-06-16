package Controller;

import casestudy.Member;
import casestudy.LibraryException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

import Service.MemberService;

public class ViewMembersController {
    @FXML private TableView<Member> membersTable;
    @FXML private TableColumn<Member, Integer> memberIdColumn;
    @FXML private TableColumn<Member, String> nameColumn, emailColumn, addressColumn;
    @FXML private TableColumn<Member, Long> mobileColumn;
    @FXML private TableColumn<Member, Character> genderColumn;
    @FXML private TextField memberIdField, nameField, emailField, mobileField, addressField;
    @FXML private ComboBox<String> genderCombo;
    private MemberService memberService = new MemberService();
    private Stage primaryStage;

    public ViewMembersController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        memberIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getMemberId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        emailColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));
        mobileColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleLongProperty(cellData.getValue().getMobile()).asObject());
        genderColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getGender()));
        addressColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAddress()));
        loadMembers();
    }

    @FXML
    private void handleUpdateMember() {
        try {
            Member member = new Member(
                nameField.getText(),
                emailField.getText(),
                Long.parseLong(mobileField.getText()),
                genderCombo.getValue().charAt(genderCombo.getValue().length() - 2),
                addressField.getText()
            );
            member.setMemberId(Integer.parseInt(memberIdField.getText()));
            memberService.updateMember(member);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Member updated successfully");
            loadMembers();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid ID or mobile format");
        } catch (LibraryException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void goBack() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        MainController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleClear() {
        memberIdField.clear();
        nameField.clear();
        emailField.clear();
        mobileField.clear();
        addressField.clear();
        genderCombo.getSelectionModel().clearSelection();
    }

    private void loadMembers() {
        try {
            List<Member> members = memberService.getAllMembers();
            membersTable.setItems(FXCollections.observableArrayList(members));
        } catch (LibraryException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void clearFields() {
        memberIdField.clear();
        nameField.clear();
        emailField.clear();
        mobileField.clear();
        addressField.clear();
        genderCombo.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}