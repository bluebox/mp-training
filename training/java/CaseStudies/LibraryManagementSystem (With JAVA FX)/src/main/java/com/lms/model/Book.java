package com.lms.model;


//testing if this is updated

public class Book {
	private int book_Id;
	private String book_Title;
	private String book_Author;
	private String book_Category;
	private char book_Status;
	private char book_Availability;
	
	
	public Book(String book_Title, String book_Author, String book_Category) {
		this.book_Title=book_Title;
		this.book_Author=book_Author;
		this.book_Category=book_Category;
		this.book_Status=BookStatus.ACTIVE.getCode();
		this.book_Availability=BookAvailability.AVAILABLE.getCode();
		
	}
	
	
	public Book(int book_Id, String book_Title, String book_Author, String book_Category, char book_Status,char book_Availability) {
		this.book_Id=book_Id;
		this.book_Title = book_Title;
		this.book_Author = book_Author;
		this.book_Category = book_Category;
		this.book_Status = book_Status;
		this.book_Availability = book_Availability;
	}
	
	public Book() {
		this("","","");
	}

	public String getBook_Title() {
		return book_Title;
	}
	public void setBook_Title(String book_Title) {
		this.book_Title = book_Title;
	}
	public String getBook_Author() {
		return book_Author;
	}
	public void setBook_Author(String book_Author) {
		this.book_Author = book_Author;
	}
	public String getBook_Category() {
		return book_Category;
	}
	public void setBook_Category(String book_Category) {
		this.book_Category = book_Category;
	}
	public char getBook_Status() {
		return book_Status;
	}
	public void setBook_Status(char book_Status) {
		this.book_Status = book_Status;
	}
	public char getBook_Availability() {
		return book_Availability;
	}
	public void setBook_Availability(char book_Availability) {
		this.book_Availability = book_Availability;
	}
	public int getBook_Id() {
		return book_Id;
	}
	
	public void setBook_Id(int book_Id) {
		this.book_Id = book_Id;
	}

}
