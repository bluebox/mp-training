package main.com.library.services;

import main.com.library.domain.Book;
import main.com.library.dao.BookDAO;
import java.util.List;

public class BookService {
    private BookDAO bookDAO = new BookDAO();

    public boolean addBook(Book book) {

        return bookDAO.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }


}