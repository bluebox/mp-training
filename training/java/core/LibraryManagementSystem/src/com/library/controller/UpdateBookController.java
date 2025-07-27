package com.library.controller;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UpdateBookController {

    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;
    @FXML private ChoiceBox<String> statusChoice;
    @FXML private Label statusLabel;

    private Book currentBook;
    private final BookServiceImplementation bookService = new BookServiceImplementation();

    public void setBook(Book book) {
        this.currentBook = book;

        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        categoryField.setText(book.getCategory());

        statusChoice.getItems().clear();
        statusChoice.getItems().addAll("A", "I");
        statusChoice.setValue(String.valueOf(book.getStatus()));
    }

    @FXML
    public void handleUpdate() {
        String newTitle = titleField.getText().trim();
        String newAuthor = authorField.getText().trim();
        String newCategory = categoryField.getText().trim();
        String status = statusChoice.getValue();

        if (newTitle.isEmpty() || newAuthor.isEmpty() || newCategory.isEmpty()) {
            statusLabel.setText("Fields cannot be empty.");
            return;
        }

        if (status == null) {
            statusLabel.setText("Please select a status.");
            return;
        }

        char newStatus = status.charAt(0);

        boolean isChanged =
            !newTitle.equals(currentBook.getTitle()) ||
            !newAuthor.equals(currentBook.getAuthor()) ||
            !newCategory.equals(currentBook.getCategory()) ||
            newStatus != currentBook.getStatus();

        if (!isChanged) {
            statusLabel.setText("No changes made.");
            return;
        }

        try {
            currentBook.setTitle(newTitle);
            currentBook.setAuthor(newAuthor);
            currentBook.setCategory(newCategory);
            currentBook.setStatus(newStatus);

            bookService.updateBookDetails(currentBook);
            statusLabel.setText("Book updated successfully!");

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                Stage stage = (Stage) titleField.getScene().getWindow();
                stage.close();
            });
            pause.play();

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void handleCancel() {
        ((Stage) titleField.getScene().getWindow()).close();
    }
}
