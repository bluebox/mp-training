package com.lms.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.lms.main.Main;
import com.lms.service.BookService;
import com.lms.service.IssueLogService;
import com.lms.service.MemberService;
import com.lms.service.impl.BookServiceImpl;
import com.lms.service.impl.IssueLogServiceImpl;
import com.lms.service.impl.MemberServiceImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class BookMembersController {
	private static IssueLogService issueLogservice;
	private static BookService bookService;
	private static MemberService memberService;
	
	static {
		issueLogservice = new IssueLogServiceImpl();
		bookService=new BookServiceImpl();
		memberService=new MemberServiceImpl();
	}
	
	@FXML
	ComboBox<String> BooksComboBox;
	
	@FXML
	private TextArea members;
	
	@FXML
    public void initialize() {	
		List<String> books=bookService.getAllBooks().stream().map(book->book.getBook_Id()+". "+book.getBook_Title()).collect(Collectors.toList()); 
		BooksComboBox.getItems().addAll(books);
	}
	
	
	@FXML
	public void getMembers() {
		if (BooksComboBox.getValue()==null) {
            showAlert("Error", "Please select the bookId!");
            return;
        }
		
		List<String> membersList=issueLogservice.getAllIssuedRecords().stream().
				filter(record-> {
				int bookId = Integer.parseInt(BooksComboBox.getValue().split("\\.")[0].trim());
				return record.getBookId()==bookId;}).map(r->memberService.getMemberNameById(r.getMemberId())).distinct().collect(Collectors.toList());
        members.setText(membersList.stream().collect(Collectors.joining("\n")));
	}
	
	@FXML
	void returnButtonClick(){
		try {
			Main.changePage("LibraryHome");
		} catch (IOException e) {
			System.out.println("Couldn't change to home page " + e.getMessage());
		}
	}
	private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}
