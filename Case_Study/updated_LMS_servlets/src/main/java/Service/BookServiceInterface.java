package Service;



import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import domain.Book;
import domain.checking_enum.Availability;

public interface BookServiceInterface {

    boolean addbooks(Book book) throws SQLIntegrityConstraintViolationException;

    void updateavailability(Book book,Availability available);

    boolean updatebookdetails(int id, Book book);

    List<Book> viewallbooks();

    boolean getBookbyId(int id);

    Book getbookwithid(int id);
}
