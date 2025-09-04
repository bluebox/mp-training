package com.lms.controller;

import com.lms.main.Main;
import com.lms.model.Issue_Records;
import com.lms.service.IssueLogService;
import com.lms.service.impl.IssueLogServiceImpl;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class ViewIssuedRecordsController {
	
	IssueLogService service;
    
    @FXML
    private TableView<Issue_Records> issueRecordsTable;
    
    @FXML
    private TableColumn<Issue_Records, String> id;
    
    @FXML
    private TableColumn<Issue_Records, String> book_id;
    
    @FXML
    private TableColumn<Issue_Records, String> member_id;
    
    @FXML
    private TableColumn<Issue_Records, String> issue_status;
    
    @FXML
    private TableColumn<Issue_Records, String> issued_date;
    
    @FXML
    private TableColumn<Issue_Records, String> returned_date;
    
    @FXML
    private Button backButton;
    
    private ObservableList<Issue_Records> issuedRecordList = FXCollections.observableArrayList();
    

    @FXML
    public void initialize() {
    	
    	service = new IssueLogServiceImpl();
    	List<Issue_Records> issuedRecords = service.getAllIssuedRecords();
    	updaterecordsList(issuedRecords);
        id.setCellValueFactory(new PropertyValueFactory<>("issueId"));
        book_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        member_id.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        issue_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        issued_date.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        //returned_date.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        returned_date.setCellValueFactory(cellData -> {
            Date date = cellData.getValue().getReturnDate();
            return new ReadOnlyObjectWrapper<>(date != null ? date.toString() : "Not Yet Returned ");
        });
        issueRecordsTable.setItems(issuedRecordList);
    } 

    public void updaterecordsList(List<Issue_Records> records) {
    	issuedRecordList.clear();
    	issuedRecordList.addAll(records);
    }  
    
    @FXML
    private void handleBackButton() {
        try {
			Main.changePage("LibraryHome");
		} catch (IOException e) {
			System.out.println("Had a problem with going back to main: " + e.getMessage());
		}
    }
}

