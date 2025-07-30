package library.ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class IssuedRecordsScreen {

	@FXML
	private TableView<IssueRecord> issuedRecordsTableView;
	@FXML
	private TableColumn<IssueRecord, Integer> issueIdColumn;
	@FXML
	private TableColumn<IssueRecord, Integer> bookIdColumn;
	@FXML
	private TableColumn<IssueRecord, String> bookTitleColumn;
	@FXML
	private TableColumn<IssueRecord, Integer> memberIdColumn;
	@FXML
	private TableColumn<IssueRecord, String> memberNameColumn;
	@FXML
	private TableColumn<IssueRecord, String> statusColumn;
	@FXML
	private TableColumn<IssueRecord, LocalDateTime> issueDateColumn;
	@FXML
	private TableColumn<IssueRecord, String> issuedByColumn;
	@FXML
	private TableColumn<IssueRecord, LocalDateTime> returnDateColumn;
	@FXML
	private TableColumn<IssueRecord, String> returnedByColumn;

	@FXML
	private Label messageLabel;

	private IssueService issueService; 
	private BookService bookService; 
	private MemberService memberService; 

	public IssuedRecordsScreen() {
		this.issueService = new IssueServiceImpl();
		this.bookService = new BookServiceImpl();
		this.memberService = new MemberServiceImpl();
	}

	@FXML
	private void initialize() {
		issueIdColumn.setCellValueFactory(new PropertyValueFactory<>("issueId"));
		bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));

		statusColumn.setCellValueFactory(
				cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus().toString()));

		
		issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
		returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		issueDateColumn.setCellFactory(column -> new TableCell<IssueRecord, LocalDateTime>() {
			@Override
			protected void updateItem(LocalDateTime item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty || item == null ? null : item.format(formatter));
			}
		});

		returnDateColumn.setCellFactory(column -> new TableCell<IssueRecord, LocalDateTime>() {
			@Override
			protected void updateItem(LocalDateTime item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty || item == null ? null : item.format(formatter));
			}
		});

		issuedByColumn.setCellValueFactory(new PropertyValueFactory<>("issuedBy"));
		returnedByColumn.setCellValueFactory(new PropertyValueFactory<>("returnedBy"));

		bookTitleColumn.setCellValueFactory(cellData -> {
			try {
				Book book = bookService.getBookById(cellData.getValue().getBookId());
				return new javafx.beans.property.SimpleStringProperty(book != null ? book.getTitle() : "N/A");
			} catch (LibraryException e) {
				System.err.println("Error fetching book title for issue record: " + e.getMessage());
				return new javafx.beans.property.SimpleStringProperty("Error");
			} catch (Exception e) {
				System.err.println("Unexpected error fetching book title for issue record: " + e.getMessage());
				return new javafx.beans.property.SimpleStringProperty("Error");
			}
		});

		memberNameColumn.setCellValueFactory(cellData -> {
			try {
				Member member = memberService.getMemberById(cellData.getValue().getMemberId());
				return new javafx.beans.property.SimpleStringProperty(member != null ? member.getName() : "N/A");
			} catch (LibraryException e) { 
				System.err.println("Error fetching member name for issue record: " + e.getMessage());
				return new javafx.beans.property.SimpleStringProperty("Error");
			} catch (Exception e) {
				System.err.println("Unexpected error fetching member name for issue record: " + e.getMessage());
				return new javafx.beans.property.SimpleStringProperty("Error");
			}
		});

		loadIssuedRecords();
	}

	@FXML
	private void handleRefreshRecords() {
		loadIssuedRecords();
	}

	private void loadIssuedRecords() {
		try {
			List<IssueRecord> records = issueService.getAllIssuedRecords();
			ObservableList<IssueRecord> observableRecords = FXCollections.observableArrayList(records);
			issuedRecordsTableView.setItems(observableRecords);
			messageLabel.setText("");
		} catch (LibraryException e) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("Database error loading records: " + e.getMessage());
		} catch (Exception e) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
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
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("Error navigating back to main menu: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			messageLabel.setTextFill(javafx.scene.paint.Color.RED);
			messageLabel.setText("An unexpected error occurred during navigation: " + e.getMessage());
			e.printStackTrace();
		}
	}
}