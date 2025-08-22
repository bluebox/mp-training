package com.library.Library.repository;

import com.library.Library.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer> {
	 @Query("SELECT b.category, COUNT(b) FROM Book b GROUP BY b.category")
	    List<Object[]> countBooksByCategory();
}
