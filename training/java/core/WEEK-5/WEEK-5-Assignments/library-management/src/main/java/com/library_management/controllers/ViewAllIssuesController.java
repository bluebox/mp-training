package com.library_management.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.library_management.App;
import com.library_management.domain.IssueRecord;
import com.library_management.services.IssueRecordServiceInterface;
import com.library_management.services.implementation.IssueRecordServiceImplementation;
import com.library_management.utilities.IssuesTableView;
import com.library_management.utilities.ResponseHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ViewAllIssuesController {

	IssueRecordServiceInterface service = new IssueRecordServiceImplementation();

	@FXML
	private TableView<IssuesTableView> viewIssuesTable;
	@FXML
	private TableColumn<IssuesTableView, Integer> issueIdColumn;
	@FXML
	private TableColumn<IssuesTableView, Integer> bookIdColumn;
	@FXML
	private TableColumn<IssuesTableView, Integer> memberIdColumn;
	@FXML
	private TableColumn<IssuesTableView, String> statusColumn;
	@FXML
	private TableColumn<IssuesTableView, String> issueDateColumn;
	@FXML
	private TableColumn<IssuesTableView, String> returnDateColumn;
	@FXML
	private Text message;

	
	@FXML
    private void backButton() throws IOException {
        App.setRoot("issueReturnMain");
    }

    @FXML
    private void homeButton() throws IOException {
        App.setRoot("home");
    }
	private ObservableList<IssuesTableView> issueData = FXCollections.observableArrayList();

	@FXML
	public void initialize() throws SQLException {

		issueIdColumn.setCellValueFactory(new PropertyValueFactory<>("issueId"));
		bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
		returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

		List<IssueRecord> issues = service.getAllIssues();
//		for (IssueRecord issue : issues) {
//			issueData.add(new IssuesTableView(issue.getIssueId(), issue.getBookId(), issue.getMemberId(), issue.getStatus(),
//					issue.getIssueDate().toString(), issue.getReturnDate().toString()));
//		}

		for (IssueRecord issue : issues) {
		    String returnDateStr = (issue.getReturnDate() != null) ? issue.getReturnDate().toString() : "Not Returned";
		    issueData.add(new IssuesTableView(
		        issue.getIssueId(),
		        issue.getBookId(),
		        issue.getMemberId(),
		        issue.getStatus(),
		        issue.getIssueDate().toString(),
		        returnDateStr
		    ));
		}

		viewIssuesTable.setItems(issueData);
		ResponseHandler.showResponse(message, "Issue Records Fetched Successfully...", null);
	}
}
