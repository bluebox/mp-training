package com.library.controller;

import com.Models.Book;
import com.Service.BookService;
//import com.library.util.AlertMsg;
 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
 
import java.util.List;
 
public class ViewBooksController {
 
    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, Integer> colId;
    @FXML private TableColumn<Book, String> colTitle;
    @FXML private TableColumn<Book, String> colAuthor;
    @FXML private TableColumn<Book, String> colCategory;
    @FXML private TableColumn<Book, String> colStatus;
    @FXML private TableColumn<Book, String> colAvailability;
 
    private final BookService bookService = new BookService();
 
    @FXML
    public void initialize() {
        try {
            List<Book> books = bookService.getAllBooks();
            ObservableList<Book> bookList = FXCollections.observableArrayList(books);
 
            colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getBookId()).asObject());
            colTitle.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
            colAuthor.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
            colCategory.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
            colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus()));
            colAvailability.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getAvailability()));
 
            bookTable.setItems(bookList);
 
        } catch (Exception e) {
            AlertMsg.showError(e.getMessage());
        }
    }
}
