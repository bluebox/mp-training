package com.lms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReturnBookController {

    @FXML
    private TextField returnTextField;

    @FXML
    private Button returnFetchButton;

    @FXML
    private ComboBox<String> returnBookNameComboBox;

    @FXML
    private Button returnBookButton;

    @FXML
    private Label issueBooksLabel;

    private ObservableList<String> issuedBooks = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        returnFetchButton.setOnAction(event -> fetchMemberAndBooks());
        returnBookButton.setOnAction(event -> returnBook());
    }

    private void fetchMemberAndBooks() {
        String input = returnTextField.getText().trim();

        String memberName = "Member Name";
        issueBooksLabel.setText(memberName);
        issuedBooks.setAll("Book X", "Book Y", "Book Z");
        returnBookNameComboBox.setItems(issuedBooks);
    }

    private void returnBook() {
        String selectedBook = returnBookNameComboBox.getValue();
        String memberInput = returnTextField.getText().trim();

        if (selectedBook == null || selectedBook.isEmpty()) {
            System.out.println("No book selected for return.");
            return;
        }
        System.out.println("Book returned: " + selectedBook + " by " + memberInput);
    }
}
