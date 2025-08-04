package Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import DAO.Databasemanager;
import domain.Book;

public class BookService implements BookServiceInterface {

    Databasemanager db = new Databasemanager();

    @Override
    public int addbooks(Book book) throws SQLIntegrityConstraintViolationException {
        return db.addBooks(book);
    }

    @Override
    public void updateavailability() {
        db.Updatebookavailability(null, null);
    }

    @Override
    public boolean updatebookdetails(int id, Book book) {
        return db.updateBookDetails(id, book);
		
    }

    @Override
    public List<Book> viewallbooks() {
        return db.viewallbooks();
    }

    @Override
    public boolean getBookbyId(int id) {
        return db.getBookbyId(id);
    }

    @Override
    public Book getbookwithid(int id) {
        return db.getBookwithId(id);
    }
}
