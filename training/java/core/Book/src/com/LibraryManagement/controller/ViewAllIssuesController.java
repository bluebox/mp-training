package com.LibraryManagement.controller;

import com.LibraryManagement.models.IssueRecords;
import com.LibraryManagement.service.implementation.IssueRecordServiceImplementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ViewAllIssuesController {

	@FXML
	private TableView<IssueRecords> issueTable;
	@FXML
	private TableColumn<IssueRecords, Integer> issueIdCol;
	@FXML
	private TableColumn<IssueRecords, Integer> bookIdCol;
	@FXML
	private TableColumn<IssueRecords, Integer> memberIdCol;
	@FXML
	private TableColumn<IssueRecords, String> availabilityCol;
	@FXML
	private TableColumn<IssueRecords, java.time.LocalDate> issueDateCol;
	@FXML
	private TableColumn<IssueRecords, java.time.LocalDate> returnDateCol;

	private final IssueRecordServiceImplementation dao = new IssueRecordServiceImplementation();

	@FXML
	public void Back(ActionEvent event) {
		try {
			MainController.switchScene("IssueReturn/IssueReturn.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void initialize() {
		issueIdCol.setCellValueFactory(new PropertyValueFactory<>("issueId"));
		bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		memberIdCol.setCellValueFactory(new PropertyValueFactory<>("memberId"));

		availabilityCol.setCellValueFactory(cellData -> {
			String availability = cellData.getValue().getAvailability();
			String displayAvailability = "Returned";
			if ("a".equalsIgnoreCase(availability)) {
				displayAvailability = "Returned";
			} else if ("i".equalsIgnoreCase(availability)) {
				displayAvailability = "Issued";
			}
			return new javafx.beans.property.SimpleStringProperty(displayAvailability);
		});

		issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
		returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

		loadAllIssues();
	}

	private void loadAllIssues() {
		List<IssueRecords> allRecords;
		try {
			allRecords = dao.getAllIssues();
			if (allRecords != null) {
				ObservableList<IssueRecords> observableList = FXCollections.observableArrayList(allRecords);
				issueTable.setItems(observableList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
