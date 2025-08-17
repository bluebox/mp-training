package com.lms.springbootlms.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.lms.springbootlms.dao.BookDao;
import com.lms.springbootlms.exception.DaoException;
import com.lms.springbootlms.exception.InvalidInputException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.service.BookService;
import com.lms.springbootlms.util.Validator;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book getBookById(String bookId) throws InvalidInputException {
        Validator.serviceValidateBookId(bookId);

        Book currentBook;
        try {
            currentBook = bookDao.getBookById(bookId);
        } catch (DataAccessException  | DaoException e) {
            throw new InvalidInputException("Error connecting to database.", e);
        }

        if (currentBook == null) {
            throw new InvalidInputException("Book not found with ID: " + bookId);
        }

        return currentBook;
    }

    @Override
    public List<Book> getAllBooks() throws InvalidInputException {
        try {
            return bookDao.getBooks();
        } catch (DataAccessException  | DaoException e) {
            throw new InvalidInputException("Error fetching books from database.", e);
        }
    }

    @Override
    public void addBook(Book newBook) throws InvalidInputException {
        Validator.serviceValidateBook(newBook);

        try {
            Book existing = bookDao.getBookByTitle(newBook.getBookTitle());
            if (existing != null) {
                throw new InvalidInputException("A book with this title already exists.");
            }
            bookDao.addBook(newBook);
        } catch (DataAccessException  | DaoException e) {
            throw new InvalidInputException("Unable to add book. Database error.", e);
        }
    }

    @Override
    public Boolean updateBook(Book updateBook) throws InvalidInputException {
        Validator.serviceValidateBook(updateBook);

        Book existing = getBookById(updateBook.getBookId());

        if (!existing.getBookTitle().equalsIgnoreCase(updateBook.getBookTitle())) {
            try {
                Book duplicateBook = bookDao.getBookByTitle(updateBook.getBookTitle());
                if (duplicateBook != null && !duplicateBook.getBookId().equals(updateBook.getBookId())) {
                    throw new InvalidInputException("Another book with this title already exists.");
                }
            } catch (DataAccessException  | DaoException e) {
                throw new InvalidInputException("Database error while checking duplicate book.", e);
            }
        }

        try {
            return bookDao.updateBook(updateBook);
        } catch (DataAccessException  | DaoException e) {
            throw new InvalidInputException("Unable to update book. Database error.", e);
        }
    }
    @Override
    public List<Book> getAvailableBooks() throws InvalidInputException  {
    	try {
			return bookDao.getAvailableBooks();
		} catch (DataAccessException  | DaoException e) {
			throw new InvalidInputException("Error fetching available books from database.", e);
		}
    }

}
