package service;

import dao.BookDAO;
import model.Book;

public class BookService {
    private BookDAO dao = new BookDAO();

    public boolean addBook(Book book) throws Exception {
        return dao.insertBookWithTransaction(book);
    }
}