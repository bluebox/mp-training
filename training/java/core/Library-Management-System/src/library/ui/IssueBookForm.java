package library.ui;

import java.io.IOException;
import java.time.LocalDateTime;
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
import library.model.Member;
import library.model.enums.BookAvailability;
import library.model.enums.BookStatus;
import library.service.BookServiceImpl;
import library.service.IssueServiceImpl;
import library.service.MemberServiceImpl;
import library.service.interfaces.BookService;
import library.service.interfaces.IssueService;
import library.service.interfaces.MemberService; 

public class IssueBookForm {

	@FXML
	private TextField bookIdField;
	@FXML
	private TextField memberIdField;
	@FXML
	private TextArea displayArea;
	@FXML
	private Label messageLabel;
	@FXML
	private Label displayListLabel;

 
	private BookService bookService; 
	private IssueService issueService;
	private MemberService memberService;
	private final String CURRENT_USER = "ADMIN";

	public IssueBookForm() {
		this.bookService = new BookServiceImpl(); 
		this.issueService = new IssueServiceImpl();
		this.memberService = new MemberServiceImpl();
	}

	@FXML
	private void initialize() {
		displayListLabel.setText("Available Items List");
	}

	@FXML
	private void handleShowBooks() {
		displayListLabel.setText("Available Books");
		displayArea.clear();
		try {
			List<Book> books = bookService.getAllBooks();
			if (books.isEmpty()) {
				displayArea.setText("No books in the library.");
			} else {
				StringBuilder sb = new StringBuilder("Books (ID - Title - Availability - Status):\n");
				for (Book book : books) {
					sb.append(book.getBookId()).append(" - ").append(book.getTitle()).append(" (Avail: ")
							.append(book.getAvailability().toString()).append(", Status: ")
							.append(book.getStatus().toString()).append(")\n");
				}
				displayArea.setText(sb.toString());
			}
		} catch (LibraryException e) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("Database error loading books: " + e.getMessage());
		} catch (Exception e) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("An unexpected error occurred loading books: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void handleShowMembers() {
		displayListLabel.setText("Available Members");
		displayArea.clear();
		try {
			List<Member> members = memberService.getAllMembers();
			if (members.isEmpty()) {
				displayArea.setText("No members registered.");
			} else {
				StringBuilder sb = new StringBuilder("Registered Members (ID - Name):\n");
				for (Member member : members) {
					sb.append(member.getMemberID()).append(" - ").append(member.getName()).append("\n");
				}
				displayArea.setText(sb.toString());
			}
		} catch (Exception e) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("An unexpected error occurred loading members: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void handleIssueBook(ActionEvent event) {
		messageLabel.setText("");
		messageLabel.setTextFill(javafx.scene.paint.Color.RED);

		String bookIdText = bookIdField.getText();
		String memberIdText = memberIdField.getText();

		if (bookIdText.isEmpty() || memberIdText.isEmpty()) {
			messageLabel.setText("Please enter both Book ID and Member ID.");
			return;
		}

		int bookId;
		int memberId;

		try {
			bookId = Integer.parseInt(bookIdText);
			memberId = Integer.parseInt(memberIdText);
		} catch (NumberFormatException e) {
			messageLabel.setText("Invalid ID format. Please enter numeric IDs.");
			return;
		}

		try {
			Book bookToIssue = bookService.getBookById(bookId);
			if (bookToIssue == null) {
				messageLabel.setText("Error: Book with ID " + bookId + " not found.");
				return;
			}
			if (bookToIssue.getAvailability() == BookAvailability.ISSUED) {
				messageLabel.setText("Error: Book '" + bookToIssue.getTitle() + "' is already issued.");
				return;
			}
			if (bookToIssue.getStatus() == BookStatus.INACTIVE) {
				messageLabel.setText("Error: Book '" + bookToIssue.getTitle() + "' is inactive and cannot be issued.");
				return;
			}

			String memberName = "Unknown Member";
			try {
				Member member = memberService.getMemberById(memberId);
				if (member == null) {
					messageLabel.setText("Error: Member with ID " + memberId + " not found.");
					return;
				}
				memberName = member.getName();
			} catch (Exception e) {
				System.err.println("Unexpected error getting member details: " + e.getMessage());
			}

			issueService.issueBook(bookId, memberId, LocalDateTime.now(), CURRENT_USER);
			messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
			messageLabel.setText("Book '" + bookToIssue.getTitle() + "' issued to " + memberName + " successfully!");
			bookIdField.clear();
			memberIdField.clear();
			handleShowBooks();
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