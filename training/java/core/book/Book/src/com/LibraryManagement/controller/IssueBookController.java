package com.LibraryManagement.controller;

import com.LibraryManagement.models.IssueRecords;
import com.LibraryManagement.service.implementation.IssueRecordServiceImplementation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class IssueBookController implements Initializable {

	@FXML
	private ComboBox<Integer> bookIdComboBox;
	@FXML
	private ComboBox<Integer> memberIdComboBox;
	@FXML
	private DatePicker issueDatePicker;

	@FXML
	private Label bookIdError;
	@FXML
	private Label memberIdError;
	@FXML
	private Label issueDateError;

	private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			loadBookIds();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			loadMemberIds();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadBookIds() throws Exception {
		List<Integer> bookIds = issueService.getAvailableBookIds();
		bookIdComboBox.getItems().addAll(bookIds);
	}

	private void loadMemberIds() throws Exception {
		List<Integer> memberIds = issueService.getValidMemberIds();
		memberIdComboBox.getItems().addAll(memberIds);
	}

	@FXML
	private void handleIssueBook() {
		clearErrors();

		Integer bookId = bookIdComboBox.getValue();
		Integer memberId = memberIdComboBox.getValue();
		LocalDate issueDate = issueDatePicker.getValue();

		boolean valid = true;

		if (bookId == null) {
			bookIdError.setText("Book ID is required");
			valid = false;
		}

		if (memberId == null) {
			memberIdError.setText("Member ID is required");
			valid = false;
		}

		if (issueDate == null) {
			issueDateError.setText("Issue date is required");
			valid = false;
		}

		if (!valid)
			return;

		IssueRecords record = new IssueRecords(bookId, memberId, "I", issueDate);

		try {
			boolean success = issueService.issueBook(record);
			if (success) {
				showAlert("Success", "Book issued successfully!");
				clearForm();
			} else {
				showAlert("Failed", "Book could not be issued. It may already be issued.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void Back(ActionEvent event) {
		try {
			MainController.switchScene("IssueReturn/IssueReturn.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearErrors() {
		bookIdError.setText("");
		memberIdError.setText("");
		issueDateError.setText("");
	}

	private void clearForm() {
		bookIdComboBox.setValue(null);
		memberIdComboBox.setValue(null);
		issueDatePicker.setValue(null);
	}

	private void showAlert(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}
}