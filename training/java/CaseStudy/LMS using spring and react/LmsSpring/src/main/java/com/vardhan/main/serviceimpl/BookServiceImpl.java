package com.vardhan.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vardhan.main.dao.BookDao;
import com.vardhan.main.model.Book;
import com.vardhan.main.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book saveBook(Book book) throws DataAccessException {
        log.info("Attempting to save book with title: {}", book.getTitle());
        
        if (book.getStatus() == null) {
            book.setStatus("A");
        }
        if (book.getAvailability() == null) {
            book.setAvailability("A");
        }
        
        if (bookDao.existsByTitle(book.getTitle())) {
            throw new IllegalArgumentException("A book with this title already exists: " + book.getTitle());
        }

        Book savedBook = bookDao.save(book);
        log.info("Successfully saved book with ID: {}", savedBook.getBookId());
        return savedBook;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookById(Integer id) throws DataAccessException {
        log.debug("Finding book by internal ID: {}", id);
        return bookDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookByBookId(String bookId) throws DataAccessException {
        log.debug("Finding book by book ID: {}", bookId);
        return bookDao.findByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() throws DataAccessException {
        log.debug("Fetching all books");
        return bookDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByCategory(String category) throws DataAccessException {
        log.debug("Finding books by category: {}", category);
        return bookDao.findByCategory(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthor(String author) throws DataAccessException {
        log.debug("Finding books by author: {}", author);
        return bookDao.findByAuthor(author);
    }
 
    @Override
    @Transactional(readOnly = true)
    public List<Book> searchBooksByTitle(String title) throws DataAccessException {
        log.debug("Searching books by title: {}", title);
        return bookDao.findByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAvailableBooks() throws DataAccessException {
        log.debug("Fetching all available books");
        return bookDao.findAvailableBooks();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAvailableBooksByCategory(String category) throws DataAccessException {
        log.debug("Finding available books by category: {}", category);
        return bookDao.findAvailableBooksByCategory(category);
    }

    @Override
    public Book updateBook(Book book) throws DataAccessException {
        log.info("Attempting to update book with ID: {}", book.getBookId());
        
        Optional<Book> existingBook = bookDao.findByBookId(book.getBookId());
        if (existingBook.isEmpty()) {
            throw new IllegalArgumentException("Book not found with ID: " + book.getBookId());
        }

        Book existing = existingBook.get();
        if (!existing.getTitle().equalsIgnoreCase(book.getTitle())) {
            if (bookDao.existsByTitle(book.getTitle())) {
                throw new IllegalArgumentException("Another book with this title already exists: " + book.getTitle());
            }
        }

        Book updatedBook = bookDao.update(book);
        log.info("Successfully updated book with ID: {}", updatedBook.getBookId());
        return updatedBook;
    }

    @Override
    public boolean markBookAsIssued(String bookId) throws DataAccessException {
        log.info("Marking book as issued: {}", bookId);
        return bookDao.updateAvailability(bookId, "I");
    }

    @Override
    public boolean markBookAsAvailable(String bookId) throws DataAccessException {
        log.info("Marking book as available: {}", bookId);
        return bookDao.updateAvailability(bookId, "A");
    }

    @Override
    public boolean deleteBook(String bookId) throws DataAccessException {
        log.info("Attempting to delete book with ID: {}", bookId);
        
        if (!bookDao.existsByBookId(bookId)) {
            throw new IllegalArgumentException("Book not found with ID: " + bookId);
        }

        boolean deleted = bookDao.deleteByBookId(bookId);
        if (deleted) {
            log.info("Successfully deleted book with ID: {}", bookId);
        }
        return deleted;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isBookExists(String bookId) throws DataAccessException {
        return bookDao.existsByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTitleExists(String title) throws DataAccessException {
        return bookDao.existsByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalBooksCount() throws DataAccessException {
        return bookDao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long getBookCountByCategory(String category) throws DataAccessException {
        return bookDao.countByCategory(category);
    }
}
