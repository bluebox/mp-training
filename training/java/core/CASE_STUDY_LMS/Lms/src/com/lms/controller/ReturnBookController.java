package com.lms.controller;

import com.lms.exceptions.InvalidInputException;
import com.lms.service.ReturnBookServiceInterface;
import com.lms.serviceImpl.ReturnBookServiceImpl;
import com.lms.util.Validator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
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

        try {
            String memberName = Validator.validateAndFetchMemberNameByMobile(mobileNumber, returnBookService);
            issueBooksLabel.setText("Member: "+memberName);

            List<String> books = returnBookService.getIssuedBooksByMobile(mobileNumber);
            returnBookNameComboBox.setItems(FXCollections.observableArrayList(books));

        } catch (InvalidInputException | SQLException e) {
            showAlert("Validation Error", e.getMessage());
        }
        }


    private void returnBook() {
        String mobile = returnTextField.getText().trim();
        String bookName = returnBookNameComboBox.getValue();
        String status = activeRadio.isSelected() ? "Active" : inactiveRadio.isSelected() ? "Inactive" : null;

        try {
            Validator.validateReturnBookInputs(mobile, bookName, status);

            boolean success = returnBookService.returnBook(mobile, bookName, status);

            if (success) {
                showAlert("Success", "Book returned successfully.");
                returnBookNameComboBox.getSelectionModel().clearSelection();
                statusToggleGroup.selectToggle(null);
            } else {
                showAlert("Failure", "Book return failed. Please try again.");
            }

        } catch (InvalidInputException e) {
            showAlert("Invalid Input", e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
