package librarySystem.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import librarySystem.Service.libraryServices;
import model.BookPojo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;


public class BookController {
    @FXML private TextField titleField, authorField, categoryField;
    @FXML private TableView<BookPojo> bookTable;
    @FXML private TableColumn<BookPojo, Integer> colId;
    @FXML private TableColumn<BookPojo, String> colTitle, colAuthor, colCategory;
    @FXML private TableColumn<BookPojo, Character> colStatus, colAvailability;

    private final libraryServices service = new libraryServices();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());
        colTitle.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        colAuthor.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        colCategory.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
        colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus()));
        colAvailability.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getAvailability()));
        refreshTable();
    }

    public void handleAddBook() {
        BookPojo book = new BookPojo();
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setCategory(categoryField.getText());
        book.setStatus('A');
        book.setAvailability('Y');
        if (service.addBook(book)) {
            refreshTable();
            clearFields();
        } else {
            showAlert("Error", "Could not add book.");
        }
    }

    private void refreshTable() {
        ObservableList<BookPojo> books = FXCollections.observableArrayList(service.viewAllBooks());
        bookTable.setItems(books);
    }

    private void clearFields() {
        titleField.clear();
        authorField.clear();
        categoryField.clear();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}