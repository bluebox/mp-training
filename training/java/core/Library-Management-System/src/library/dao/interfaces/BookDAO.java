package library.dao.interfaces;

import java.util.List;
import java.util.Map;

import library.exception.LibraryException;
import library.model.Book;

public interface BookDAO {

    void addBook(Book book, String createdBy) throws LibraryException;

    boolean updateBook(Book book, String updatedBy) throws LibraryException;

    boolean updateBookAvailability(int bookId, String newAvailabilityCode, String updatedBy) throws LibraryException;

    boolean deleteBook(int bookId) throws LibraryException;

    boolean[] deleteBooksBatch(List<Integer> bookIds) throws LibraryException;

    List<Book> findBooks(Map<String, Object> criteria) throws LibraryException;
}