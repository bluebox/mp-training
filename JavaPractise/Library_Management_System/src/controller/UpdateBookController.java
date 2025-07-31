package controller;

import dao.BookDao;
import domain.Book;
import domain.Status;
import domain.Category;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.BookService;
import service.BookServiceInterface;

public class UpdateBookController {

    @FXML private TextField idField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private ComboBox<Category> categoryCombo;
    @FXML private ComboBox<Status> statusCombo;

    private final BookServiceInterface bookService = new BookService(new BookDao());
    private int bookId;

    @FXML
    public void initialize() {
        statusCombo.setItems(FXCollections.observableArrayList(Status.values()));
        categoryCombo.setItems(FXCollections.observableArrayList(Category.values()));
    }

    public void setBookData(Book book) {
        this.bookId = book.getBookId();
        idField.setText(String.valueOf(book.getBookId()));
        idField.setEditable(false);
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        categoryCombo.setValue(book.getCategory());
        statusCombo.setValue(book.getStatus());
    }

    @FXML
    private void handleUpdateBook() {
        try {
            if (bookId == 0 && !idField.getText().trim().isEmpty()) {
                bookId = Integer.parseInt(idField.getText().trim());
            }
            Book updatedBook = new Book();
            updatedBook.setBookId(bookId);
            if (!bookService.bookExists(updatedBook)) {
                showAlert("Error", "No book found with ID: " + bookId);
                handleBack();
                return;
            }
            updatedBook.setTitle(titleField.getText().trim().isEmpty() ? null : titleField.getText().trim());
            updatedBook.setAuthor(authorField.getText().trim().isEmpty() ? null : authorField.getText().trim());
            updatedBook.setCategory(categoryCombo.getValue());
            updatedBook.setStatus(statusCombo.getValue());

            bookService.updateBook(updatedBook);

            showAlert("Success", "Book updated successfully.");
            clearFields();
            handleBack();
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
        }
    }

    @FXML
    private void handleBack() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/ViewBooks.fxml"));
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void clearFields() {
        titleField.clear();
        authorField.clear();
        categoryCombo.getSelectionModel().clearSelection();
        statusCombo.getSelectionModel().clearSelection();
    }
}
