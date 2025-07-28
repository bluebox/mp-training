package controller;

import domain.IssueRecord;
import domain.IssueStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.IssueService;

import java.sql.Connection;
import java.time.LocalDate;
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
    private TableColumn<IssueRecord, IssueStatus> colStatus;

    @FXML
    private TableColumn<IssueRecord, LocalDate> colIssueDate;

    @FXML
    private TableColumn<IssueRecord, LocalDate> colReturnDate;

    private IssueService issueService;

    public void setConnection(Connection connection) {
        this.issueService = new IssueService(connection);
        loadIssueRecords(); // Load data after connection is set
    }

    private void loadIssueRecords() {
        List<IssueRecord> records = issueService.getAllIssueRecords();
        ObservableList<IssueRecord> observableList = FXCollections.observableArrayList(records);

        colIssueId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getIssueId()).asObject());
        colBookId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getBookId()).asObject());
        colMemberId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getMemberId()).asObject());
        colStatus.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getStatus()));
        colIssueDate.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getIssueDate()));
        colReturnDate.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getReturnDate()));

        tableView.setItems(observableList);
    }
}