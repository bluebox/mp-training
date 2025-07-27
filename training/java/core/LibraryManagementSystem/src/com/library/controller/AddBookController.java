package com.library.controller;

import java.io.IOException;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddBookController {

    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;
    @FXML private Label statusLabel;

    private final BookServiceImplementation bsi = new BookServiceImplementation();

    @FXML
    public void initialize() {
        Platform.runLater(() -> titleField.getParent().requestFocus());
    }
    @FXML
    private void handleBack() throws IOException {
        MainController.switchScene("MainDashboard.fxml");
    }


    @FXML
    private void handleSubmit() {
        statusLabel.setStyle("-fx-text-fill: red;");

        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String category = categoryField.getText().trim();

        System.out.println("title: " + title + " author: " + author + " category: " + category);

        if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
            statusLabel.setText("All fields are required.");
            return;
        }

        try {
            Book book = new Book(0, title, author, category, 'A', 'A');
            bsi.addBook(book);

            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setText("Book added successfully.");
            clearForm();

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Failed to add book: " + e.getMessage());
        }
    }

    private void clearForm() {
        titleField.clear();
        authorField.clear();
        categoryField.clear();
    }
}
