package Library.src.main.java.com.LibraryManagement.ui;

import Library.src.main.java.com.LibraryManagement.service.IssueRecordService;
import Library.src.main.java.com.LibraryManagement.service.IssueRecordServiceImpl;
import Library.src.main.java.com.LibraryManagement.dao.IssueRecordDAOImpl;
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
import java.util.function.UnaryOperator;

public class IssueBookUI {
    private final IssueRecordService issueService;

    public IssueBookUI() {
        try {
            Connection conn = DBConnection.getConnection();
            this.issueService = new IssueRecordServiceImpl(new IssueRecordDAOImpl(conn), new BookDAOImpl(conn));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize service: " + e.getMessage());
        }
    }

    public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(8);
        pane.setHgap(10);

        TextField bookIdField = new TextField();
        TextField memberIdField = new TextField();
        Button issueButton = new Button("Issue Book");
        Label messageLabel = new Label();

        // === Input validation filters ===
        UnaryOperator<TextFormatter.Change> digitsFilter = change -> 
            change.getControlNewText().matches("\\d*") ? change : null;

        bookIdField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, digitsFilter));
        bookIdField.setText("");
        memberIdField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, digitsFilter));
        memberIdField.setText("");

        // === Layout ===
        pane.add(new Label("Book ID:"), 0, 0);
        pane.add(bookIdField, 1, 0);
        pane.add(new Label("Member ID:"), 0, 1);
        pane.add(memberIdField, 1, 1);
        pane.add(issueButton, 1, 2);
        pane.add(messageLabel, 1, 3);

        // === Button action ===
        issueButton.setOnAction(e -> {
            String bookIdText = bookIdField.getText().trim();
            String memberIdText = memberIdField.getText().trim();

            if (bookIdText.isEmpty() || memberIdText.isEmpty()) {
                messageLabel.setText("Please fill in both Book ID and Member ID.");
                return;
            }

            try {
                int bookId = Integer.parseInt(bookIdText);
                int memberId = Integer.parseInt(memberIdText);

                issueService.issueBook(bookId, memberId);

                messageLabel.setText(" Book issued successfully.");
                bookIdField.clear();
                memberIdField.clear();
            } catch (NumberFormatException ex) {
                messageLabel.setText(" Both fields must be valid numbers.");
            } catch (Exception ex) {
                messageLabel.setText(" Error: " + ex.getMessage());
            }
        });

        stage.setScene(new Scene(pane, 400, 200));
        stage.setTitle("Issue Book");
        stage.show();
    }
}


