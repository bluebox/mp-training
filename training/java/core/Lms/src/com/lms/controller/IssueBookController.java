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

import java.sql.SQLException;
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
        try {
            currentMember = Validator.validateAndFetchMemberByMobile(mobile, issueBookService);
            issueBooksLabel.setText("Member: " + currentMember.getName());
            loadAvailableCategories(); 
        } catch (InvalidInputException | SQLException e) {
            showAlert(Alert.AlertType.WARNING, "Fetch Error",e.getMessage());
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
    }


    private void loadAvailableCategories() {
        Set<BookCategory> categories = null;
		try {
			categories = issueBookService.getAllAvailableBooks(null).stream()
			        .filter(book -> book.getStatus() == 'A' && book.getAvailability() == 'A')
			        .map(Book::getBookCategory)
			        .collect(Collectors.toSet());
		} catch (SQLException e) {
			showAlert(Alert.AlertType.ERROR, "Load Error", e.getMessage());
		}

        categoryList.setAll(categories.stream().map(Enum::toString).toList());
    }

    private void loadBooksForCategory() {
        String selectedCategory = issueCategoriesComboBox.getValue();
        if (selectedCategory == null) return;

        List<Book> availableBooks = null;
		try {
			availableBooks = issueBookService.getAvailableBooksByCategory(BookCategory.valueOf(selectedCategory.toUpperCase()));
		} catch (SQLException e) {
			showAlert(Alert.AlertType.ERROR, "Load Error", e.getMessage());
		}
        List<String> bookTitles = availableBooks.stream()
                .filter(book -> book.getAvailability() == 'A' && book.getStatus() == 'A')
                .map(Book::getBookTitle)
                .collect(Collectors.toList());

        bookList.setAll(bookTitles);
    }

    private void issueBook() {
        if (currentMember == null) {
            showAlert(Alert.AlertType.ERROR,"Fetch Error", "Fetch member first.");
            return;
        }

        String selectedBookTitle = issueBookNameComboBox.getValue();
        String selectedCategory = issueCategoriesComboBox.getValue();
        LocalDate returnDate = issueDueDatePicker.getValue();

        if  ( selectedCategory == null) {
            showAlert(Alert.AlertType.WARNING,"Input Error" ,"Select book Category.");
            return;
        }else if (selectedBookTitle == null) {
            showAlert(Alert.AlertType.WARNING,"Input Error", "Select Book Titke.");
            return;
        }else if (returnDate == null || returnDate.isBefore(LocalDate.now())) {
			showAlert(Alert.AlertType.WARNING,"Input Error", "Select a valid due date.");
			return;
		}

        
        List<Book> availableBooks;
		try {
			availableBooks = issueBookService.getAvailableBooksByCategory(BookCategory.valueOf(selectedCategory.toUpperCase()));
		} catch (SQLException e) {
			showAlert(Alert.AlertType.ERROR, "Load Error", e.getMessage());
			return;
		}

		if (availableBooks.isEmpty()) {
			showAlert(Alert.AlertType.WARNING,"No Books Available", "No books available in this category.");
			return;
		}
        Book selectedBook = availableBooks.stream()
                .filter(book -> book.getBookTitle().equals(selectedBookTitle))
                .findFirst().orElse(null);

        if (selectedBook == null) {
            showAlert(Alert.AlertType.ERROR,"Data Error", "Selected book not found.");
            return;
        }

        IssueBook issue = new IssueBook();
        issue.setMemberId(currentMember.getMemberId());
        issue.setBookId(selectedBook.getBookId());
        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(returnDate);

        boolean success = false;
		try {
			success = issueBookService.issueBook(issue);
		} catch (SQLException e) {
			showAlert(Alert.AlertType.ERROR, "Issue Error", e.getMessage());
		}
        if (success) {
            
            selectedBook.setAvailability('U');
            try {
				issueBookService.updateBookAvailability(selectedBook.getBookId(), 'U');
			} catch (SQLException e) {
				showAlert(Alert.AlertType.ERROR, "Update Error", e.getMessage());
			}
            showAlert(Alert.AlertType.INFORMATION,"Success", "Book issued successfully.");
            clearForm();
        } else {
            showAlert(Alert.AlertType.ERROR,"Error", "Book issue failed.");
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

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
