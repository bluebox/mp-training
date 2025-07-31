// --- IssueBookController.java ---
package com.lms.controller;

import com.lms.daoImpl.BookDao;
import com.lms.daoImpl.MemberDao;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.IssueBook;
import com.lms.model.Member;
import com.lms.service.IssueBookServiceInterface;
import com.lms.serviceImpl.IssueBookServiceImpl;

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

    private final IssueBookServiceInterface issueBookService = new IssueBookServiceImpl();
    private Member currentMember;

    @FXML
    private void initialize() {
        loadCategories();

        issueCategoriesComboBox.setItems(categoryList);
        issueBookNameComboBox.setItems(bookList);

        issueFetchButton.setOnAction(event -> fetchMember());
        issueCategoriesComboBox.setOnAction(event -> {
            if (currentMember != null) {
                loadBooksForSelectedCategory();
            } else {
                showAlert(Alert.AlertType.WARNING, "Please fetch a valid member first.");
            }
        });
        issueBookButton.setOnAction(event -> issueBook());
    }

    private void loadCategories() {
        categoryList.setAll(
            Arrays.stream(BookCategory.values())
                  .map(BookCategory::toString)
                  .collect(Collectors.toList())
        );
    }

    private void fetchMember() {
        String input = issueTextField.getText().trim();

        if (input.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please enter a Member ID or Name.");
            return;
        }

        try {
            int id = Integer.parseInt(input);
            currentMember = MemberDao.getMemberById(id);
        } catch (NumberFormatException e) {
            currentMember = MemberDao.getAllMembers().stream()
                    .filter(m -> m.getName().equalsIgnoreCase(input))
                    .findFirst().orElse(null);
        }

        if (currentMember != null) {
            issueBooksLabel.setText("Member: " + currentMember.getName());
        } else {
            issueBooksLabel.setText("Member not found");
        }
    }

    private void loadBooksForSelectedCategory() {
        String selectedCategoryString = issueCategoriesComboBox.getValue();
        if (selectedCategoryString == null) return;

        BookCategory selectedCategory = Arrays.stream(BookCategory.values())
                .filter(cat -> cat.toString().equals(selectedCategoryString))
                .findFirst().orElse(null);

        if (selectedCategory == null) return;

        List<Book> availableBooks = BookDao.getInstance().getAvailableBooksByCategory(selectedCategory);

        bookList.clear();
        bookList.addAll(availableBooks.stream().map(Book::getBookTitle).collect(Collectors.toList()));
    }

    private void issueBook() {
        if (currentMember == null) {
            showAlert(Alert.AlertType.ERROR, "Please fetch a valid member first.");
            return;
        }

        String selectedBookTitle = issueBookNameComboBox.getValue();
        LocalDate dueDate = issueDueDatePicker.getValue();

        if (selectedBookTitle == null || dueDate == null) {
            showAlert(Alert.AlertType.WARNING, "Please select both a book and due date.");
            return;
        }

        List<Book> allAvailableBooks = BookDao.getInstance().getAvailableBooksByCategory(
                Arrays.stream(BookCategory.values())
                        .filter(cat -> cat.toString().equals(issueCategoriesComboBox.getValue()))
                        .findFirst().orElse(null)
        );

        Book selectedBook = allAvailableBooks.stream()
                .filter(book -> book.getBookTitle().equals(selectedBookTitle))
                .findFirst().orElse(null);

        if (selectedBook == null) {
            showAlert(Alert.AlertType.ERROR, "Selected book not found or unavailable.");
            return;
        }

        IssueBook issue = new IssueBook();
        issue.setMemberId(currentMember.getMemberId());
        issue.setBookId(selectedBook.getBookId());
        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(dueDate);
        issue.setStatus('I');

        boolean result = issueBookService.issueBook(issue);

        if (result) {
            showAlert(Alert.AlertType.INFORMATION, "Book issued successfully.");
            clearForm();
        } else {
            showAlert(Alert.AlertType.ERROR, "Failed to issue book.");
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

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Library System");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
