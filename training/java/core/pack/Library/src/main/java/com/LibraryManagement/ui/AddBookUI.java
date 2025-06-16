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

import java.sql.Connection;
import java.sql.SQLException;

public class AddBookUI {
    private final BookService bookService;

    public AddBookUI() {
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
        TextField authorField = new TextField();
        TextField categoryField = new TextField();
        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("A", "I");
        statusBox.setValue("A");

        Button addButton = new Button("Add");
        Label messageLabel = new Label();

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
        pane.add(addButton, 1, 5);
        pane.add(messageLabel, 1, 6);

        addButton.setOnAction(e -> {
            String idText = idField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            String status = statusBox.getValue();

            if (idText.isEmpty() || title.isEmpty() || author.isEmpty() || category.isEmpty() || status == null) {
                messageLabel.setText("Please fill in all fields.");
                return;
            }

            try {
                int bookId = Integer.parseInt(idText);
                Book book = new Book();
                book.setBookId(bookId);
                book.setTitle(title);
                book.setAuthor(author);
                book.setCategory(category);
                book.setStatus(status.charAt(0));
                book.setAvailability('A');

                bookService.addBook(book);
                
                messageLabel.setText("Book added successfully.");
                idField.clear();
                titleField.clear();
                authorField.clear();
                categoryField.clear();
                statusBox.setValue("A");
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid Book ID. It must be a number.");
            } catch (SQLException ex) {
                messageLabel.setText("Database error: " + ex.getMessage());
            } catch (RuntimeException ex) {
                messageLabel.setText("Unexpected error: " + ex.getMessage());
            } catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        stage.setScene(new Scene(pane, 400, 300));
        stage.setTitle("Add Book");
        stage.show();
    }
}
