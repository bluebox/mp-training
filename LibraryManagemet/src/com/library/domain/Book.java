package com.library.domain;

public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private String status;
    private String availability;

    public Book(int id, String title, String author, String category, String status, String availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.availability = availability;
    }

    public Book(String title, String author, String category, String status, String availability) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.availability = availability;
    }

    public int getBookId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public String getStatus() { return status; }
    public String getAvailability() { return availability; }
}
