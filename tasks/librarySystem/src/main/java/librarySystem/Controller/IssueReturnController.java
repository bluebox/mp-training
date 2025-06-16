package librarySystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import librarySystem.Service.libraryServices;
import model.IssueRecordPojo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;


public class IssueReturnController {
    @FXML private TextField bookIdField, memberIdField;
    @FXML private TableView<IssueRecordPojo> issueTable;
    @FXML private TableColumn<IssueRecordPojo,Integer> colIssueId, colBookId, colMemberId;
    @FXML private TableColumn<IssueRecordPojo,Character> colStatus;
    @FXML private TableColumn<IssueRecordPojo,Date> colIssueDate, colReturnDate;
    private final libraryServices service = new libraryServices();

    @FXML public void initialize() {
        colIssueId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getIssueId()).asObject());
        colBookId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getBookId()).asObject());
        colMemberId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getMemberId()).asObject());
        colStatus.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getStatus()));
        colIssueDate.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getIssueDate()));
        colReturnDate.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getReturnDate()));
        refreshTable();
    }

    public void handleIssue() {
        int bid = Integer.parseInt(bookIdField.getText());
        int mid = Integer.parseInt(memberIdField.getText());
        if (service.issueBook(bid, mid, Date.valueOf(LocalDate.now()))) {
            refreshTable(); clearFields();
        } else showAlert("Error","Could not issue book.");
    }

    public void handleReturn() {
        int bid = Integer.parseInt(bookIdField.getText());
        int mid = Integer.parseInt(memberIdField.getText());
        if (service.returnBook(bid, mid, Date.valueOf(LocalDate.now()))) {
            refreshTable(); clearFields();
        } else showAlert("Error","Could not return book.");
    }

    private void refreshTable() {
        ObservableList<IssueRecordPojo> list = FXCollections.observableArrayList(service.getAllRecords());
        issueTable.setItems(list);
    }

    private void clearFields() {
        bookIdField.clear(); memberIdField.clear();
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
        Stage stage = (Stage) issueTable.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Library System");
        stage.show();
    }
}
