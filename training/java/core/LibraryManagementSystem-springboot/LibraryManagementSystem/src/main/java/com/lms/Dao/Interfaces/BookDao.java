package com.lms.Dao.Interfaces;



import java.util.List;
import com.lms.Models.Book;

public interface BookDao {
    int addBook(Book book); 
    void updateBook(Book book);
    void updateAvailability(Book book);
    List<Book> getAllBooks();
}
