package com.libraryManagement.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.libraryManagement.utility.pojos.Book;

public interface BookDao {
    public boolean addBook(Book book);
    public boolean updateBook(Book book);
    public List<Book> getByBookId(int id);
    public boolean verifyBook(Book book) throws IOException, SQLException, ClassNotFoundException;
}
