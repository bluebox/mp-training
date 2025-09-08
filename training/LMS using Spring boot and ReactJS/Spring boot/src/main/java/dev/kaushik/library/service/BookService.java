package dev.kaushik.library.service;

import java.util.List;

import dev.kaushik.library.model.Book;

public interface BookService {

    int addBook(Book book);

    List<Book> findBooks(Book criteria);

    boolean updateBook(Book book);

    boolean deleteBook(Integer bookId);

    int deleteBooksBatch(List<Integer> bookIds);

	int updateBookStatusBatch(List<Integer> bookIds);
}