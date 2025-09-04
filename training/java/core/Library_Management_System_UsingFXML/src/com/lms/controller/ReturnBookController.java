package com.lms.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.lms.Utils.ControllerUtil;
import com.lms.main.Main;
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

public class ReturnBookController {
	
	MemberService memberService;
	IssueLogService IssueBookService;
	BookService bookService;
	
	
	@FXML
	ComboBox<String> memberComboBox;
	
	@FXML
	ComboBox<String> bookComboBox;
	
	@FXML
	TextField emailField;
	
	@FXML
	Label errorLabel;
	
	@FXML
    public void initialize() {
		memberService = new MemberServiceImpl();
		IssueBookService = new IssueLogServiceImpl();
		bookService = new BookServiceImpl();
		List<String> members= memberService.getAllMembers().stream()
								.map(m->{
									return m.getMember_Id() +" ."+ m.getMember_Name();
								})
								.collect(Collectors.toList());
		memberComboBox.getItems().addAll(members);
	}
	
	@FXML
	void cancelButtonClick(){
		try {
			Main.changePage("LibraryHome");
		} catch (IOException e) {
			System.out.println("Couldn't change to home page " + e.getMessage());
		}
	}
	
	@FXML
	void getBooksByMember(){
		if(memberComboBox.getValue() == null) {
			return;
		}
		System.out.println(memberComboBox.getValue());
		int memberId =  Integer.parseInt(memberComboBox.getValue().split("\\.")[0].trim());
		
		List<String> records= IssueBookService.getAllIssuedRecords().stream()
										.filter(r->{
											return r.getMemberId() == memberId && r.getStatus() == 'I';
										})
										.map(r->{
											return r.getIssueId()+"-"+r.getBookId()+" - " +bookService.getBookById(r.getBookId()).getBook_Title();
										})
										.collect(Collectors.toList());
		
		bookComboBox.getItems().clear();
		bookComboBox.getItems().addAll(records);
		
	}
	
	@FXML
	void returnButtonClick(){
		if(bookComboBox.getValue()!= null && bookComboBox.getValue()!=null) {
			int bookId = Integer.parseInt(bookComboBox.getValue().split("-")[1].trim());
			int issueId = Integer.parseInt(bookComboBox.getValue().split("-")[0].trim());
			bookService.updateBookAvailability(bookId,true);
			
			IssueBookService.returnIssuedBook(issueId, true);
			ControllerUtil.createAlert(AlertType.CONFIRMATION,"Success","Return done","The selected book has been returned and has been updated!");

			try {
				Main.changePage("LibraryHome");
			} catch (IOException e) {
				System.out.println("Error changing to home page");
			}
		}
		else {
			ControllerUtil.createAlert(AlertType.WARNING,"Alert!","Data not selected","Please select a Member and a Book");
		}
		
	}
	
//	@FXML
//	void issueButtonClick() {
//		String selectedMember = memberComboBox.getValue();
//        if (selectedMember != null) {
//            int memberId = Integer.parseInt(selectedMember.split("\\.")[0].trim());
//            displayMemberBooks(memberId);
//            emailField.clear();
//            errorLabel.setText("");
//        }
//	}
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
        memberComboBox.setValue(memberString);
        
        errorLabel.setText("");
	}
	
	
	
}
