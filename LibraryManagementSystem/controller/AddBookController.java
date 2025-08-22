package com.library.controller;

import java.io.IOException;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

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
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String category = categoryField.getText().trim();

        if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
            showTemporaryMessage("All fields are required.", "red");
            return;
        }

        try {
            Book book = new Book(0, title, author, category, 'A', 'A');

            if (bsi.doesBookExist(title, author, category)) {
                showTemporaryMessage("Book already exists.", "red");
                return;
            }

            bsi.addBook(book);
            showTemporaryMessage("Book added successfully.", "green");
            clearForm();

        } catch (Exception e) {
            e.printStackTrace();
            showTemporaryMessage("Failed to add book: " + e.getMessage(), "red");
        }
    }

    private void clearForm() {
        titleField.clear();
        authorField.clear();
        categoryField.clear();
    }

    private void showTemporaryMessage(String message, String color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + color + ";");

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> statusLabel.setText(""));
        pause.play();
    }
}
