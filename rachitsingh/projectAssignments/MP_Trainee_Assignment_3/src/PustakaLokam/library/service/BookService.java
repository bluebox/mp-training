package PustakaLokam.library.service;

import PustakaLokam.library.model.Book;
import PustakaLokam.library.dao.BookDAO;
import PustakaLokam.library.exceptionhandler.InvalidBookDataException;
// import PustakaLokam.library.enums.AvailabilityStatus;

import java.util.List;

public class BookService {
    private BookDAO bookDao;

    public BookService() {
        this.bookDao = new BookDAO();
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
        return bookDao.insertBatchOfBooks(booksToBeInserted);
    }

    public boolean updateBookDetails(Book book) {
    	return bookDao.updateBookDetails(book);
    }

    // public boolean updateBookAvailability(int bookID, AvailabilityStatus status)
    // {
    // return bookDao.updateBookAvailability(bookID, status);
    // }

    public List<Book> fetchAllBooks() {
        return bookDao.getAllBooks();
    }
}
