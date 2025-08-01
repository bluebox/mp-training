package com.libraryManagementSystem.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.services.IssueService;
import com.libraryManagementSystem.services.MemberService;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.services.impl.IssueServiceImpl;
import com.libraryManagementSystem.services.impl.MemberServiceImpl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class ReturnController implements Initializable {

	BookServices bookService = new BookServicesImpl();
	MemberService memberService = new MemberServiceImpl();
	IssueService issueService = new IssueServiceImpl();;

	@FXML
	private DatePicker returnDate;
	@FXML
	private ComboBox<Member> members;
	@FXML
	private ComboBox<Book> books;
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

		List<Member> membersList;
		try {
			membersList = memberService.getMembers();
			members.getItems().addAll(membersList);

			List<Book> bookTitles = bookService.getBooks();

			books.getItems().addAll(bookTitles);

			members.setOnAction(event -> {
				returnPropertySetting();
			});

			books.setOnAction(event -> {

				returnPropertySetting();
			});

		} catch (InvalidException e) {
			error.setText(e.getMessage());
		}

	}

	@FXML
	public void returnBook() {

		Book book = books.getSelectionModel().getSelectedItem();
		Member member = members.getSelectionModel().getSelectedItem();
		LocalDate date = returnDate.getValue();

		if (book != null && member != null && date != null) {

			try {

				issueService.returnBook(book, member.getMemberId(), date);

				error.setText(book.getTitle() + " Book is Issued to " + member.getName());

				error.setStyle("-fx-text-fill: green");

				returnDate.setValue(null);
				members.setValue(null);
				books.setValue(null);

				members.setPromptText("Select Member");
				books.setPromptText("Select Book");

			} catch (InvalidException e) {
				error.setText(e.getMessage());
				error.setStyle("-fx-text-fill: red");
			}
		} else {

			error.setText("Select Book, Member and Date");
			error.setStyle("-fx-text-fill: red");
		}
	}

	private void returnPropertySetting() {

		if ((!(members.getSelectionModel().isEmpty())) && (!(books.getSelectionModel().isEmpty()))) {
			returnDate.setDisable(false);
		}

	}
}
