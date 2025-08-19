package com.lms.serviceImpl;

import com.lms.dao.BookDao;
import com.lms.exceptions.DAOException;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.util.Validator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookDao bookDao;

    public List<Book> getAllBooks() throws DAOException {
        logger.info("Fetching all books");
        try {
            return bookDao.findAll();
        } catch (Exception e) {
            logger.error("Failed to fetch books", e);
            throw new DAOException("Failed to fetch books", e);
        }
    }

    public Book getBookById(String bookId) throws DAOException {
        logger.info("Fetching book with ID: {}", bookId);
        try {
        	Validator.validateBookId(bookId);
            Book book = bookDao.findByBookId(bookId);
            if (book == null) {
                logger.warn("Book not found with ID: {}", bookId);
                throw new DAOException("Book not found with ID: " + bookId);
            }
            return book;
        } catch (Exception e) {
            logger.error("Error fetching book by ID: {}", bookId, e);
            throw new DAOException("Database error while fetching book by ID", e);
        }
    }

    @Transactional
    public void addBook(Book book) throws InvalidInputException, DAOException {
        logger.info("Adding new book: {}", book.getBookTitle());


        if (book.getStatus() == null) book.setStatus("A");
        if (book.getAvailability() == null) book.setAvailability("A");
        Validator.validateBook(book);


        try {
            boolean saved = bookDao.save(book);
            if (!saved) {
                logger.error("Failed to insert book: {}", book.getBookTitle());
                throw new DAOException("Failed to insert book");
            }
            logger.info("Book added successfully: {}", book.getBookTitle());
        } catch (Exception e) {
            logger.error("Database error while inserting book: {}", book.getBookTitle(), e);
            throw new DAOException("Database error while inserting book", e);
        }
    }

    @Transactional
    public void updateBook(Book book) throws InvalidInputException,DAOException,Exception {
        logger.info("Updating book with ID: {}", book.getBookId());

        Validator.validateBook(book);

        Book existing = bookDao.findByBookId(book.getBookId());
        if (existing == null) {
            logger.warn("Book not found with ID: {}", book.getBookId());
            throw new InvalidInputException("Book not found.");
        }

        try {
            boolean updated = bookDao.update(book);
            if (!updated) {
                logger.error("Failed to update book in database: {}", book.getBookId());
                throw new DAOException("Failed to update book in database");
            }
            logger.info("Book updated successfully: {}", book.getBookId());
        } catch (Exception e) {
            logger.error("Database error while updating book: {}", book.getBookId(), e);
            throw new DAOException("Database error while updating book", e);
        }
    }
}
