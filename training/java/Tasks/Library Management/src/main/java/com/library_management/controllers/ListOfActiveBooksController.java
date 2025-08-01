package com.library_management.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.library_management.App;
import com.library_management.dao.implementation.ReportsDaoImplementation;
import com.library_management.domain.IssueRecord;
import com.library_management.utilities.CustomClassForCategoryCountTable;
import com.library_management.utilities.ListOfActiveBooksTable;
import com.library_management.utilities.ResponseHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ListOfActiveBooksController {
	
	@FXML
    private TableView<ListOfActiveBooksTable> activeBooksCountTable;
    @FXML
   
    private TableColumn<ListOfActiveBooksTable, String> memberIdColumn;
    
    @FXML
    private TableColumn<ListOfActiveBooksTable, String> nameColumn;
    
    @FXML
    private TableColumn<ListOfActiveBooksTable, String> bookTitleColumn;
 
    
    @FXML
    private TableColumn<ListOfActiveBooksTable, String> issueRecordStatusColumn;
    
    @FXML
    private TableColumn<ListOfActiveBooksTable, String> bookStatusColumn;
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
    private ObservableList<ListOfActiveBooksTable> countData = FXCollections.observableArrayList();
	
	@FXML
    public void initialize() {
   
       memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
      nameColumn.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        issueRecordStatusColumn.setCellValueFactory(new PropertyValueFactory<>("issueRecordStatus"));
      bookStatusColumn.setCellValueFactory(new PropertyValueFactory<>("bookStatus"));
        try {
			List<List<String>> countMap=new ReportsDaoImplementation().getActiveIssuedBooks();
			countMap.forEach(b->countData.add(new ListOfActiveBooksTable(b.get(0),b.get(1),b.get(2),b.get(3),b.get(4))));
		} catch (Exception e) {
			ResponseHandler.showResponse(message, "Cannot Fetch Books Data...", Color.RED);
		}
        activeBooksCountTable.setItems(countData);
		ResponseHandler.showResponse(message, "Report Data Fetched Succesfully...", null);
    }
}
