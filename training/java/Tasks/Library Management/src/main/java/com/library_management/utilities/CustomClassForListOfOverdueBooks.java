package com.library_management.utilities;

import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomClassForListOfOverdueBooks {
	private final SimpleIntegerProperty bookId;
	private final SimpleIntegerProperty memberId;
	private final LocalDate issueDate;

    
    
	public CustomClassForListOfOverdueBooks(int bookId, int memberId, LocalDate issueDate) {
		this.bookId = new SimpleIntegerProperty();
		this.memberId=new SimpleIntegerProperty();
		this.issueDate=issueDate;
		
		// TODO Auto-generated constructor stub
	}



	public SimpleIntegerProperty getBookId() {
		return bookId;
	}



	public SimpleIntegerProperty getMemberId() {
		return memberId;
	}



	public LocalDate getIssueDate() {
		return issueDate;
	}

	
}
