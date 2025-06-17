package com.services;

import com.models.Book;
import com.DAO.BookDAO;

public class BookService {
    private final BookDAO dao = new BookDAO();

    public boolean addBook(String title, String author, String category, char status, char availability) {
        
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title is required.");
            return false;
        }
        if (author == null || author.trim().isEmpty()) {
            System.out.println("Author is required.");
            return false;
        }
        if (category == null || category.trim().isEmpty()) {
            System.out.println("Category is required.");
            return false;
        }
        if (status != 'A' && status != 'I') {
            System.out.println("Status must be 'A' (Active) or 'I' (Inactive).");
            return false;
        }

        if (availability != 'A' && availability != 'I') {
            System.out.println("Availability must be 'A' (Available) or 'I' (Issued).");
            return false;
        }
        Book book = new Book(title.trim(), author.trim(), category.trim(), status, availability);
        return dao.save(book);
    }

    public boolean updateBook(int id, String title, String author, String category, char status, char availability) {
        
        if (id <= 0) {
            System.out.println("Invalid book ID.");
            return false;
        }

        
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title is required.");
            return false;
        }

       
        if (author == null || author.trim().isEmpty()) {
            System.out.println("Author is required.");
            return false;
        }

        
        if (category == null || category.trim().isEmpty()) {
            System.out.println("Category is required.");
            return false;
        }

        
        if (status != 'A' && status != 'I') {
            System.out.println("Status must be 'A' (Active) or 'I' (Inactive).");
            return false;
        }

        
        if (availability != 'A' && availability != 'I') {
            System.out.println("Availability must be 'A' (Available) or 'I' (Issued).");
            return false;
        }

        
        Book book = new Book(id, title.trim(), author.trim(), category.trim(), status, availability);
        return dao.update(book);
    }

    public boolean returnBook(int bookId) {
        
        if (bookId <= 0) {
            System.out.println("Invalid Book ID.");
            return false;
        }
        return dao.returnBook(bookId);
    }

}
