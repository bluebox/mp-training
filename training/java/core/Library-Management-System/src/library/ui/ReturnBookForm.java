package library.ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.exception.LibraryException;
import library.model.Book;
import library.model.IssueRecord;
import library.model.Member;
import library.model.enums.IssueStatus;

import library.service.BookServiceImpl;
import library.service.IssueServiceImpl;
import library.service.MemberServiceImpl;
import library.service.interfaces.BookService;
import library.service.interfaces.IssueService;
import library.service.interfaces.MemberService;

public class ReturnBookForm {

	@FXML
	private TextField bookIdField;
	@FXML
	private TextArea displayArea;
	@FXML
	private Label messageLabel;


	private IssueService issueService; 
	private BookService bookService; 
	private MemberService memberService; 
	private final String CURRENT_USER = "ADMIN";

	public ReturnBookForm() {
		this.issueService = new IssueServiceImpl(); 
		this.bookService = new BookServiceImpl(); 
		this.memberService = new MemberServiceImpl(); 
	}

	@FXML
	private void initialize() {
		handleShowIssuedBooks();
	}

	@FXML
	private void handleShowIssuedBooks() {
		displayArea.clear();
		try {
			List<IssueRecord> issuedRecords = issueService.getAllIssuedRecords();
			if (issuedRecords.isEmpty()) {
				displayArea.setText("No books currently issued.");
			} else {
				StringBuilder sb = new StringBuilder("Issued Books (Book ID - Title - Issued To - Issue Date/Time):\n");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

				for (IssueRecord record : issuedRecords) {
					if (record.getStatus() == IssueStatus.ISSUED && record.getReturnDate() == null) { 
						String bookTitle = "Unknown Book";
						String memberName = "Unknown Member";

						try {
							Book book = bookService.getBookById(record.getBookId());
							if (book != null) {
								bookTitle = book.getTitle();
							}
						} catch (LibraryException e) {
							System.err.println("DB error getting book title for record " + record.getIssueId() + ": "
									+ e.getMessage());
						} catch (Exception e) {
							System.err.println("Unexpected error getting book title for record " + record.getIssueId()
									+ ": " + e.getMessage());
						}

						try {
							Member member = memberService.getMemberById(record.getMemberId());
							if (member != null) {
								memberName = member.getName();
							}
						} catch (Exception e) {
							System.err.println("Unexpected error getting member name for record " + record.getIssueId()
									+ ": " + e.getMessage());
						}

						sb.append(record.getBookId()).append(" - ").append(bookTitle).append(" (Issued to: ")
								.append(memberName).append(" on ").append(record.getIssueDate().format(formatter))
								.append(")\n");
					}
				}
				if (sb.toString().equals("Issued Books (Book ID - Title - Issued To - Issue Date/Time):\n")) {
					displayArea.setText("No books currently marked as 'Issued'.");
				} else {
					displayArea.setText(sb.toString());
				}
			}
		} catch (LibraryException e) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("Database error loading issued records: " + e.getMessage());
		} catch (Exception e) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("An unexpected error occurred loading issued records: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void handleReturnBook(ActionEvent event) {
		messageLabel.setText("");
		messageLabel.setTextFill(javafx.scene.paint.Color.RED);

		String bookIdText = bookIdField.getText();

		if (bookIdText.isEmpty()) {
			messageLabel.setText("Please enter the Book ID to return.");
			return;
		}

		int bookId;
		try {
			bookId = Integer.parseInt(bookIdText);
		} catch (NumberFormatException e) {
			messageLabel.setText("Invalid Book ID format. Please enter a numeric ID.");
			return;
		}

		try {
			issueService.returnBook(bookId, CURRENT_USER);
			messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
			messageLabel.setText("Book ID " + bookId + " returned successfully!");
			bookIdField.clear();
			handleShowIssuedBooks();
		} catch (LibraryException e) {
			messageLabel.setText(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			messageLabel.setText("An unexpected error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void handleBackToMainMenu(ActionEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root, 900,600);
			stage.setScene(scene);
			stage.setTitle("Library Management System - Main Menu");
			stage.show();
		} catch (IOException e) {
			messageLabel.setText("Error navigating back to main menu: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			messageLabel.setText("An unexpected error occurred during navigation: " + e.getMessage());
			e.printStackTrace();
		}
	}
}