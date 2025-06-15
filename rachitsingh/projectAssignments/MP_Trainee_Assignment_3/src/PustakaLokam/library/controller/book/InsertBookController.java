package PustakaLokam.library.controller.book;

import PustakaLokam.library.enums.AvailabilityStatus;
import PustakaLokam.library.enums.BookCondition;
import PustakaLokam.library.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import PustakaLokam.library.service.BookService;
import javafx.scene.control.ListView;

public class InsertBookController {
    @FXML
    private TextField bookTitleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField bookCategoryField;
    @FXML
    private ChoiceBox<BookCondition> bookConditionChoiceBox;
    @FXML
    private ListView<Book> listOfBooksView;
    private ObservableList<Book> stagedBooks;
    private BookService bookService;

    @FXML
    public void initialize() {
        bookConditionChoiceBox.setItems(FXCollections.observableArrayList(BookCondition.values()));
        bookConditionChoiceBox.setValue(BookCondition.ACTIVE);

        stagedBooks = FXCollections.observableArrayList();
        listOfBooksView.setItems(stagedBooks);

        bookService = new BookService();
    }

    @FXML
    private void handleBookInsertion() {
        String bookTitle = bookTitleField.getText();
        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            displayAlertMessage("Book Title is required.");
            return;
        }
        String author = authorField.getText();
        String category = bookCategoryField.getText();
        BookCondition bookCondition = bookConditionChoiceBox.getValue();

        if (bookTitle.isBlank() || author.isBlank() || category.isBlank()) {
            displayAlertMessage("Kindly fill all the given input fields.");
            return;
        }

        Book book = new Book();
        book.setTitle(bookTitle);
        book.setAuthor(author);
        book.setCategory(category);
        book.setAvailability(AvailabilityStatus.AVAILABLE);
        book.setCondition(bookCondition);

        stagedBooks.add(book);
        clearAllFields();
    }

    @FXML
    private void submitHandler() {
        if (stagedBooks.isEmpty()) {
            displayAlertMessage("No books have been added for submission.");
            return;
        }
        boolean bookDetailsSubmissionStatus = bookService.insertNewBooks(stagedBooks);
        if (bookDetailsSubmissionStatus) {
            displayAlertMessage("Book details have been submitted successfully to the library.");
            stagedBooks.clear();
        } else {
            displayAlertMessage("Addition of new books failed!");
        }
    }

    private void clearAllFields() {
        bookTitleField.clear();
        authorField.clear();
        bookCategoryField.clear();
        bookConditionChoiceBox.setValue(BookCondition.ACTIVE);
    }

    private void displayAlertMessage(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
}
