package com.lms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lms.model.Book;
import com.lms.repositories.BookRepository;
import com.lms.service.interfaces.implementation.BookServiceImplementation;

@ExtendWith(MockitoExtension.class) 
class LibraryManagementApplicationTests {

    @Mock
    private BookRepository bookRepo;
    
    @InjectMocks
    private BookServiceImplementation bookService;

    private Book book;
    private int bookId=1;

    @BeforeEach
    public void setup() {
        book = Book.builder()
                .bookId(bookId)
                .title("Stories")
                .author("Author")
                .category("Story")
                .status("A")
                .availability("A")
                .build();
    }

    @Test
    public void checkAddBook() {
        given(bookRepo.addBook(any(Book.class))).willReturn(book);

        Book savedBook = bookService.addBook(book);
        assertNotNull(savedBook);
        assertThat(savedBook.getTitle()).isEqualTo("Stories"); 
        assertThat(savedBook.getAuthor()).isEqualTo("Author");
    }
    
    
    @Test
    public void checkgetBooks() {
    	Book book1=Book.builder()
    			.bookId(2)
    			.title("Horror Stories")
    			.author("Rajeev")
    			.category("Horror")
    			.status("A")
    			.availability("A")
    			.build();
    	given(bookRepo.getAllBooks()).willReturn(List.of(book,book1));
    	List<Book>books=bookService.getAllBooks();
    	assertThat(books).isNotNull();
    	assertThat(books.size()).isEqualTo(2);
    }
    
    @Test
    public void checkgetById() {
    	when(bookRepo.getBookById(bookId)).thenReturn(Optional.of(book));
    	
    	Book booku=bookService.getBookById(1).get();
    	assertThat(booku).isNotNull();
    }
    
    @Test
    public void checkgetByNoId() {
    	when(bookRepo.getBookById(anyInt())).thenReturn(Optional.empty());
    	
    	assertThatThrownBy(()->bookService.getBookById(3)).isInstanceOf(Exception.class).hasMessage("Employee not found ");
    }
    
    @Test
    public void checkUpdateBook() {
        when(bookRepo.getBookById(book.getBookId())).thenReturn(Optional.of(book));
        Book newBook=Book.builder()
        		.bookId(book.getBookId())
                .title("Local Stories")
                .author("Raju")
                .category(book.getCategory())
                .status(book.getStatus())
                .availability(book.getAvailability())
                .build();
        given(bookRepo.updateBook(any(Book.class))).willReturn(newBook);
        
        Book actualUpdatedBook = bookService.updateBook(book);
       
        assertThat(actualUpdatedBook).isNotNull();
        assertThat(actualUpdatedBook.getAuthor()).isEqualTo("Raju"); 
        assertThat(actualUpdatedBook.getTitle()).isEqualTo("Local Stories"); 
    }
    
    
}