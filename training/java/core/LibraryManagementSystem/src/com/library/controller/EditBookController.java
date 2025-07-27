package com.library.controller;


import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditBookController {

    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;
    @FXML private ChoiceBox<String> statusChoice;

    private Book book;
    private final BookServiceImplementation bookService = new BookServiceImplementation();

    public void setBook(Book book) {
        this.book = book;
        
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        categoryField.setText(book.getCategory());
        statusChoice.setValue(String.valueOf(book.getStatus()));
    }

    @FXML
    private void handleUpdate() {
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setCategory(categoryField.getText());
        book.setStatus(statusChoice.getValue().charAt(0));
System.out.println("hadling updation of book");
//        boolean success = bookService.updateBook(book);
//        if (success) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book updated successfully!");
//            alert.showAndWait();
//            closeWindow();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed.");
//            alert.showAndWait();
//        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
