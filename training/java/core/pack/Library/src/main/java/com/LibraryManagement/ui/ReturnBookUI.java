package Library.src.main.java.com.LibraryManagement.ui;

import Library.src.main.java.com.LibraryManagement.service.IssueRecordService;
import Library.src.main.java.com.LibraryManagement.service.IssueRecordServiceImpl;
//import Library.src.main.java.com.LibraryManagement.dao.MemberDAOImpl;
import Library.src.main.java.com.LibraryManagement.dao.*;
import Library.src.main.java.com.LibraryManagement.util.DBConnection;

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

public class ReturnBookUI {
    private final IssueRecordService issueService;

    public ReturnBookUI() {
        try {
        	 Connection conn = DBConnection.getConnection();
             this.issueService = new IssueRecordServiceImpl(new IssueRecordDAOImpl(conn), new BookDAOImpl(conn));
//        	Connection conn = DBConnection.getConnection();
//
//        	// Initialize DAOs
//        	IssueRecordDAO issueRecordDAO = new IssueRecordDAOImpl(conn);
//        	BookDAO bookDAO = new BookDAOImpl(conn);
//        	MemberDAO memberDAO = new MemberDAOImpl(conn);
//
//        	// Inject DAOs into the service
//        	this.issueService = new IssueRecordServiceImpl(issueRecordDAO, bookDAO, memberDAO);
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
        Label messageLabel = new Label();
        Button returnButton = new Button("Return Book");

        // Allow only digits in both fields
        Pattern numericPattern = Pattern.compile("\\d*");
        UnaryOperator<TextFormatter.Change> numericFilter = change -> {
            return numericPattern.matcher(change.getControlNewText()).matches() ? change : null;
        };

        bookIdField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(),null, numericFilter));
        memberIdField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, numericFilter));

        pane.add(new Label("Book ID:"), 0, 0);
        pane.add(bookIdField, 1, 0);
        pane.add(new Label("Member ID:"), 0, 1);
        pane.add(memberIdField, 1, 1);
        pane.add(returnButton, 1, 2);
        pane.add(messageLabel, 1, 3);

        returnButton.setOnAction(e -> {
            String bookIdText = bookIdField.getText().trim();
            String memberIdText = memberIdField.getText().trim();

            if (bookIdText.isEmpty() || memberIdText.isEmpty()) {
                messageLabel.setText("Please enter both Book ID and Member ID.");
                return;
            }

            try {
                int bookId = Integer.parseInt(bookIdText);
                int memberId = Integer.parseInt(memberIdText);

                issueService.returnBook(bookId, memberId); // Perform return
                messageLabel.setText("✅ Book returned successfully.");
                bookIdField.clear();
                memberIdField.clear();
            } catch (IllegalArgumentException | IllegalStateException ex) {
                messageLabel.setText( ex.getMessage());
            } catch (SQLException ex) {
                messageLabel.setText("DB Error: " + ex.getMessage());
            } catch (Exception ex) {
                messageLabel.setText("Unexpected Error: " + ex.getMessage());
            }
        });

        stage.setScene(new Scene(pane, 400, 200));
        stage.setTitle("Return Book");
        stage.show();
    }
}



