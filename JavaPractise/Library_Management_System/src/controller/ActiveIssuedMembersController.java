package controller;

import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ReportService;

public class ActiveIssuedMembersController {

    @FXML private TableView<Member> membersTable;
    @FXML private TableColumn<Member, Integer> colMemberId;
    @FXML private TableColumn<Member, String> colName;
    @FXML private TableColumn<Member, String> colEmail;

    private ReportService reportService = new ReportService();

    @FXML
    public void initialize() {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        membersTable.getItems().setAll(reportService.getMembersWithActiveIssuedBooks());
    }
    @FXML
    private void handleBack() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/RecordOperations.fxml"));
        Stage stage = (Stage) membersTable.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    @FXML
    private void handleGoHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/Main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
