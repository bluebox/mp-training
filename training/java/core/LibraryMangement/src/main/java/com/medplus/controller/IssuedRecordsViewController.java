package com.medplus.controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import com.medplus.model.IssueRecord;
import com.medplus.service.LibraryService;

public class IssuedRecordsViewController {
    @FXML private TableView<IssueRecord> issueTable;

    private final LibraryService service = new LibraryService();

    @FXML
    public void initialize() {
        try {
            ObservableList<IssueRecord> list = FXCollections.observableArrayList(service.getAllIssuedRecords());
            issueTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
