package com.libraryManagementSystem.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.dao.ReportsDao;
import com.libraryManagementSystem.dao.impl.ReportsDaoImpl;
import com.libraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ActiveBooksController implements Initializable {

	private final ReportsDao reportsService = new ReportsDaoImpl();

	@FXML
	private TableView<CustomActiveIssuedBooks> activeIssuedBooksTableView;
	@FXML
	private TableColumn<CustomActiveIssuedBooks, Integer> memberId;
	@FXML
	private TableColumn<CustomActiveIssuedBooks, String> memberName;
	@FXML
	private TableColumn<CustomActiveIssuedBooks, Integer> bookId;
	@FXML
	private TableColumn<CustomActiveIssuedBooks, String> bookTitle;
	@FXML
	private TableColumn<CustomActiveIssuedBooks, Date> issuedDate;
	@FXML
	private Label error;

	@FXML
	public void switchToReports() throws Exception {
		App.setRoot("Reports");
	}

	@FXML
	public void switchToHome() throws Exception {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<CustomActiveIssuedBooks> activeIssuedBooks;
		try {
			activeIssuedBooksTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			activeIssuedBooks = reportsService.getActiveIssuedBooks();
			memberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
			memberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
			bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
			bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
			issuedDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

			ObservableList<CustomActiveIssuedBooks> bookList = FXCollections.observableArrayList();

			activeIssuedBooks.forEach(book -> {
				bookList.add(book);
			});

			activeIssuedBooksTableView.setItems(bookList);
		} catch (DatabaseOperationException e) {
			error.setText(e.getMessage());
		}

	}
}
