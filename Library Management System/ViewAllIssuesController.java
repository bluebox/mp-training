package com.LibraryManagement.controller;

import com.LibraryManagement.DAO.Implementation.IssueRecordDAOImplementation;
import com.LibraryManagement.models.IssueRecords;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ViewAllIssuesController {

    @FXML private TableView<IssueRecords> issueTable;
    @FXML private TableColumn<IssueRecords, Integer> issueIdCol;
    @FXML private TableColumn<IssueRecords, Integer> bookIdCol;
    @FXML private TableColumn<IssueRecords, Integer> memberIdCol;
    @FXML private TableColumn<IssueRecords, String> statusCol;
    @FXML private TableColumn<IssueRecords, java.time.LocalDate> issueDateCol;
    @FXML private TableColumn<IssueRecords, java.time.LocalDate> returnDateCol;

    private final IssueRecordDAOImplementation dao = new IssueRecordDAOImplementation();

    @FXML
    private void initialize() {
        issueIdCol.setCellValueFactory(new PropertyValueFactory<>("issueId"));
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        loadAllIssues();
    }

    private void loadAllIssues() {
        List<IssueRecords> allRecords = dao.getAllIssues();
        if (allRecords != null) {
            ObservableList<IssueRecords> observableList = FXCollections.observableArrayList(allRecords);
            issueTable.setItems(observableList);
        }
    }
}
