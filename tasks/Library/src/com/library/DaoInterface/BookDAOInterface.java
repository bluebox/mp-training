package com.library.DaoInterface;

import com.library.domain.Book;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BookDAOInterface {

    public abstract List<Book> getAllBooks(Connection conn);

    public abstract boolean addBook(Connection conn, Book book);

    public abstract boolean isAvailable(Connection conn, int bookId) throws Exception;

    public abstract Book getBookById(Connection conn, int bookId, char status) throws Exception;

    public abstract boolean insertIntoBookLog(Connection conn, Book book) throws Exception;

    public abstract void updateAvailability(Connection conn, int bookId, char status) throws SQLException;
}
