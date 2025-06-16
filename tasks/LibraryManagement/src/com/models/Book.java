package com.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private char status;       
    private char availability; 

    public Book(String title, String author, String category, char status, char availability) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.availability = availability;
    }
    public Book(int Bookid, String title, String author, String category, char status, char availability) {
        this.id = Bookid;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.availability = availability;
    }
    
    public int getId()
    {
    	return this.id;
    }
    public String getTitle()
    {
    	return this.title;
    }
    public String getAuthor() {
		return this.author;
	}
    public String getCategory(){
		return this.category;
	}
    public char getStatus() {
		return this.status;
	}
    public char getAvailability() {
		return this.availability;
	}
}
