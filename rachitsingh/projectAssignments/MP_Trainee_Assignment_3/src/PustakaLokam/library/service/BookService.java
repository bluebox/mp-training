package PustakaLokam.library.service;

import PustakaLokam.library.model.Book;
import PustakaLokam.library.dao.BookDAO;
import PustakaLokam.library.exceptionhandler.BookNotFoundException;
import PustakaLokam.library.exceptionhandler.DatabaseOperationException;
import PustakaLokam.library.exceptionhandler.InvalidBookDataException;
// import PustakaLokam.library.enums.AvailabilityStatus;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public boolean insertNewBooks(List<Book> booksToBeInserted) {
        if (booksToBeInserted == null || booksToBeInserted.isEmpty()) {
            throw new InvalidBookDataException("No books are there to put in the library.");
        }

        for (var book: booksToBeInserted) {
            if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
                throw new InvalidBookDataException("Book title not provided.");
            }
            if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
                throw new InvalidBookDataException("Book Author not provided.");
            }
            if (book.getCategory() == null || book.getCategory().trim().isEmpty()) {
                throw new InvalidBookDataException("Book Category not provided.");
            }
        }
        return bookDAO.insertBatchOfBooks(booksToBeInserted);
    }

    public boolean updateBookDetails(Book book)throws BookNotFoundException, DatabaseOperationException  {
    	return bookDAO.updateBookDetails(book);
    }
    public Book getBookByID(int bookID) {
        try {
            Book book = bookDAO.getBookByID(bookID);
            if (book == null) {
                throw new BookNotFoundException("No book found with ID: " + bookID);
            }
            return book;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to fetch book with ID: " + bookID, e);
        }
    }
    
    public List<Book> fetchAllBooks() {
        return bookDAO.getAllBooks();
    }
    public boolean deleteBook(int bookID) {
        return bookDAO.deleteBookByID(bookID);
    }
}
