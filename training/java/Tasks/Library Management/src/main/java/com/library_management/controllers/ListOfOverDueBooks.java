package com.library_management.controllers;

import java.io.IOException;
import java.util.List;

import com.library_management.App;
import com.library_management.dao.implementation.ReportsDaoImplementation;
import com.library_management.domain.IssueRecord;
import com.library_management.utilities.CustomClassForListOfOverdueBooks;
import com.library_management.utilities.ResponseHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ListOfOverDueBooks {
	@FXML
    private TableView<CustomClassForListOfOverdueBooks> overDueBooksCountTable;
    @FXML
   
    private TableColumn<CustomClassForListOfOverdueBooks, String> booksColumn;
    
    @FXML
    private TableColumn<CustomClassForListOfOverdueBooks, String> membersColumn;
    
    @FXML
    private TableColumn<CustomClassForListOfOverdueBooks, String> issueDateColumn;
    @FXML
    private Text message;
    @FXML
    private void backButton() throws IOException {
        App.setRoot("reports");
    }

    @FXML
    private void homeButton() throws IOException {
        App.setRoot("home");
    }
//    Creating Observable list
    private ObservableList<CustomClassForListOfOverdueBooks> countData = FXCollections.observableArrayList();
	
	@FXML
    public void initialize() {
		
//		Creating cell value factories for AllPermission columns
        
        booksColumn.setCellValueFactory(new PropertyValueFactory<>("Book Id"));
        membersColumn.setCellValueFactory(new PropertyValueFactory<>("Member Id"));
        issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("Issue Date"));

        try {
			List<IssueRecord> countMap=new ReportsDaoImplementation().getOverdueBooks();
			countMap.forEach(b->countData.add(new CustomClassForListOfOverdueBooks(b.getBookId(),b.getMemberId(),b.getIssueDate())));
		} catch (Exception e) {
			ResponseHandler.showResponse(message, "Cannot Fetch Books Data...", Color.RED);
		}
		
        overDueBooksCountTable.setItems(countData);
		ResponseHandler.showResponse(message, "Report Data Fetched Succesfully...", null);
    }
}
