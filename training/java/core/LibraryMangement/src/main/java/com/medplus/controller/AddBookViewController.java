package com.medplus.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.medplus.model.Book;
import com.medplus.service.LibraryService;

public class AddBookViewController {
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;

    private final LibraryService service = new LibraryService();

    @FXML
    public void handleAddBook() {
        Book book = new Book(0, titleField.getText(), authorField.getText(), categoryField.getText(), 'A', 'A');
        try {
            service.addBook(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
