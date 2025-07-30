import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public boolean addBook(Book book) throws IllegalArgumentException {
        if (book == null || book.getTitle() == null || book.getTitle().trim().isEmpty() ||
                book.getAuthor() == null || book.getAuthor().trim().isEmpty() ||
                book.getCategory() == null || book.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Book details cannot be null or empty");
        }
        return bookDAO.addBook(book);
    }

    public boolean updateBookDetails(Book book) throws IllegalArgumentException {
        if (book == null ||
                book.getTitle() == null || book.getTitle().trim().isEmpty() ||
                book.getAuthor() == null || book.getAuthor().trim().isEmpty() ||
                book.getCategory() == null || book.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid book details");
        }
        return bookDAO.updateBookDetails(book);
    }

    public boolean updateBookAvailability(int bookId, char availability) throws IllegalArgumentException {
        if (bookId <= 0 || (availability != 'A' && availability != 'I')) {
            throw new IllegalArgumentException("Invalid book ID or availability status");
        }
        return bookDAO.updateBookAvailability(bookId, availability);
    }

    public Book getBookById(int bookId) throws IllegalArgumentException {
        if (bookId <= 0) {
            throw new IllegalArgumentException("Invalid book ID");
        }
        return bookDAO.getBookById(bookId);
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public List<Book> getAvailableBooks() {
        return bookDAO.getAllBooks().stream()
                .filter(book -> book.getAvailability() == 'A')
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByCategory(String category) {
        return bookDAO.getAllBooks().stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}