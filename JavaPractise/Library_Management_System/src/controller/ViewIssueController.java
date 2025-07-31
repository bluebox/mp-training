package controller;

import domain.IssueRecord;
import domain.IssueStatus;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.IssueService;

import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewIssueController {

    @FXML
    private TableView<IssueRecord> tableView;

    @FXML
    private TableColumn<IssueRecord, Integer> colIssueId;

    @FXML
    private TableColumn<IssueRecord, Integer> colBookId;

    @FXML
    private TableColumn<IssueRecord, Integer> colMemberId;

    @FXML
    private TableColumn<IssueRecord, String> colStatus;

    @FXML
    private TableColumn<IssueRecord, String> colIssueDate;

    @FXML
    private TableColumn<IssueRecord, String> colReturnDate;

    @FXML
    private TableColumn<IssueRecord, String> colIssuedBy;

    private IssueService issueService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void setConnection(Connection connection) {
        this.issueService = new IssueService(connection);
        loadIssueRecords();
    }

    private void loadIssueRecords() {
        List<IssueRecord> records = issueService.getAllIssueRecords();
        ObservableList<IssueRecord> observableList = FXCollections.observableArrayList(records);

        colIssueId.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getIssueId()).asObject());

        colBookId.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getBookId()).asObject());

        colMemberId.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getMemberId()).asObject());

        colStatus.setCellValueFactory(cellData -> {
            IssueStatus status = cellData.getValue().getStatus();
            String statusStr = (status == IssueStatus.Issued) ? "Issued" : "Returned";
            return new SimpleStringProperty(statusStr);
        });

        colIssueDate.setCellValueFactory(cellData -> {
            if (cellData.getValue().getIssueDate() != null) {
                return new SimpleStringProperty(cellData.getValue().getIssueDate().format(formatter));
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        colReturnDate.setCellValueFactory(cellData -> {
            if (cellData.getValue().getReturnDate() != null) {
                return new SimpleStringProperty(cellData.getValue().getReturnDate().format(formatter));
            } else {
                return new SimpleStringProperty("Not Returned");
            }
        });

        colIssuedBy.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getIssuedBy()));

        tableView.setItems(observableList);
    }
}