package Library.src.main.java.com.LibraryManagement.service;
import Library.src.main.java.com.LibraryManagement.model.*;
import java.util.*;

public interface BookService {
    void addBook(Book book) throws Exception;
    void updateBook(Book book) throws Exception;
    void updateBookAvailability(int bookId, char availability) throws Exception;
    List<Book> getAllBooks() throws Exception;
    Book getBookById(int bookId) throws Exception;
}