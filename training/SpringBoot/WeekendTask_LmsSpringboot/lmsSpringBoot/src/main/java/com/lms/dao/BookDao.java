package com.lms.dao;

import com.lms.model.Book;
import java.util.List;

public interface BookDao {
    List<Book> findAll() throws Exception;
    Book findByBookId(String bookId) throws Exception;
    boolean save(Book book) throws Exception;
    boolean update(Book book) throws Exception;
}
