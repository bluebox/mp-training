package com.library_management.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.library_management.App;
import com.library_management.dao.implementation.BookDaoImplementation;
import com.library_management.dao.implementation.MemberDaoImplementation;
import com.library_management.domain.Book;
import com.library_management.domain.Member;
import com.library_management.exceptions.DatabaseException;
import com.library_management.exceptions.InvalidDetailsException;
import com.library_management.services.IssueRecordServiceInterface;
import com.library_management.services.implementation.IssueRecordServiceImplementation;
import com.library_management.utilities.ResponseHandler;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ReturnBookController {

	IssueRecordServiceInterface service = new IssueRecordServiceImplementation();

	private List<Book> booksList;
	private List<Member> memberList;
	@FXML
    private void backButton() throws IOException {
        App.setRoot("IssueReturnMain");
    }
	
	@FXML
	private void homeButton() throws IOException{
		App.setRoot("home");
	}

	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
	}

	public List<Book> getBooksList() {
		return this.booksList;
	}

	public void setMembersList(List<Member> memberList) {
		this.memberList = memberList;
	}

	public List<Member> getMembersList() {
		return this.memberList;
	}

	@FXML
	private ComboBox<String> bookSelector;

	@FXML
	private ComboBox<String> memberSelector;

	@FXML
	private Text message;

	private int memberId;

	@FXML
	public void initialize() {
//		try {
//			List<Book> booksList = new BookDAOImplementation().selectBooksByMemberId();
//			this.setBooksList(booksList);
//			for (Book book : booksList) {
//				bookSelector.getItems().add(book.getBookId() + ". " + book.getTitle());
//			}
//		} catch (DatabaseException e) {
//			e.printStackTrace();
//		}
		try {
			List<Member> membersList = new MemberDaoImplementation().getAllMembers();
			this.setMembersList(membersList);
			for (Member member : membersList) {
				memberSelector.getItems().add(member.getMemberId() + ". " + member.getMemberName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void loadBookDetails() {
		System.out.println("Book selected: " + bookSelector.getValue());
	}

	@FXML
	public void loadMemberDetails() {
		try {
			if (memberSelector.getValue() == null) {
				throw new InvalidDetailsException("Select Member To Return...");
			}

			bookSelector.getItems().clear();

			memberId = Integer.parseInt(memberSelector.getValue().split("\\.")[0].trim());
			System.out.println(memberId);

			List<Book> booksList = new BookDaoImplementation().selectAllMemberBooks(memberId);
			this.setBooksList(booksList);

			for (Book book : booksList) {
				bookSelector.getItems().add(book.getBookId() + ". " + book.getTitle());
			}

			System.out.println("Member selected: " + memberSelector.getValue());
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
//		System.out.println("Member selected: " + memberSelector.getValue());
		catch (InvalidDetailsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void switchToReturnBookSubmit() {
		try {
			if (bookSelector.getValue() == null) {
				throw new InvalidDetailsException("Select Book To Return...");
			}

			int bookId = Integer.parseInt(bookSelector.getValue().split("\\.")[0].trim());

			String print_message = service.returnBook(memberId, bookId);
			System.out.println(print_message);
			if (print_message == "Book returned successfully") {
				ResponseHandler.showResponse(message, print_message, Color.GREEN);
			} else {
				ResponseHandler.showResponse(message, print_message, Color.RED);
			}
		} catch (InvalidDetailsException e) {
			ResponseHandler.showResponse(message, e.getMessage(), Color.RED);
		}
	}
}
