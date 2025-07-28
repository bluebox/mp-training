package controller;

import domain.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import service.MemberManagementService;

import java.io.IOException;
import java.util.List;

public class ViewMembersController {
    @FXML private TableView<Member> memberTable;
    @FXML  private TableColumn<Member, Integer> idColumn;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> emailColumn;
    @FXML private TableColumn<Member, String> mobileColumn;
    @FXML private TableColumn<Member, String> genderColumn;
    @FXML private TableColumn<Member, String> addressColumn;
    private final MemberManagementService service = new MemberManagementService();
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));      
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        try {
            List<Member> members = service.getAllMembers();
            ObservableList<Member> memberList = FXCollections.observableArrayList(members);
            memberTable.setItems(memberList);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Load Error", "Unable to load members: " + e.getMessage());
            e.printStackTrace();
        }
    }
@FXML
    private void handleBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/MemberManagement.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not navigate back.");
        }
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}