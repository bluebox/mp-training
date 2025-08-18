package com.lms.LMS_Springboot.Service;

import com.lms.LMS_Springboot.DAO.BookDAO;
import com.lms.LMS_Springboot.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public int addBook(Book book) {
        return bookDAO.addBook(book);
    }

    public int updateBook(int id, Book book) {
        return bookDAO.updateBook(id, book);
    }
}
