package library.ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import library.exception.LibraryException;
import library.model.Book;
import library.model.IssueRecord;
import library.model.Member;
import library.service.BookServiceImpl;
import library.service.IssueServiceImpl;
import library.service.MemberServiceImpl;
import library.service.interfaces.BookService;
import library.service.interfaces.IssueService;
import library.service.interfaces.MemberService; 

public class ReportsScreen {

	@FXML
	private TextArea reportDisplayArea;
	@FXML
	private Label messageLabel;


	private IssueService issueService;
	private BookService bookService;
	private MemberService memberService;

	public ReportsScreen() {
		this.issueService = new IssueServiceImpl();
		this.bookService = new BookServiceImpl(); 
		this.memberService = new MemberServiceImpl();
	}

	@FXML
	private void initialize() {
		handleOverdueBooksReport();
	}

	@FXML
	private void handleOverdueBooksReport() {
		reportDisplayArea.clear();
		messageLabel.setText("");
		messageLabel.setTextFill(javafx.scene.paint.Color.RED); 

		try {
			List<IssueRecord> overdueRecords = issueService.getOverdueBooks(14);

			if (overdueRecords.isEmpty()) {
				reportDisplayArea.setText("No overdue books found.");
			} else {
				StringBuilder sb = new StringBuilder("--- Overdue Books (Issued for >14 days) ---\n\n");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

				for (IssueRecord record : overdueRecords) {
					String bookTitle = "N/A";
					String memberName = "N/A";

					try {
						Book book = bookService.getBookById(record.getBookId());
						if (book != null) {
							bookTitle = book.getTitle();
						}
					} catch (LibraryException e) {
						System.err.println("Error fetching book for report: " + e.getMessage());
					} catch (Exception e) {
						System.err.println("Unexpected error fetching book for report: " + e.getMessage());
					}

					try {
						Member member = memberService.getMemberById(record.getMemberId());
						if (member != null) {
							memberName = member.getName();
						}
					} catch (LibraryException e) { 
						System.err.println("Error fetching member for report: " + e.getMessage());
					} catch (Exception e) {
						System.err.println("Unexpected error fetching member for report: " + e.getMessage());
					}

					sb.append("Book ID: ").append(record.getBookId()).append("\n");
					sb.append("  Title: ").append(bookTitle).append("\n");
					sb.append("  Issued to: ").append(memberName).append(" (Member ID: ").append(record.getMemberId())
							.append(")\n");
					sb.append("  Issue Date: ").append(record.getIssueDate().format(formatter)).append("\n");
					sb.append("-------------------------------------------\n");
				}
				reportDisplayArea.setText(sb.toString());
			}
		} catch (LibraryException e) {
			messageLabel.setText("Database error generating report: " + e.getMessage());
		} catch (Exception e) {
			messageLabel.setText("An unexpected error occurred generating report: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void handleBooksByCategoryReport() {
		reportDisplayArea.clear();
		messageLabel.setText("");
		messageLabel.setTextFill(javafx.scene.paint.Color.RED);

		try {
			Map<String, Long> booksByCategory = bookService.getBooksCountByCategory();

			if (booksByCategory.isEmpty()) {
				reportDisplayArea.setText("No books found to categorize.");
			} else {
				StringBuilder sb = new StringBuilder("--- Books Per Category ---\n\n");
				booksByCategory.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(
						entry -> sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" books\n"));

				reportDisplayArea.setText(sb.toString());
			}
		} catch (LibraryException e) {
			messageLabel.setText("Database error generating report: " + e.getMessage());
		} catch (Exception e) {
			messageLabel.setText("An unexpected error occurred generating report: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void handleMembersWithActiveBooksReport() {
		reportDisplayArea.clear();
		messageLabel.setText("");
		messageLabel.setTextFill(javafx.scene.paint.Color.RED);

		try {
			List<Member> membersWithActiveBooks = issueService.getMembersWithActiveBooks();

			if (membersWithActiveBooks.isEmpty()) {
				reportDisplayArea.setText("No members currently have active issued books.");
			} else {
				StringBuilder sb = new StringBuilder("--- Members with Active Issued Books ---\n\n");
				membersWithActiveBooks.stream().sorted((m1, m2) -> Integer.compare(m1.getMemberID(), m2.getMemberID()))
						.forEach(member -> sb.append("Member ID: ").append(member.getMemberID()).append(" - Name: ")
								.append(member.getName()).append("\n"));

				reportDisplayArea.setText(sb.toString());
			}
		} catch (LibraryException e) {
			messageLabel.setText("Database error generating report: " + e.getMessage());
		} catch (Exception e) {
			messageLabel.setText("An unexpected error occurred generating report: " + e.getMessage());
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