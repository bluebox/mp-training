package com.library.controller;

import com.Models.Issue;
import com.Service.IssueService;
//import com.library.util.AlertMsg;
 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.time.LocalDate;
import javafx.scene.control.*;
import javafx.beans.property.SimpleObjectProperty;
 
import java.util.List;
 
public class ViewAllIssuedBooksController {
 
    @FXML private TableView<Issue> issueTable;
    @FXML private TableColumn<Issue, Integer> colIssueId;
    @FXML private TableColumn<Issue, Integer> colBookId;
    @FXML private TableColumn<Issue, Integer> colMemberId;
    @FXML private TableColumn<Issue, Character> colStatus;
    @FXML private TableColumn<Issue, LocalDate> colIssueDate;
    @FXML private TableColumn<Issue, LocalDate> colReturnDate;
    
    private final IssueService issueService = new IssueService();
 
    @FXML
    public void initialize() {
        try {
        	
            List<Issue> records = issueService.getAllIssuedRecords();
            ObservableList<Issue> issueList = FXCollections.observableArrayList(records);
 
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
