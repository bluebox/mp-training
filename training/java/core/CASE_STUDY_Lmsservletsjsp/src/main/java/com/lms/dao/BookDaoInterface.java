package com.lms.dao;

import java.util.List;

import com.lms.model.Book;
import com.lms.model.BookCategory;

public interface BookDaoInterface {

      Book addBook(String title, String author, BookCategory category, char status, char availability);
    boolean updateBook(String bookId, String title, String author, BookCategory category, char status, char availability);

      List<Book> getBooks();

       Book getBookById(String bookId);
}
