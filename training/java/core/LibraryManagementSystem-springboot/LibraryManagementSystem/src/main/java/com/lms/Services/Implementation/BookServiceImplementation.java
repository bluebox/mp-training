package com.lms.Services.Implementation;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.Exceptions.BookDaoException;
import com.lms.Models.Book;
import com.lms.Repository.BookRepo;
import com.lms.Services.Interfaces.BookService;

import java.util.List;

@Service
public class BookServiceImplementation implements BookService {

    @Autowired
    private BookRepo bookDAO;

    @Override
    public int addBook(Book book) {
        if (book == null || book.getTitle() == null || book.getAuthor() == null || book.getCategory() == null) {
            throw new BookDaoException("Invalid book data: Title, Author, and Category must not be null.");
        }
        return bookDAO.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        if (book == null || book.getBookId() == null || book.getBookId() <= 0) {
            throw new BookDaoException("Invalid book ID for update.");
        }
        bookDAO.updateBook(book);
    }

    @Override
    public void updateAvailability(Book book) {
        if (book == null || book.getBookId() == null || book.getBookId() <= 0) {
            throw new BookDaoException("Invalid book ID for availability update.");
        }
        bookDAO.updateAvailability(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }
}
