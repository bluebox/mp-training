package com.LibraryManagement.controller;

import com.LibraryManagement.models.Book;
import com.LibraryManagement.service.implementation.BookServiceImplementation;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private ComboBox<String> category;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        category.setItems(FXCollections.observableArrayList(
                "Fiction", "Mystery", "Thriller", "Story",
                "Adventure", "Humor", "Science Fiction",
                "Narrative", "Autobiography"
        ));
    }

    @FXML
    public void AddBook(ActionEvent event) {
        String titleText = title.getText().trim();
        String authorText = author.getText().trim();
        String categoryText = category.getValue();

        if (titleText.isEmpty()) {
            showAlert("Missing Title", "Please enter the book title.");
            return;
        }

        if (authorText.isEmpty()) {
            showAlert("Missing Author", "Please enter the author's name.");
            return;
        }

        if (categoryText == null || categoryText.trim().isEmpty()) {
            showAlert("Missing Category", "Please select a category.");
            return;
        }

        BookServiceImplementation bookService = new BookServiceImplementation();
        bookService.addBook(new Book(titleText, authorText, categoryText));
        showAlertu("Status", "Successfully added Book");
        ((Stage) title.getScene().getWindow()).close();
    }

    private void showAlertu(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
