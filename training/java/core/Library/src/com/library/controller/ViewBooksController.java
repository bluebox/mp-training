package com.library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.library.dao.Book;
import com.library.service.BookService;

public class ViewBooksController {

    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, Integer> idCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> categoryCol;
    @FXML private TableColumn<Book, String> statusCol;
    @FXML private TableColumn<Book, String> availabilityCol;

    private final BookService bookService = new BookService();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());
        titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        authorCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        categoryCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus().toString()));
        availabilityCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getAvailability().toString()));

        loadBooks();
    }

    private void loadBooks() {
        try {
            ObservableList<Book> books = FXCollections.observableArrayList(bookService.getAllBooks());
            bookTable.setItems(books);
        } catch (Exception e) {
            showAlert("Failed to load books: " + e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("View Books");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
