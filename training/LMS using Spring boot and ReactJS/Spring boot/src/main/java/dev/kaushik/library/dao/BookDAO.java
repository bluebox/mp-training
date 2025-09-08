package dev.kaushik.library.dao;

import java.util.List;

import dev.kaushik.library.model.Book;

public interface BookDAO {

    int addBook(Book book);

    boolean updateBook(Book book);

    int deleteBooksInBatch(Integer... bookIds);

    List<Book> findBooks(Book criteria);
    
	int updateBookStatusBatch(List<Integer> bookIds);
} 