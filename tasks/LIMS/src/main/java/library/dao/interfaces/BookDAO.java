package library.dao.interfaces;

import java.util.List;
import java.util.Map;

import library.exception.LibraryException;
import library.model.Book;

public interface BookDAO {

    void addBook(Book book, String createdBy) throws LibraryException;

    List<Book> findBooks(Map<String, Object> criteria) throws LibraryException;
    
    boolean updateBook(Book book, String updatedBy) throws LibraryException;
    
    boolean updateBookAvailability(List<Integer> bookIds,String updatedBy) throws LibraryException;
    
    boolean deleteBooks(List<Integer> bookIds) throws LibraryException;

}