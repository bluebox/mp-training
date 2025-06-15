package com.library.controller;

import com.library.domain.Book;
import com.library.service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class ViewBooksController {

    @FXML private TableView<Book> table;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> categoryCol;
    @FXML private TableColumn<Book, String> statusCol;
    @FXML private TableColumn<Book, String> availabilityCol;

    private final BookService bookService = new BookService();

    @FXML
    public void initialize() {
        titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        authorCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        categoryCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));
        availabilityCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAvailability()));

        List<Book> books = bookService.fetchAllBooks();
        ObservableList<Book> list = FXCollections.observableArrayList(books);
        table.setItems(list);
    }
}