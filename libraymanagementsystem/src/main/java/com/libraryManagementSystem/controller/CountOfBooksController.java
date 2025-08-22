package com.libraryManagementSystem.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.CustomCategoryCount;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.utilities.BookCategory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CountOfBooksController implements Initializable {

	private BookServices bookService = new BookServicesImpl();
	@FXML
	private TableView<CustomCategoryCount> categoryCountTableView;
	@FXML
	private TableColumn<CustomCategoryCount, BookCategory> categoryColumn;
	@FXML
	private TableColumn<CustomCategoryCount, Integer> bookCount;
	@FXML
	private Label error;

	@FXML
	public void switchTOReports() throws Exception {
		App.setRoot("Reports");
	}

	@FXML
	public void switchTOHome() throws Exception {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<CustomCategoryCount> categories;
		try {
			categoryCountTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

			categories = bookService.getBookCountByCategory();

			categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
			bookCount.setCellValueFactory(new PropertyValueFactory<>("count"));

			ObservableList<CustomCategoryCount> bookList = FXCollections.observableArrayList();
			categories.forEach(book -> {
				bookList.add(book);
			});
			categoryCountTableView.setItems(bookList);
		} catch (InvalidException e) {

			error.setText(e.getMessage());
		}

	}
}
