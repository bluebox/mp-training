package com.libraryManagementSystem.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.IssueRecord;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.services.IssueService;
import com.libraryManagementSystem.services.MemberService;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.services.impl.IssueServiceImpl;
import com.libraryManagementSystem.services.impl.MemberServiceImpl;
import com.libraryManagementSystem.utilities.IssueStatus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class IssueController implements Initializable {

	BookServices bookService = new BookServicesImpl();
	MemberService memberService = new MemberServiceImpl();
	IssueService issueService = new IssueServiceImpl();;

	@FXML
	private DatePicker issueDate;
	@FXML
	private ComboBox<Member> members;
	@FXML
	private ComboBox<Book> books;
	@FXML
	private Label error;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<Member> membersList;
		try {
			membersList = memberService.getMembers();
			members.getItems().addAll(membersList);

			List<Book> bookTitles = bookService.getBooks();
			books.getItems().addAll(bookTitles);

			members.setOnAction(event -> {
				issuePropertySetting();
			});

			books.setOnAction(event -> {
				issuePropertySetting();
			});

		} catch (InvalidException e) {
			error.setText(e.getMessage());
		}

	}

	@FXML
	public void issueBook() {

		Book book = books.getSelectionModel().getSelectedItem();
		Member member = members.getSelectionModel().getSelectedItem();
		LocalDate date = issueDate.getValue();
		if (book != null && member != null && date != null) {
			IssueRecord newIssue = new IssueRecord(-1, book.getBookId(), member.getMemberId(), IssueStatus.ISSUED, date,
					null);
			try {

				issueService.addIssue(newIssue);

				error.setText(member.getName() + " Rssued " + book.getTitle() + " Book");

				error.setStyle("-fx-text-fill: green");

				issueDate.setValue(null);
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

	@FXML
	public void switchToIssueAndReturn() throws Exception {
		App.setRoot("Issue&Return");
	}

	@FXML
	public void switchTOPrimary() throws Exception {
		App.setRoot("primary");
	}

	private void issuePropertySetting() {

		if ((!(members.getSelectionModel().isEmpty())) && (!(books.getSelectionModel().isEmpty()))) {
			issueDate.setDisable(false);
		}

	}

}
