package com.library.controller;

import java.io.IOException;

import com.library.service.impl.LibraryServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReturnBookController {

	@FXML
	private TextField bookIdField;
	@FXML
	private TextField memberIdField;
	@FXML
	private Button backButton;

	@FXML
	private void handleReturnBook() {
		String bookIdStr = bookIdField.getText().trim();
		String memberIdStr = memberIdField.getText().trim();

		if (bookIdStr.isEmpty() || memberIdStr.isEmpty()) {
			showAlert(Alert.AlertType.WARNING, "Validation Error", "Both Book ID and Member ID are required.");
			return;
		}

		try {
			int bookId = Integer.parseInt(bookIdStr);
			int memberId = Integer.parseInt(memberIdStr);

			LibraryServiceImpl service = new LibraryServiceImpl();

			if (!service.returnBook(bookId, memberId)) {
				showAlert(Alert.AlertType.ERROR, "Invalid Details",
						"No active issue found for this Book ID and Member ID.");
				return;
			}
			else
			{
				showAlert(Alert.AlertType.INFORMATION, "Success", "Book returned successfully!");
				clearFields();
				
			}
			

		} catch (NumberFormatException e) {
			showAlert(Alert.AlertType.ERROR, "Input Error", "Book ID and Member ID must be numbers.");
		} catch (Exception e) {
			e.printStackTrace();
			showAlert(Alert.AlertType.ERROR, "Database Error", "Error returning the book.");
		}
	}

	@FXML
	private void handleCancel() {
		Stage stage = (Stage) bookIdField.getScene().getWindow();
		stage.close();
	}

	private void showAlert(Alert.AlertType type, String title, String message) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void clearFields() {
		bookIdField.clear();
		memberIdField.clear();
	}

	@FXML
	private void handleBack(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/UI/Home.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Library - Home");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
