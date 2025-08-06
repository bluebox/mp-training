package com.example.beans;


import org.springframework.stereotype.Component;

@Component
public class Book {
    private String title;

    public Book() {
        // Default constructor
    }

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + "]";
    }
}