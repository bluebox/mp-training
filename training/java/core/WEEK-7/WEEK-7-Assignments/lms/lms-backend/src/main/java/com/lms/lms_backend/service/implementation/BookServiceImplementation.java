package com.lms.lms_backend.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.lms_backend.constant.BookStatus;
import com.lms.lms_backend.dao.BookDao;
import com.lms.lms_backend.exception.DatabaseException;
import com.lms.lms_backend.exception.InvalidOperationException;
import com.lms.lms_backend.exception.ResourceNotFoundException;
import com.lms.lms_backend.model.Book;
import com.lms.lms_backend.service.BookService;

@Service
public class BookServiceImplementation implements BookService {

    private final BookDao bookDao;

    public BookServiceImplementation(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        List<Book> existingBooks = bookDao.selectAllBooks();
        boolean duplicate = existingBooks.stream()
                .anyMatch(b -> b.getTitle().equalsIgnoreCase(book.getTitle())
                        && b.getAuthor().equalsIgnoreCase(book.getAuthor()));
        if (duplicate) {
            throw new InvalidOperationException("Book with same title and author already exists");
        }

        int count = bookDao.addBook(book);
        if (count != 1) {
            throw new DatabaseException("Book insertion failed");
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.selectAllBooks();
    }

    @Override
    @Transactional
    public void updateBookDetails(int id, Book newBook) {
        Book oldBook = bookDao.selectBookById(id);
        if (oldBook == null) {
            throw new ResourceNotFoundException("Book not found with ID " + id);
        }

        if (oldBook.equals(newBook)) {
            throw new InvalidOperationException("At least one detail should be updated");
        }

        if (!BookStatus.ACTIVE.equals(oldBook.getStatus())) {
            throw new InvalidOperationException("Cannot update inactive book");
        }

        int logInserted = bookDao.insertBookLog(oldBook);
        if (logInserted != 1) {
            throw new DatabaseException("Failed to insert log");
        }

        int updated = bookDao.updateBookDetails(oldBook, newBook);
        if (updated != 1) {
            throw new DatabaseException("Book already issued or not updated correctly");
        }
    }

    @Override
    @Transactional
    public void deleteBook(int id) {
        Book book = bookDao.selectBookById(id);
        if (book == null) {
            throw new ResourceNotFoundException("Book not found with ID " + id);
        }

        int logInserted = bookDao.insertBookLog(book);
        if (logInserted != 1) {
            throw new DatabaseException("Failed to insert log");
        }

        int deleted = bookDao.deleteBook(book);
        if (deleted != 1) {
            throw new DatabaseException("An issued book cannot be deleted or not deleted correctly");
        }
    }

    @Override
    public List<String> getAllCategories() {
        return bookDao.getAllCategories();
    }
}
