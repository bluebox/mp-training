package com.lms.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.lms.Utils.ControllerUtil;
import com.lms.exceptions.EmptyFieldsException;
import com.lms.exceptions.IdNotExistException;
import com.lms.main.Main;
import com.lms.model.Issue_Records;
import com.lms.model.Member;
import com.lms.service.BookService;
import com.lms.service.IssueLogService;
import com.lms.service.MemberService;
import com.lms.service.impl.BookServiceImpl;
import com.lms.service.impl.IssueLogServiceImpl;
import com.lms.service.impl.MemberServiceImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class IssueBookController {

	static MemberService memberService;
	static BookService bookService;
	static IssueLogService issueLogService;
	private static List<String> members;
	private static List<String> books;
	static {
		memberService = new MemberServiceImpl();
		bookService =new BookServiceImpl();
		issueLogService=new IssueLogServiceImpl();
 	}
	
	@FXML
	ComboBox<String> memberIdComboBox;
	@FXML
	ComboBox<String> bookIdComboBox;
	@FXML
	Label errorLabel;
	@FXML
	TextField emailField;
	@FXML
	public void initialize() {
		members=memberService.getAllMembers().stream().map(member-> member.getMember_Id()+". "+member.getMember_Name()).collect(Collectors.toList());

		books=bookService.getAllBooks().stream().filter(book-> ("A".equals(String.valueOf(book.getBook_Status())) && "A".equals(String.valueOf(book.getBook_Availability()))))
				.map(resultbook->resultbook.getBook_Id()+"-"+resultbook.getBook_Title()+"["+resultbook.getBook_Category()+"]").collect(Collectors.toList());
		memberIdComboBox.getItems().addAll(members);
		bookIdComboBox.getItems().addAll(books);
		
	}
	
	@FXML
	public void issueButtonClick() {
		try {
			String member = memberIdComboBox.getValue();
			String book = bookIdComboBox.getValue();
			
			if(member.isEmpty() || book.isEmpty()) {
				throw new EmptyFieldsException("One or more of the fields are empty");
			}
			Issue_Records record=new Issue_Records(Integer.parseInt(book.split("-")[0].trim()),Integer.parseInt(member.split("\\.")[0].trim()));
			issueLogService.addIssueRecord(record);
			ControllerUtil.createAlert(AlertType.CONFIRMATION, "Success", "Issued", book+ " is successfully issued");
			Main.changePage("LibraryHome");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException | EmptyFieldsException e ) {
			errorLabel.setText("Please enter all values");
		} catch(IdNotExistException e) {
				System.out.println("Please enter proper ID's ");
		}
	}
	
	@FXML 
	public void searchMemberByEmail(){
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            errorLabel.setText("Please enter an email address");
            return;
        }

        Member member = memberService.getMemberByEmail(email);
        if (member == null) {
            errorLabel.setText("No member found with this email");
            return;
        }

        String memberString = member.getMember_Id() + ". " + member.getMember_Name();
        memberIdComboBox.setValue(memberString);
        
        errorLabel.setText("");
	}
	
	@FXML
	public void cancleButtonClick() {
		try {
			Main.changePage("LibraryHome");
		} catch (IOException e) {
			System.out.println("Problem with going to home page");

		} 
	}
}
