package Controller;

import casestudy.Book;
import casestudy.LibraryException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

import Service.BookService;

public class ViewBooksController {
    @FXML private TableView<Book> booksTable;
    @FXML private TableColumn<Book, Integer> bookIdColumn;
    @FXML private TableColumn<Book, String> titleColumn, authorColumn, categoryColumn;
    @FXML private TableColumn<Book, Character> statusColumn, availabilityColumn;
    @FXML private TextField bookIdField, titleField, authorField, categoryField;
    @FXML private ComboBox<String> statusCombo;
    private BookService bookService = new BookService();
    private Stage primaryStage;

    public ViewBooksController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        try {
            bookIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getBookId()).asObject());
            titleColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitle()));
            authorColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAuthor()));
            categoryColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategory()));
            statusColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getStatus()));
            availabilityColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getAvailability()));
            loadBooks();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Initialization Error", 
                "Failed to initialize the view: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdateBook() {
        try {
            // Validate required fields
            if (bookIdField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Book ID is required");
            }
            if (titleField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Title is required");
            }
            if (authorField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Author is required");
            }
            if (categoryField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Category is required");
            }
            if (statusCombo.getValue() == null) {
                throw new IllegalArgumentException("Status is required");
            }

            Book book = new Book(
                titleField.getText().trim(),
                authorField.getText().trim(),
                categoryField.getText().trim(),
                statusCombo.getValue().charAt(statusCombo.getValue().length() - 2),
                'A'
            );
            book.setBookId(Integer.parseInt(bookIdField.getText().trim()));
            bookService.updateBook(book);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully");
            loadBooks();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", 
                "Book ID must be a valid number. Please check your input.");
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", e.getMessage());
        } catch (LibraryException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", 
                "Failed to update book: " + e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Unexpected Error", 
                "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/MainView.fxml"));
            Scene scene = new Scene(loader.load());
            MainController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", 
                "Failed to return to main view: " + e.getMessage());
        }
    }

    @FXML
    private void handleClear() {
        clearFields();
    }

    private void loadBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            booksTable.setItems(FXCollections.observableArrayList(books));
        } catch (LibraryException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", 
                "Failed to load books: " + e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Unexpected Error", 
                "An unexpected error occurred while loading books: " + e.getMessage());
        }
    }

    private void clearFields() {
        bookIdField.clear();
        titleField.clear();
        authorField.clear();
        categoryField.clear();
        statusCombo.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        try {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        } catch (Exception e) {
            // Fallback error handling if alert creation fails
            System.err.println("Error showing alert: " + e.getMessage());
            System.err.println("Alert details - Type: " + type + ", Title: " + title + ", Message: " + message);
        }
    }
}