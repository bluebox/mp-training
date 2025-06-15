package PustakaLokam.library.service;

import PustakaLokam.library.model.Book;
import PustakaLokam.library.dao.BookDAO;
// import PustakaLokam.library.enums.AvailabilityStatus;

import java.util.List;

public class BookService {
    private BookDAO bookDao;

    public BookService() {
        this.bookDao = new BookDAO();
    }

    public boolean insertNewBooks(List<Book> booksToBeInserted) {
        if (booksToBeInserted == null || booksToBeInserted.isEmpty()) {
            System.out.println("No books are there to put in the library.");
            return false;
        }

        for (int i = 0; i < booksToBeInserted.size(); i++) {
            if (booksToBeInserted.get(i).getTitle() == null || booksToBeInserted.get(i).getTitle().trim().isEmpty()) {
                System.out.println("Book title not provided.");
                return false;
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
