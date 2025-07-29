package com.lms.controller;

import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.IssueBook;
import com.lms.model.Member;
import com.lms.serviceImpl.IssueBookServiceImpl;
import com.lms.util.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class IssueBookController {

    @FXML private TextField issueTextField;
    @FXML private Button issueFetchButton;
    @FXML private Label issueBooksLabel;
    @FXML private ComboBox<String> issueCategoriesComboBox;
    @FXML private ComboBox<String> issueBookNameComboBox;
    @FXML private DatePicker issueDueDatePicker;
    @FXML private Button issueBookButton;

    private final ObservableList<String> categoryList = FXCollections.observableArrayList();
    private final ObservableList<String> bookList = FXCollections.observableArrayList();

    private final IssueBookServiceImpl issueBookService = new IssueBookServiceImpl();
    private Member currentMember;

    @FXML
    private void initialize() {
        issueCategoriesComboBox.setItems(categoryList);
        issueBookNameComboBox.setItems(bookList);

        issueFetchButton.setOnAction(event -> fetchMember());
        issueCategoriesComboBox.setOnAction(event -> loadBooksForCategory());
        issueBookButton.setOnAction(event -> issueBook());
    }

    private void fetchMember() {
        String mobile = issueTextField.getText().trim();
        if (mobile.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Mobile number is required.");
            return;
        }

        try {
            Validator.validateMobileNumber(mobile);
            currentMember = issueBookService.getMemberByMobile(mobile);
            issueBooksLabel.setText("Member: " + currentMember.getName());
            loadAvailableCategories(); 
        } catch (InvalidInputException e) {
            showAlert(Alert.AlertType.WARNING, e.getMessage());
            issueBooksLabel.setText("Member not found");
            currentMember = null;
        }
    
    
    issueDueDatePicker.setDayCellFactory(picker -> new DateCell() {
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            if (date.isBefore(LocalDate.now())) {
                setDisable(true);
                setStyle("-fx-background-color: #ffc0cb;");
            }
        }
    });

    //issueDueDatePicker.setValue(LocalDate.now().plusDays(1));
    }


    private void loadAvailableCategories() {
        Set<BookCategory> categories = issueBookService.getAllAvailableBooks(null).stream()
                .filter(book -> book.getStatus() == 'A' && book.getAvailability() == 'A')
                .map(Book::getBookCategory)
                .collect(Collectors.toSet());

        categoryList.setAll(categories.stream().map(Enum::toString).toList());
    }

    private void loadBooksForCategory() {
        String selectedCategory = issueCategoriesComboBox.getValue();
        if (selectedCategory == null) return;

        List<Book> availableBooks = issueBookService.getAvailableBooksByCategory(BookCategory.valueOf(selectedCategory.toUpperCase()));
        List<String> bookTitles = availableBooks.stream()
                .filter(book -> book.getAvailability() == 'A' && book.getStatus() == 'A')
                .map(Book::getBookTitle)
                .collect(Collectors.toList());

        bookList.setAll(bookTitles);
    }

    private void issueBook() {
        if (currentMember == null) {
            showAlert(Alert.AlertType.ERROR, "Fetch member first.");
            return;
        }

        String selectedBookTitle = issueBookNameComboBox.getValue();
        String selectedCategory = issueCategoriesComboBox.getValue();
        LocalDate returnDate = issueDueDatePicker.getValue();

        if (selectedBookTitle == null || selectedCategory == null || returnDate == null) {
            showAlert(Alert.AlertType.WARNING, "Select category, book, and due date.");
            return;
        }

        
        List<Book> availableBooks = issueBookService.getAvailableBooksByCategory(BookCategory.valueOf(selectedCategory.toUpperCase()));
        Book selectedBook = availableBooks.stream()
                .filter(book -> book.getBookTitle().equals(selectedBookTitle))
                .findFirst().orElse(null);

        if (selectedBook == null) {
            showAlert(Alert.AlertType.ERROR, "Selected book not found.");
            return;
        }

        IssueBook issue = new IssueBook();
        issue.setMemberId(currentMember.getMemberId());
        issue.setBookId(selectedBook.getBookId());
        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(returnDate);

        boolean success = issueBookService.issueBook(issue);
        if (success) {
            
            selectedBook.setAvailability('U');
            issueBookService.updateBookAvailability(selectedBook.getBookId(), 'U');
            showAlert(Alert.AlertType.INFORMATION, "Book issued successfully.");
            clearForm();
        } else {
            showAlert(Alert.AlertType.ERROR, "Book issue failed.");
        }
    }

    private void clearForm() {
        issueTextField.clear();
        issueBooksLabel.setText("");
        issueCategoriesComboBox.getSelectionModel().clearSelection();
        issueBookNameComboBox.getItems().clear();
        issueDueDatePicker.setValue(null);
        currentMember = null;
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
