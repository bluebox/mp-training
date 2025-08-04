package Service;



import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import domain.Book;

public interface BookServiceInterface {

    int addbooks(Book book) throws SQLIntegrityConstraintViolationException;

    void updateavailability();

    boolean updatebookdetails(int id, Book book);

    List<Book> viewallbooks();

    boolean getBookbyId(int id);

    Book getbookwithid(int id);
}
