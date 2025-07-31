package controller;

import dao.BookDao;
import domain.AvailabilityStatus;
import domain.Book;
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

public class UpdateBookAvailabilityController {

    @FXML private TextField idField;
    @FXML private ComboBox<AvailabilityStatus> availabilityCombo;

    private final BookServiceInterface bookService = new BookService(new BookDao());
    Book book = new Book();
    private int bookId;

    @FXML
    public void initialize() {
        availabilityCombo.setItems(FXCollections.observableArrayList(AvailabilityStatus.values()));
    }

    public void setBookData(Book book) {
        this.bookId = book.getBookId();
        idField.setText(String.valueOf(book.getBookId()));
        idField.setEditable(false);
        availabilityCombo.setValue(book.getAvailability());
    }

    @FXML
    private void handleUpdateBook() {
        try {
            if (bookId == 0 && !idField.getText().trim().isEmpty()) {
                bookId = Integer.parseInt(idField.getText().trim());
            }
            book.setBookId(bookId);
            if (!bookService.bookExists(book)) {
                showAlert("Error", "No book found with ID: " + bookId);
                handleBack();
                return;
            }

            AvailabilityStatus availability = availabilityCombo.getValue();
            if (availability == null) {
                showAlert("Error", "Please select availability.");
                return;
            }
            Book book=new Book();
            book.setBookId(bookId);
            book.setAvailability(availability);
            bookService.updateBookAvailability(book);

            showAlert("Success", "Book availability updated successfully.");
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
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void clearFields() {
        idField.clear();
        availabilityCombo.getSelectionModel().clearSelection();
    }
}
