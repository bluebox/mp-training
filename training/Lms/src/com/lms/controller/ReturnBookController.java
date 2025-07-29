package com.lms.controller;

import com.lms.service.ReturnBookServiceInterface;
import com.lms.serviceImpl.ReturnBookServiceImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;

public class ReturnBookController {

    @FXML private TextField returnTextField;
    @FXML private Button returnFetchButton;
    @FXML private ComboBox<String> returnBookNameComboBox;
    @FXML private Button returnBookButton;
    @FXML private Label issueBooksLabel;
    @FXML private RadioButton activeRadio;
    @FXML private RadioButton inactiveRadio;

    private final ReturnBookServiceInterface returnBookService = new ReturnBookServiceImpl();
    private ToggleGroup statusToggleGroup;

    @FXML
    public void initialize() {
        statusToggleGroup = new ToggleGroup();
        activeRadio.setToggleGroup(statusToggleGroup);
        inactiveRadio.setToggleGroup(statusToggleGroup);

        returnFetchButton.setOnAction(e -> fetchMemberAndBooks());
        returnBookButton.setOnAction(e -> returnBook());
    }

    private void fetchMemberAndBooks() {
        String mobileNumber = returnTextField.getText().trim();

        if (!mobileNumber.matches("\\d{10}")) {
            showAlert("Invalid Mobile Number", "Enter a valid 10-digit mobile number.");
            return;
        }

        String memberName = returnBookService.getMemberNameByMobile(mobileNumber);
        if (memberName == null) {
            showAlert("Not Found", "No member found with the given mobile number.");
            return;
        }

        issueBooksLabel.setText(memberName);
        List<String> books = returnBookService.getIssuedBooksByMobile(mobileNumber);
        returnBookNameComboBox.setItems(FXCollections.observableArrayList(books));
    }

    private void returnBook() {
        String mobile = returnTextField.getText().trim();
        String bookName = returnBookNameComboBox.getValue();

        if (!mobile.matches("\\d{10}")) {
            showAlert("Invalid Input", "Mobile number is not valid.");
            return;
        }

        if (bookName == null || bookName.isEmpty()) {
            showAlert("Missing Book", "Please select a book to return.");
            return;
        }

        String status = activeRadio.isSelected() ? "Active" : inactiveRadio.isSelected() ? "Inactive" : null;

        if (status == null) {
            showAlert("Missing Status", "Please select the return status.");
            return;
        }

        boolean success = returnBookService.returnBook(mobile, bookName, status);

        if (success) {
            showAlert("Success", "Book returned successfully.");
            returnBookNameComboBox.getSelectionModel().clearSelection();
            statusToggleGroup.selectToggle(null);
        } else {
            showAlert("Failure", "Book return failed. Please try again.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
