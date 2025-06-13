package com.libraryManagement.utility.pojos;

public class Book {
     private Integer bookId;
     private String title;
     private String author;
     private String category;
     private Character status;
     private Character availability;
	 public Integer getBookId() {
		 return bookId;
	 }
	 public void setBookId(Integer bookId) {
		 this.bookId = bookId;
	 }
	 public String getTitle() {
		 return title;
	 }
	 public void setTitle(String ittle) {
		 this.title = ittle;
	 }
	 public String getAuthor() {
		 return author;
	 }
	 public void setAuthor(String author) {
		 this.author = author;
	 }
	 public String getCategory() {
		 return category;
	 }
	 public void setCategory(String category) {
		 this.category = category;
	 }
	 public Character getStatus() {
		 return status;
	 }
	 public void setStatus(Character status) {
		 this.status = status;
	 }
	 public Character getAvailability() {
		 return availability;
	 }
	 public void setAvailability(Character availability) {
		 this.availability = availability;
	 }
     
}
