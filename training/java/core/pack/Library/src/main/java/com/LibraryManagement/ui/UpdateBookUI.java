package Library.src.main.java.com.LibraryManagement.ui;

import Library.src.main.java.com.LibraryManagement.model.Book;
import Library.src.main.java.com.LibraryManagement.service.BookService;
import Library.src.main.java.com.LibraryManagement.service.BookServiceImpl;
import Library.src.main.java.com.LibraryManagement.dao.BookDAOImpl;
import Library.src.main.java.com.LibraryManagement.util.DBConnection;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TextFormatter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.UnaryOperator;

public class UpdateBookUI {
    private final BookService bookService;

    public UpdateBookUI() {
        try {
            Connection conn = DBConnection.getConnection();
            this.bookService = new BookServiceImpl(new BookDAOImpl(conn));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize BookService: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during DB initialization: " + e.getMessage());
        }
    }

    public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(8);
        pane.setHgap(10);

        TextField idField = new TextField();
        idField.setPromptText("e.g., 1001");

        TextField titleField = new TextField();
        titleField.setPromptText("e.g., Java Basics");

        TextField authorField = new TextField();
        authorField.setPromptText("e.g., John Smith");

        TextField categoryField = new TextField();
        categoryField.setPromptText("e.g., Programming");

        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("A", "I");
        statusBox.setValue("A");

        Button updateButton = new Button("Update");
        Label messageLabel = new Label();

        // === INPUT RESTRICTIONS ===

        // Only digits for Book ID
        UnaryOperator<TextFormatter.Change> digitsFilter = change -> {
            String newText = change.getControlNewText();
            return newText.matches("\\d*") ? change : null;
        };
        idField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, digitsFilter));
        idField.setText(""); // clear initial "0"

        // Only letters and spaces for text fields
        UnaryOperator<TextFormatter.Change> lettersFilter = change -> {
            String newText = change.getControlNewText();
            return newText.matches("[a-zA-Z ]*") ? change : null;
        };
        titleField.setTextFormatter(new TextFormatter<>(lettersFilter));
        authorField.setTextFormatter(new TextFormatter<>(lettersFilter));
        categoryField.setTextFormatter(new TextFormatter<>(lettersFilter));

        // === UI LAYOUT ===
        pane.add(new Label("Book ID:"), 0, 0);
        pane.add(idField, 1, 0);
        pane.add(new Label("Title:"), 0, 1);
        pane.add(titleField, 1, 1);
        pane.add(new Label("Author:"), 0, 2);
        pane.add(authorField, 1, 2);
        pane.add(new Label("Category:"), 0, 3);
        pane.add(categoryField, 1, 3);
        pane.add(new Label("Status (A/I):"), 0, 4);
        pane.add(statusBox, 1, 4);
        pane.add(updateButton, 1, 5);
        pane.add(messageLabel, 1, 6);

        // === BUTTON ACTION ===
        updateButton.setOnAction(e -> {
            String idText = idField.getText().trim();
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String category = categoryField.getText().trim();
            String status = statusBox.getValue();

            if (idText.isEmpty()) {
                messageLabel.setText("Book ID is required to update.");
                return;
            }

            try {
                int bookId = Integer.parseInt(idText);

                // Fetch the existing book from DB
                Book existingBook = bookService.getBookById(bookId); // Ensure this method exists in BookService

                if (existingBook == null) {
                    messageLabel.setText("No book found with ID: " + bookId);
                    return;
                }

                // Replace only non-empty fields
                if (!title.isEmpty()) existingBook.setTitle(title);
                if (!author.isEmpty()) existingBook.setAuthor(author);
                if (!category.isEmpty()) existingBook.setCategory(category);
                if (status != null && !status.isEmpty()) existingBook.setStatus(status.charAt(0));

                // Call update
                bookService.updateBook(existingBook);

                messageLabel.setText("Book updated successfully.");

                // Optional: Clear fields
                // idField.clear();  // keep ID if you want to allow further updates
                titleField.clear();
                authorField.clear();
                categoryField.clear();
                statusBox.setValue("A");

            } catch (SQLException ex) {
                messageLabel.setText("Database error: " + ex.getMessage());
            } catch (Exception ex) {
                messageLabel.setText("Unexpected error: " + ex.getMessage());
            }
        });


        stage.setScene(new Scene(pane, 400, 320));
        stage.setTitle("update Book");
        stage.show();
    }
}


