package Service;



import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import domain.Book;

public interface BookServiceInterface {

    void addbooks(Book book) throws SQLIntegrityConstraintViolationException;

    void updateavailability();

    void updatebookdetails(int id, Book book);

    List<Book> viewallbooks();

    boolean getBookbyId(int id);

    Book getbookwithid(int id);
}
