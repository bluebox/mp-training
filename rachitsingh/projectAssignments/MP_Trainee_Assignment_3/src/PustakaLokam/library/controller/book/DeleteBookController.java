package PustakaLokam.library.controller.book;

import PustakaLokam.library.model.Book;
import PustakaLokam.library.service.BookService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DeleteBookController {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> categoryColumn;
    @FXML
    private Button deleteButton;

    private final BookService bookService = new BookService();
    private ObservableList<Book> bookList;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(
                data -> new SimpleObjectProperty<>(data.getValue().getBookID()));
        titleColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getTitle()));
        authorColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getAuthor()));
        categoryColumn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getCategory()));

        refreshTable();
    }

    private void refreshTable() {
        bookList = FXCollections.observableArrayList(bookService.fetchAllBooks());
        bookTable.setItems(bookList);
    }

    @FXML
    private void handleDelete() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            showAlert(Alert.AlertType.WARNING, "No book selected. Please select a book to be deleted.");
            return;
        }

        boolean deleted = bookService.deleteBook(selectedBook.getBookID());

        if (deleted) {
            bookList.remove(selectedBook); // UI auto updates
            showAlert(Alert.AlertType.INFORMATION, "The Book has been deleted successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Failed to delete the book.");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
