package Library.src.main.java.com.LibraryManagement.ui;

import Library.src.main.java.com.LibraryManagement.service.BookService;
import Library.src.main.java.com.LibraryManagement.service.BookServiceImpl;
import Library.src.main.java.com.LibraryManagement.dao.BookDAOImpl;
import Library.src.main.java.com.LibraryManagement.util.DBConnection;
import Library.src.main.java.com.LibraryManagement.model.Book;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class UpdateBookAvailUI {
    private final BookService bookService;

    public UpdateBookAvailUI() {
        try {
            Connection conn = DBConnection.getConnection();
            this.bookService = new BookServiceImpl(new BookDAOImpl(conn));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize BookService: " + e.getMessage());
        }
    }

    public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(8);
        pane.setHgap(10);

        TextField bookIdField = new TextField();
        ComboBox<String> availabilityBox = new ComboBox<>();
        availabilityBox.getItems().addAll("A", "I");
        availabilityBox.setValue("A");

        Label messageLabel = new Label();
        Button updateButton = new Button("Update Availability");

        // Only allow digits for book ID
        Pattern numericPattern = Pattern.compile("\\d*");
        UnaryOperator<TextFormatter.Change> numericFilter = change ->
                numericPattern.matcher(change.getControlNewText()).matches() ? change : null;
        bookIdField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, numericFilter));

        pane.add(new Label("Book ID:"), 0, 0);
        pane.add(bookIdField, 1, 0);
        pane.add(new Label("Availability (A/I):"), 0, 1);
        pane.add(availabilityBox, 1, 1);
        pane.add(updateButton, 1, 2);
        pane.add(messageLabel, 1, 3);

        updateButton.setOnAction(e -> {
            String bookIdText = bookIdField.getText().trim();
            String availability = availabilityBox.getValue();

            if (bookIdText.isEmpty() || availability == null) {
                messageLabel.setText("Please enter Book ID and choose Availability.");
                return;
            }

            try {
                int bookId = Integer.parseInt(bookIdText);
                
                Book existingBook=bookService.getBookById(bookId);
                if(existingBook==null) {
                	messageLabel.setText("No Book found with ID: " + bookId);
                    return;
                }
                
                bookService.updateBookAvailability(bookId, availability.charAt(0));
                messageLabel.setText(" Availability updated to '" + availability + "' for Book ID " + bookId);
                bookIdField.clear();
                availabilityBox.setValue("A");
            } catch (SQLException ex) {
                messageLabel.setText("DB Error: " + ex.getMessage());
            } catch (Exception ex) {
                messageLabel.setText("Unexpected Error: " + ex.getMessage());
            }
        });

        stage.setScene(new Scene(pane, 400, 200));
        stage.setTitle("Update Book Availability");
        stage.show();
    }
}
