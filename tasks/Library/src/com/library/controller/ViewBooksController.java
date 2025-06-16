package com.library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import com.library.domain.Book;
import com.library.serviceInterface.BookServiceInterface;
import com.library.services.BookService;

public class ViewBooksController {

    @FXML private TableView<com.library.domain.Book> bookTable;
    @FXML private TableColumn<Book, Integer> idCol;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> categoryCol;
    @FXML private TableColumn<Book, Character> availabilityCol;

    private final BookServiceInterface bookService = new BookService();

    @FXML
    public void initialize() {
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

        
        try {
            List<Book> books = bookService.getAllBooks();
            ObservableList<Book> data = FXCollections.observableArrayList(books);
            bookTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
