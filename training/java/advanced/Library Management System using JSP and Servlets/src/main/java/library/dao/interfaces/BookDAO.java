package library.dao.interfaces;

import java.util.List;
import library.exception.LibraryException;
import library.model.Book;

public interface BookDAO {

    void addBook(Book book, String createdBy) throws LibraryException;

    boolean updateBook(Book book, String updatedBy) throws LibraryException;

    boolean updateBookAvailability(int bookId, String newAvailabilityCode, String updatedBy) throws LibraryException;

    boolean deleteBook(int bookId) throws LibraryException;

    int deleteBooksBatch(List<Integer> bookIds) throws LibraryException;

    List<Book> findBooks(Book criteria) throws LibraryException;

	int updateBookAvailabilityBatch(List<Integer> bookIds, String updatedBy);
}