package com.libraryManagementSystem.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.dao.ReportsDao;
import com.libraryManagementSystem.dao.impl.ReportsDaoImpl;
import com.libraryManagementSystem.domain.CustomOverDueBooks;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BooksOverDueController implements Initializable {
	private final ReportsDao reportsService = new ReportsDaoImpl();
	@FXML
	private TableView<CustomOverDueBooks> overDueBookTableView;
	@FXML
	private TableColumn<CustomOverDueBooks, Integer> bookId;
	@FXML
	private TableColumn<CustomOverDueBooks, String> bookName;
	@FXML
	private TableColumn<CustomOverDueBooks, Integer> memberId;
	@FXML
	private TableColumn<CustomOverDueBooks, String> memberName;
	@FXML
	private TableColumn<CustomOverDueBooks, LocalDate> issuedDate;
	@FXML
	private Label error;

	@FXML
	public void switchTOReports() throws Exception {
		App.setRoot("Reports");
	}

	public void switchToPrimary() throws Exception {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<CustomOverDueBooks> overDueBooks;
		try {
			overDueBookTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

			overDueBooks = reportsService.getOverDueBooks();

			bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
			bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
			memberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
			memberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
			issuedDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

			ObservableList<CustomOverDueBooks> bookList = FXCollections.observableArrayList();
			overDueBooks.forEach(book -> {
				bookList.add(book);

			});
			overDueBookTableView.setItems(bookList);
		} catch (DatabaseOperationException e) {

			error.setText(e.getMessage());
		}

	}
}
