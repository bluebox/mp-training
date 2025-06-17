package com.medplus.controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import com.medplus.model.Book;
import com.medplus.service.LibraryService;

public class BookListViewController {
    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, String> categoryCol;

    private final LibraryService service = new LibraryService();

    @FXML
    public void initialize() {
        try {
            ObservableList<Book> list = FXCollections.observableArrayList(service.getAllBooks());
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
            categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
            bookTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
