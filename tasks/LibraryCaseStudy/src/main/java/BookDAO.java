import java.util.List;

public interface BookDAO {
    boolean addBook(Book book);
    boolean updateBookDetails(Book book);
    boolean updateBookAvailability(int bookId, char availability);
    Book getBookById(int bookId);
    List<Book> getAllBooks();
}