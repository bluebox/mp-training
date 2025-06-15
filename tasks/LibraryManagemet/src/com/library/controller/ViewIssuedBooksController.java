package com.library.controller;

import com.library.domain.IssueRecord;
import com.library.service.IssueBookService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;

public class ViewIssuedBooksController {

    @FXML private TableView<IssueRecord> issueTable;
    @FXML private TableColumn<IssueRecord, Integer> bookIdCol;
    @FXML private TableColumn<IssueRecord, Integer> memberIdCol;
    @FXML private TableColumn<IssueRecord, String> issueDateCol;
    @FXML private TableColumn<IssueRecord, String> returnDateCol;

    private IssueBookService service = new IssueBookService();

    @FXML
    public void initialize() {
        bookIdCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getBookId()).asObject());
        memberIdCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMemberId()).asObject());
        issueDateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIssueDate().toString()));
        returnDateCol.setCellValueFactory(data -> new SimpleStringProperty(
            data.getValue().getReturnDate() != null ? data.getValue().getReturnDate().toString() : "Not Returned"));

        try {
            ObservableList<IssueRecord> list = FXCollections.observableArrayList(service.getAllIssuedBooks());
            issueTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
