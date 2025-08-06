package com.example.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Library {
    private Book book;
    private Author author;

    public Library() {
    }

    // @Autowired on a method to inject Book and Author
    @Autowired
    public void setupLibrary(Book book, Author author) {
        this.book = book;
        this.author = author;
        System.out.println("Library setup with: " + book + " by " + author);
    }

    public Book getBook() {
        return book;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Library [book=" + book + ", author=" + author + "]";
    }
}
