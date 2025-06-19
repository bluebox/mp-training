package com.library.controller;

import java.io.IOException;

import com.library.domain.IssueRecord;
import com.library.service.impl.LibraryServiceImpl;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ViewIssuedBooksController {

    @FXML private TableView<IssueRecord> issueTable;
    @FXML private TableColumn<IssueRecord, Integer> bookIdCol;
    @FXML private TableColumn<IssueRecord, Integer> memberIdCol;
    @FXML private TableColumn<IssueRecord, String> issueDateCol;
    @FXML private TableColumn<IssueRecord, String> returnDateCol;
    @FXML
    private Button backButton;


    private LibraryServiceImpl service = new LibraryServiceImpl();

    @FXML
    public void initialize() {
        bookIdCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getBookId()).asObject());
        memberIdCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMemberId()).asObject());
        issueDateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIssueDate().toString()));
        returnDateCol.setCellValueFactory(data -> new SimpleStringProperty(
            data.getValue().getReturnDate() != null ? data.getValue().getReturnDate().toString() : "Not Returned"));

        try {
            ObservableList<IssueRecord> list = FXCollections.observableArrayList(service.viewAllRecords());
            issueTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleBack(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Library - Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
