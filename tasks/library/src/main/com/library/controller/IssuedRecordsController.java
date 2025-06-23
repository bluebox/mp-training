package main.com.library.controller;

import main.com.library.dao.IssueRecordDAO;
import main.com.library.domain.IssueRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class IssuedRecordsController {

    @FXML private TableView<IssueRecord> issueTable;
    @FXML private TableColumn<IssueRecord, Integer> issueIdCol;
    @FXML private TableColumn<IssueRecord, Integer> bookIdCol;
    @FXML private TableColumn<IssueRecord, Integer> memberIdCol;
    @FXML private TableColumn<IssueRecord, Character> statusCol;
    @FXML private TableColumn<IssueRecord, java.time.LocalDate> issueDateCol;
    @FXML private TableColumn<IssueRecord, java.time.LocalDate> returnDateCol;

    private final IssueRecordDAO issueRecordDAO = new IssueRecordDAO();

    @FXML
    public void initialize() {
        issueIdCol.setCellValueFactory(new PropertyValueFactory<>("issueId"));
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        List<IssueRecord> records = issueRecordDAO.getAllRecords();
        ObservableList<IssueRecord> data = FXCollections.observableArrayList(records);
        issueTable.setItems(data);
    }
}