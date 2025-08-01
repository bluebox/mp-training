package com.library_management.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.library_management.App;
import com.library_management.dao.implementation.ReportsDaoImplementation;
import com.library_management.domain.Book;
import com.library_management.exceptions.DatabaseException;
import com.library_management.services.implementation.BookServiceImplementation;
import com.library_management.utilities.CustomBookForTableView;
import com.library_management.utilities.CustomClassForCategoryCountTable;
import com.library_management.utilities.ResponseHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CategoryCountController {
//	Declaring all fields
	@FXML
    private TableView<CustomClassForCategoryCountTable> categoryCountTable;
    @FXML
    private TableColumn<CustomClassForCategoryCountTable, Integer> countColumn;
    @FXML
    private TableColumn<CustomClassForCategoryCountTable, String> categoryColumn;
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
    private ObservableList<CustomClassForCategoryCountTable> countData = FXCollections.observableArrayList();
	
	@FXML
    public void initialize() {
		
//		Creating cell value factories for AllPermission columns
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        try {
			Map<Object, Long> countMap=new ReportsDaoImplementation().countOfBooksPerCategory();
			countMap.forEach((k,v) -> countData.add(new CustomClassForCategoryCountTable(String.valueOf(k),Integer.parseInt(String.valueOf(v)))));
		} catch (Exception e) {
			ResponseHandler.showResponse(message, "Cannot Fetch Books Data...", Color.RED);
		}
		
        categoryCountTable.setItems(countData);
		ResponseHandler.showResponse(message, "Report Data Fetched Succesfully...", null);
    }
}
