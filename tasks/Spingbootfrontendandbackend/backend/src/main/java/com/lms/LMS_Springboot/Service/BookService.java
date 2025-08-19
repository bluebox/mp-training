package com.lms.LMS_Springboot.Service;

import com.lms.LMS_Springboot.DAO.BookDao;
import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public boolean addBook(Book book) {
        return bookDao.addBooks(book);
    }

    public boolean updateBookDetails(int bookId, Book book) {
        return bookDao.updateBookDetails(bookId, book);
    }

    public Book getBookById(int bookId) {
        return bookDao.getbookid(bookId);
    }

    public boolean updateAvailability(int bookId, Availability availability) {
        return bookDao.Updatebookavailability(bookId, availability);
    }

    public List<Book> getAllBooks() {
        return bookDao.viewallbooks();
    }
}
