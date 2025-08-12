package com.libraryManagementSystem.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.dao.ReportsDao;
import com.libraryManagementSystem.dao.impl.ReportsDaoImpl;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.InvalidIssueDataException;
import com.libraryManagementSystem.exceptions.InvalidMemberDataException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.services.IssueService;
import com.libraryManagementSystem.services.MemberService;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.services.impl.IssueServiceImpl;
import com.libraryManagementSystem.services.impl.MemberServiceImpl;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookStatus;
import com.libraryManagementSystem.utilities.Validations;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class ReturnController implements Initializable {

	BookServices bookService = new BookServicesImpl();
	MemberService memberService = new MemberServiceImpl();
	IssueService issueService = new IssueServiceImpl();
	private final ReportsDao reportsService = new ReportsDaoImpl();

	private ObservableList<Book> allBooks;
	private ObservableList<Member> allMembers;

	@FXML
	private TextField bookSearchField;
	@FXML
	private TextField memberSearchField;

	private ContextMenu suggestionsPopup = new ContextMenu();
	private Book selectedBook = null;
	private Member selectedMember = null;

	@FXML
	private DatePicker returnDate;
	@FXML
	private Label error;

	@FXML
	public void switchToIssueAndReturn() throws Exception {
		App.setRoot("Issue&Return");
	}

	@FXML
	public void switchTOPrimary() throws Exception {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			allBooks = FXCollections.observableArrayList(bookService.getBooks());
			setupSearch();
			allMembers = FXCollections.observableArrayList(memberService.getMembers());
			setupSearchMember();
			returnDate.setEditable(false);

		} catch (DatabaseOperationException e) {
			error.setText(e.getMessage());
		} catch (DatabaseConnectionException e) {
			error.setText(e.getMessage());
		} catch (StatementPreparationException e) {
			error.setText(e.getMessage());
		}

	}

	private void setupSearch() {
		bookSearchField.textProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal == null || newVal.isEmpty()) {
				suggestionsPopup.hide();
				selectedBook = null;
				return;
			}

			bookSearchField.setTextFormatter(new TextFormatter<>(change -> {
				error.setText("");
				if (Validations.isValidTitle(change.getControlNewText())) {
					return change;
				}
				setError("Please use only letters, numbers, and allowed punctuation in the title.");
				return null;
			}));

			String filter = newVal.toLowerCase();
			List<Book> matches = allBooks.stream().filter(
					b -> b.getTitle().toLowerCase().contains(filter) || b.getAuthor().toLowerCase().contains(filter))
					.filter(b -> b.getStatus().equals(BookStatus.ACTIVE)
							&& b.getAvailability().equals(BookAvailability.ISSUED))
					.collect(Collectors.toList());

			Book exactMatch = allBooks.stream()
					.filter(b -> (b.getTitle() + " - " + b.getAuthor()).equalsIgnoreCase(newVal)).findFirst()
					.orElse(null);

			if (exactMatch != null) {
				selectedBook = exactMatch;

			} else {
				selectedBook = null;

			}

			if (matches.isEmpty()) {
				suggestionsPopup.hide();
			} else {
				populateSuggestions(matches);
				if (!suggestionsPopup.isShowing()) {
					suggestionsPopup.show(bookSearchField, javafx.geometry.Side.BOTTOM, 0, 0);
				}
			}
		});
	}

	private void setupSearchMember() {
		memberSearchField.textProperty().addListener((obs, oldVal, newVal) -> {

			if (newVal == null || newVal.isEmpty()) {
				suggestionsPopup.hide();
				selectedBook = null;
				return;
			}

			memberSearchField.setTextFormatter(new TextFormatter<>(change -> {
				error.setText("");
				if (Validations.isValidIssueTitle(change.getControlNewText())) {
					return change;
				}
				setError("Please use only letters and spaces for the author's name.");
				return null;
			}));

			try {
				List<Integer> ids = reportsService.getActiveIssuedBooks().stream().map(i -> i.getMemberId())
						.collect(Collectors.toList());

				String filter = newVal.toLowerCase();
				List<Member> matches = allMembers.stream().filter(m -> m.getName().contains(filter))
						.filter(m -> ids.contains(m.getMemberId())).collect(Collectors.toList());

				Member exactMatch = allMembers.stream()
						.filter(m -> (m.getName() + "(" + m.getMobile() + ")").equalsIgnoreCase(newVal)).findFirst()
						.orElse(null);

				if (exactMatch != null) {
					selectedMember = exactMatch;

				} else {
					selectedMember = null;

				}

				if (matches.isEmpty()) {
					suggestionsPopup.hide();
				} else {
					populateSuggestionsMember(matches);
					if (!suggestionsPopup.isShowing()) {
						suggestionsPopup.show(memberSearchField, javafx.geometry.Side.BOTTOM, 0, 0);
					}
				}
			} catch (DatabaseOperationException e) {
				setError(e.getMessage());
			}

		});
	}

	private void populateSuggestions(List<Book> matches) {
		suggestionsPopup.getItems().clear();
		for (Book b : matches) {
			MenuItem item = new MenuItem(b.getTitle() + " - " + b.getAuthor());
			item.setOnAction(e -> {
				selectedBook = b;
				bookSearchField.setText(b.getTitle() + " - " + b.getAuthor());
				suggestionsPopup.hide();
			});
			suggestionsPopup.getItems().add(item);
		}
	}

	private void populateSuggestionsMember(List<Member> matches) {
		suggestionsPopup.getItems().clear();
		for (Member m : matches) {
			MenuItem item = new MenuItem(m.getName() + "(" + m.getMobile() + ")");
			item.setOnAction(e -> {
				selectedMember = m;
				memberSearchField.setText(m.getName() + "(" + m.getMobile() + ")");
				suggestionsPopup.hide();
			});
			suggestionsPopup.getItems().add(item);
		}
	}

	@FXML
	public void returnBook() {

		Book book = selectedBook;
		Member member = selectedMember;
		LocalDate date = returnDate.getValue();

		try {

			issueService.returnBook(book, member, date);

			Platform.runLater(() -> {
				error.setText(book.getTitle() + " Book  is Return By " + member.getName());
				error.setStyle("-fx-text-fill: green");
			});

			returnDate.setValue(null);
			memberSearchField.setText("");
			bookSearchField.setText("");
			initialize(null, null);

		} catch (DatabaseOperationException | InvalidIssueDataException | BookNotFoundException
				| InvalidMemberDataException e) {
			error.setText(e.getMessage());
			error.setStyle("-fx-text-fill: red");
		}

	}

	private void setError(String message) {
		error.setText(message);
		error.setStyle("-fx-text-fill: red");
	}
}
