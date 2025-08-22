package com.library.controller;

import java.io.IOException;

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
    private static Book selectedBook;

    public static void setSelectedBook(Book book) {
        selectedBook = book;
    }


    private Book currentBook;
    private final BookServiceImplementation bookService = new BookServiceImplementation();

    @FXML
    public void initialize() {
        if (selectedBook != null) {
            setBook(selectedBook);
        }
    }

    public void setBook(Book book) {
        this.currentBook = book;

        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        categoryField.setText(book.getCategory());

        statusChoice.getItems().clear();
        statusChoice.getItems().addAll("Active", "Inactive");

        // Map current status char to human-readable text
        if (book.getStatus() == 'A') {
            statusChoice.setValue("Active");
        } else {
            statusChoice.setValue("Inactive");
        }
    }

    @FXML
    public void handleUpdate() {
        String newTitle = titleField.getText().trim();
        String newAuthor = authorField.getText().trim();
        String newCategory = categoryField.getText().trim();
        String selectedStatus = statusChoice.getValue();

        if (newTitle.isEmpty() || newAuthor.isEmpty() || newCategory.isEmpty()) {
//            statusLabel.setText("Fields cannot be empty.");
        	showTemporaryMessage("Fields cannot be empty.", "red");
            return;
        }

        if (selectedStatus == null) {
//            statusLabel.setText("Please select a status.");
        	showTemporaryMessage("Please select a status.", "red");
            return;
        }

        // Convert status string to corresponding character
        char newStatus = selectedStatus.equals("Active") ? 'A' : 'I';

        boolean isChanged =
            !newTitle.equals(currentBook.getTitle()) ||
            !newAuthor.equals(currentBook.getAuthor()) ||
            !newCategory.equals(currentBook.getCategory()) ||
            newStatus != currentBook.getStatus();

        if (!isChanged) {
//            statusLabel.setText("No changes made.");
        	showTemporaryMessage("No changes made.", "red");
            return;
        }

        try {
            currentBook.setTitle(newTitle);
            currentBook.setAuthor(newAuthor);
            currentBook.setCategory(newCategory);
            currentBook.setStatus(newStatus);

            bookService.updateBookDetails(currentBook);
            statusLabel.setStyle("-fx-text-fill:green;");
            statusLabel.setText("Book updated successfully!");

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
//                Stage stage = (Stage) titleField.getScene().getWindow();
//                stage.close();
            	try {
					MainController.switchScene("ViewBooks.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            pause.play();

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void handleCancel() throws IOException {
//        ((Stage) titleField.getScene().getWindow()).close();
    	MainController.switchScene("ViewBooks.fxml");
    }
    @FXML
    private void handleBack() throws IOException {
        MainController.switchScene("ViewBooks.fxml");
    }
    @FXML
    private void handleBackDash() throws IOException {
        MainController.switchScene("MainDashboard.fxml");
    }
    private void showTemporaryMessage(String message, String color) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: " + color + ";");

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> statusLabel.setText(""));
        pause.play();
    }

}
