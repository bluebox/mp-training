package com.lms.Services.Interfaces;



import com.lms.Models.Book;
import java.util.List;

public interface BookService {

    int addBook(Book book);

    void updateBook(Book book);

    void updateAvailability(Book book);

    List<Book> getAllBooks();
}

