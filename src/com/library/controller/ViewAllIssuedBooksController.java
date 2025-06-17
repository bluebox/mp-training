package com.library.controller;

import com.library.service.IssueService;
import com.library.util.AlertMsg;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.time.LocalDate;
import javafx.scene.control.*;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class ViewAllIssuedBooksController {

    @FXML private TableView<IssueRecord> issueTable;
    @FXML private TableColumn<IssueRecord, Integer> colIssueId;
    @FXML private TableColumn<IssueRecord, Integer> colBookId;
    @FXML private TableColumn<IssueRecord, Integer> colMemberId;
    @FXML private TableColumn<IssueRecord, Character> colStatus;
    @FXML private TableColumn<IssueRecord, LocalDate> colIssueDate;
    @FXML private TableColumn<IssueRecord, LocalDate> colReturnDate;
    
    private final IssueService issueService = new IssueService();

    @FXML
    public void initialize() {
        try {
        	
            List<IssueRecord> records = issueService.getAllIssuedRecords();
            ObservableList<IssueRecord> issueList = FXCollections.observableArrayList(records);

            colIssueId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIssueId()).asObject());
            colBookId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());
            colMemberId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getMemberId()).asObject());
            colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus()));
            colIssueDate.setCellValueFactory(data -> new SimpleObjectProperty<LocalDate>(data.getValue().getIssueDate()));
            colReturnDate.setCellValueFactory(data -> new SimpleObjectProperty<LocalDate>(data.getValue().getReturnDate()));

            issueTable.setItems(issueList);

        } catch (Exception e) {
            AlertMsg.showError(e.getMessage());
        }
    }
}
